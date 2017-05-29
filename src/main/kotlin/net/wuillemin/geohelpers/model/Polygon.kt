package net.wuillemin.geohelpers.model

import com.github.salomonbrys.kotson.jsonArray
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonElement

/**
 * Abstract class for all Polygons
 */
open class Polygon : Surface {

    val rings: List<LinearRing>
    final override val coordinateDimension: Int
    final override val spatialDimension: Int

    constructor(rings: List<LinearRing>) : this(rings, "Polygon")

    constructor(rings: List<LinearRing>, geometryType: String) : super(geometryType) {
        checkRingsCoherence(rings)
        this.rings = rings
        if (this.rings.isEmpty()) {
            coordinateDimension = 0
            spatialDimension = 0
        }
        else {
            coordinateDimension = this.rings.first().coordinateDimension
            spatialDimension = this.rings.first().spatialDimension
        }
    }

    private fun checkRingsCoherence(rings: List<LinearRing>) {

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
        return jsonArray(rings.map { ring -> ring.generateAsGeoJsonSubObject() })
    }
}
