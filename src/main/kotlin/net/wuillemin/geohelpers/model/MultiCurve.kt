package net.wuillemin.geohelpers.model

/**
 * A MultiCurve is a collection of Curves, which may be non homogeneous
 */
open class MultiCurve : GeometryCollection {

    override val dimension get() = 1
    override val coordinateDimension get() = -1
    override val spatialDimension get() = -1

    constructor(geometries: List<Curve>) : this(geometries, "MultiCurve")

    constructor(geometries: List<Curve>, geometryType: String) : super(geometries, geometryType)
}
