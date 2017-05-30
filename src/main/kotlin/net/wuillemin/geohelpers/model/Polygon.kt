package net.wuillemin.geohelpers.model

import com.github.salomonbrys.kotson.jsonArray
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonElement

/**
 * Abstract class for all Polygons
 */
open class Polygon : Surface {

    /**
     * The list of linear rings referenced by the Polygon
     */
    val rings: List<LinearRing>

    /**
     * Get the number of coordinate dimensions of the object.
     */
    override val coordinateDimension get() = rings.first().coordinateDimension

    /**
     * Get the number of spatial dimensions of the object.
     */
    override val spatialDimension get() = rings.first().spatialDimension

    /**
     * Constructor. Checks if the Polygon has at least one linear string points and check that all given
     * rings have the same coordinateDimension and spatialDimension. In both case, an AssertionError is
     * thrown.
     *
     * @param rings the list of linear rings to be referenced in the collection
     */
    constructor(rings: List<LinearRing>) : this(rings, "Polygon")

    /**
     * Constructor to be used by sub classes that allows to define the geometryType
     *
     * @param rings the list of linear rings to be referenced in the collection
     * @param geometryType the name of the geometry type, for example "LinearRing"
     */
    constructor(rings: List<LinearRing>, geometryType: String) : super(geometryType) {
        checkRingsCoherence(rings)
        this.rings = rings
    }

    private fun checkRingsCoherence(rings: List<LinearRing>) {

        if(rings.isEmpty()) {
            throw AssertionError("Bad Polygon format - at least one linear ring is mandatory")
        }

        // Check that all rings have the same coordinateDimension and spatialDimension
        val first: LinearRing = rings.first()
        if (rings
                .filter({
                    (it.coordinateDimension != first.coordinateDimension) ||
                            (it.spatialDimension != first.spatialDimension)
                })
                .isNotEmpty()) {
            throw AssertionError("Bad Polygon format - all rings must be of the same type: 2D, 2D+M, 3D or 3D+M")
        }
    }

    override fun generateAsGeoJsonObject(): JsonElement {
        return jsonObject(
                "type" to "Polygon",
                "coordinates" to generateAsGeoJsonSubObject())
    }

    override fun generateAsGeoJsonSubObject(): JsonElement {
        return jsonArray(rings.map { it.generateAsGeoJsonSubObject() })
    }
}
