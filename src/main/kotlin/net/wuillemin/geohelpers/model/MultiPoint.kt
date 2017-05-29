package net.wuillemin.geohelpers.model

import com.github.salomonbrys.kotson.jsonArray
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonElement

/**
 * A MultiPoint is a collection of Points, which may be non homogeneous
 */
open class MultiPoint : GeometryCollection {

    override val dimension get() = 0
    override val coordinateDimension get() = -1
    override val spatialDimension get() = -1

    constructor(geometries: List<Point>) : this(geometries, "MultiPoint")

    constructor(geometries: List<Point>, geometryType: String) : super(geometries, geometryType)

    override fun generateAsGeoJsonObject(): JsonElement {
        return jsonObject(
                "type" to "MultiPoint",
                "coordinates" to generateAsGeoJsonSubObject())
    }

    override fun generateAsGeoJsonSubObject(): JsonElement {
        return jsonArray(geometries.map { geometry -> geometry.generateAsGeoJsonSubObject() })
    }
}
