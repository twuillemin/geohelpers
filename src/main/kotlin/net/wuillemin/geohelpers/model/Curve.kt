package net.wuillemin.geohelpers.model

/**
 * Abstract class for all curves
 */
abstract class Curve(geometryType: String) : Geometry(geometryType) {

    /**
     * For curves, dimension is always 1
     */
    override val dimension get() = 1
}
