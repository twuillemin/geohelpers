package net.wuillemin.geohelpers.model

/**
 * A MultiCurve is a collection of Curves, which may be non homogeneous
 */
open class MultiCurve : GeometryCollection {

    /**
     * Get the dimension of the object. Will always return 1 for MultiCurve objects
     */
    override val dimension get() = 1

    /**
     * Constructor for MultiCurve objects
     *
     * @param geometries the list of curves to be referenced in the collection
     */
    constructor(geometries: List<Curve>) : this(geometries, "MultiCurve")

    /**
     * Constructor to be used by sub classes that allows to define the geometryType
     *
     * @param geometries the list of geometries to be referenced in the collection
     * @param geometryType the name of the geometry type, for example "MultiLineString"
     */
    protected constructor(geometries: List<Curve>, geometryType: String) : super(geometries, geometryType)
}
