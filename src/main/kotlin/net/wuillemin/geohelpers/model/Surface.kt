package net.wuillemin.geohelpers.model

/**
 * Abstract class for all Surfaces
 */
abstract class Surface(geometryType: String) : Geometry(geometryType) {

    override val dimension get() = 2
}
