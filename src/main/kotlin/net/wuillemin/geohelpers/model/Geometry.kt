package net.wuillemin.geohelpers.model

import com.google.gson.JsonElement

/**
 * The Geometry is the abstract base class of all geometric object
 */
abstract class Geometry(val geometryType: String) {

    /**
     * Get the number of intrinsic dimension of the object:
     * * 0: Point
     * * 1: Curve
     * * 2: Surface
     * * -1: Collection
     */
    abstract val dimension: Int

    /**
     * Get the number of dimensions of the object
     * * 2: 2D point
     * * 3: 2D + M or 2D + Z
     * * 4: Surface
     * * -1: Not applicable / Not known (ie Empty collection)
     * </ul>
     */
    abstract val coordinateDimension: Int

    /**
     * Get the number of spatial dimension of the object (without the M)
     * * 2: 2D point
     * * 3: 3D
     * * -1: Not applicable / Not known (ie Empty collection)
     * </ul>
     */
    abstract val spatialDimension: Int

    /**
     * Return a Json object with the given object fully formatted, meaning
     * the object have the type and the coordinate attributes. The object
     * is ready to converted to a valid String
     */
    abstract fun generateAsGeoJsonObject(): JsonElement

    /**
     * Return just the representation of the data of the object. This function
     * is meant to be used by [generateAsGeoJsonObject].
     */
    abstract fun generateAsGeoJsonSubObject(): JsonElement
}
