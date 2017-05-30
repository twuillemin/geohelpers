package net.wuillemin.geohelpers.model

import com.github.salomonbrys.kotson.jsonArray
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonElement

/**
 * A class representing a LineString. A LineString must be composed of at least two vertices
 */
open class LineString : Curve {

    /**
     * The list of the vertices (points) in the collection
     */
    val vertices: List<Point>

    /**
     * Get the number of coordinate dimensions of the object.
     */
    override val coordinateDimension get() = vertices.first().coordinateDimension

    /**
     * Get the number of spatial dimensions of the object.
     */
    override val spatialDimension get() = vertices.first().spatialDimension

    /**
     * Constructor. Checks if the LineString has at least two points and check that all given
     * vertices have the same coordinateDimension and spatialDimension. In both case, an AssertionError is
     * thrown.
     *
     * @param vertices the vertices (points) of the LineString
     */
    constructor(vertices: List<Point>) : this(vertices, "LineString")

    /**
     * Constructor to be used by sub classes that allows to define the geometryType
     *
     * @param vertices the vertices (points) of the LineString
     * @param geometryType the name of the geometry type, for example "LinearRing"
     */
    protected constructor(vertices: List<Point>, geometryType: String) : super(geometryType) {

        // Check for at least two vertices
        if (vertices.size < 2) {
            throw AssertionError("Bad LineString format - at least two vertices are needed")
        }

        checkVerticesCoherence(vertices)
        this.vertices = vertices
    }

    private fun checkVerticesCoherence(vertices: List<Point>) {

        // Check that all rings have the same coordinateDimension and spatialDimension
        val first: Point = vertices.first()
        if (vertices
                .filter({
                    (it.coordinateDimension != first.coordinateDimension) ||
                            (it.spatialDimension != first.spatialDimension)
                })
                .isNotEmpty()) {
            throw AssertionError("Bad LineString format - all LineStrings must be of the same type: 2D, 2D+M, 3D or 3D+M")
        }
    }

    override fun generateAsGeoJsonObject(): JsonElement {
        return jsonObject(
                "type" to "LineString",
                "coordinates" to generateAsGeoJsonSubObject())
    }

    override fun generateAsGeoJsonSubObject(): JsonElement {
        return jsonArray(vertices.map { it.generateAsGeoJsonSubObject() })
    }
}
