package net.wuillemin.geohelpers.model

/**
 * Abstract class for all curves
 */
abstract class Curve(geometryType: String) : Geometry(geometryType) {

    override val dimension get() = 1
}
