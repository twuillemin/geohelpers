package net.wuillemin.geohelpers.model

import com.github.salomonbrys.kotson.jsonArray
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonElement

/**
 * Abstract class for all PolyhedralSurfaces
 */
open class PolyhedralSurface : Surface {

    val patches: List<Polygon>
    final override val coordinateDimension: Int
    final override val spatialDimension: Int

    constructor(patches: List<Polygon>) : this(patches, "PolyhedralSurface")

    constructor(patches: List<Polygon>, geometryType: String) : super(geometryType) {
        checkPatchesCoherence(patches)
        this.patches = patches
        if (this.patches.isEmpty()) {
            coordinateDimension = 0
            spatialDimension = 0
        }
        else {
            coordinateDimension = this.patches.first().coordinateDimension
            spatialDimension = this.patches.first().spatialDimension
        }
    }

    private fun checkPatchesCoherence(rings: List<Polygon>) {

        // Check that all patches have the same coordinateDimension and spatialDimension
        val first: Polygon = patches.first()
        if (rings
                .filter({
                    (it.coordinateDimension != first.coordinateDimension) ||
                            (it.spatialDimension != first.spatialDimension)
                })
                .isNotEmpty()) {
            throw AssertionError("Bad PolyhedralSurface format - all patches must be of the same type: 2D, 2D+M, 3D or 3D+M")
        }
    }

    override fun generateAsGeoJsonObject(): JsonElement {
        throw AssertionError("PolyhedralSurface can not be used in GeoJson")
    }

    override fun generateAsGeoJsonSubObject(): JsonElement {
        throw AssertionError("PolyhedralSurface can not be used in GeoJson")
    }
}
