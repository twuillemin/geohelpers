package net.wuillemin.geohelpers.model

/**
 * A MultiSurface is a collection of Surfaces, which may be non homogeneous
 */
open class MultiSurface : GeometryCollection {

    /**
     * Get the dimension of the object. Will always return 2 for MultiSurface objects
     */
    override val dimension get() = 2

    /**
     * Constructor for MultiSurface objects
     *
     * @param geometries the list of surfaces to be referenced in the collection
     */
    constructor(geometries: List<Surface>) : this(geometries, "MultiSurface")

    /**
     * Constructor to be used by sub classes that allows to define the geometryType
     *
     * @param geometries the list of surfaces to be referenced in the collection
     * @param geometryType the name of the geometry type, for example "MultiPolygon"
     */
    protected constructor(geometries: List<Surface>, geometryType: String) : super(geometries, geometryType)
}
