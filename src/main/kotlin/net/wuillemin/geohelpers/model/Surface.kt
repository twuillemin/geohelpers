package net.wuillemin.geohelpers.model

/**
 * Abstract class for all Surfaces
 */
abstract class Surface(geometryType: String) : Geometry(geometryType) {

    /**
     * For surfaces, dimension is always 2
     */
    final override val dimension get() = 2
}
