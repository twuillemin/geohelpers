package net.wuillemin.geohelpers.model

import com.google.gson.JsonElement

/**
 * Abstract class for all PolyhedralSurfaces
 */
open class PolyhedralSurface : Surface {

    /**
     * The list of patches referenced by the Polygon
     */
    val patches: List<Polygon>

    /**
     * Get the number of coordinate dimensions of the object.
     */
    override val coordinateDimension get() = patches.first().coordinateDimension

    /**
     * Get the number of spatial dimensions of the object.
     */
    override val spatialDimension get() = patches.first().spatialDimension

    /**
     * Constructor. Checks if the polyhedral surface has at least one linear string points and check that all given
     * rings have the same coordinateDimension and spatialDimension. In both case, an AssertionError is
     * thrown.
     *
     * @param patches the list of linear rings to be referenced in the collection
     */
    constructor(patches: List<Polygon>) : this(patches, "PolyhedralSurface")

    /**
     * Constructor to be used by sub classes that allows to define the geometryType
     *
     * @param patches the list of linear rings to be referenced in the collection
     * @param geometryType the name of the geometry type, for example "LinearRing"
     */
    constructor(patches: List<Polygon>, geometryType: String) : super(geometryType) {
        checkPatchesCoherence(patches)
        this.patches = patches
    }

    private fun checkPatchesCoherence(patches: List<Polygon>) {

        if(patches.isEmpty()) {
            throw AssertionError("Bad PolyhedralSurface format - at least one linear ring is mandatory")
        }

        // Check that all patches have the same coordinateDimension and spatialDimension
        val first: Polygon = patches.first()
        if (patches
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
