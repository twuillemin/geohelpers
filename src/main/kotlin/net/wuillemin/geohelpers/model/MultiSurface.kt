package net.wuillemin.geohelpers.model

/**
 * A MultiSurface is a collection of Surfaces, which may be non homogeneous
 */
open class MultiSurface : GeometryCollection {

    override val dimension get() = 2
    override val coordinateDimension get() = -1
    override val spatialDimension get() = -1

    constructor(geometries: List<Surface>) : this(geometries, "MultiSurface")

    constructor(geometries: List<Surface>, geometryType: String) : super(geometries, geometryType)
}
