package net.wuillemin.geohelpers.model

import com.github.salomonbrys.kotson.jsonArray
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonElement

/**
 * A Simple class representing a LineString. A LineString must be composed of at least two vertices
 */
open class LineString : Curve {

    val vertices: List<Point>
    override val coordinateDimension get() = vertices.first().coordinateDimension
    override val spatialDimension get() = vertices.first().spatialDimension

    constructor(vertices: List<Point>) : this(vertices, "LineString")

    constructor(vertices: List<Point>, geometryType: String) : super(geometryType) {

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

    override fun generateAsGeoJsonSubObject(): JsonElement  {
        return jsonArray(vertices.map { vertex -> vertex.generateAsGeoJsonSubObject() })
    }
}
