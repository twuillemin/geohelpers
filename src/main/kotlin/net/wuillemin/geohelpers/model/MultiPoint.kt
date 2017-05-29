package net.wuillemin.geohelpers.model

import com.github.salomonbrys.kotson.jsonArray
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonElement

/**
 * A MultiPoint is a collection of Points, which may be non homogeneous
 */
open class MultiPoint : GeometryCollection {

    /**
     * Get the dimension of the object. Will always return 0 for MultiPoint objects
     */
    override val dimension get() = 0

    /**
     * Constructor for MultiPoint objects
     *
     * @param geometries the list of Point to be referenced in the collection
     */
    constructor(geometries: List<Point>) : super(geometries, "MultiPoint")

    override fun generateAsGeoJsonObject(): JsonElement {
        return jsonObject(
                "type" to "MultiPoint",
                "coordinates" to generateAsGeoJsonSubObject())
    }

    override fun generateAsGeoJsonSubObject(): JsonElement {
        return jsonArray(geometries.map { geometry -> geometry.generateAsGeoJsonSubObject() })
    }
}
