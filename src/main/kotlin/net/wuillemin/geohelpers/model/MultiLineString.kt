package net.wuillemin.geohelpers.model

import com.github.salomonbrys.kotson.jsonArray
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonElement

/**
 * A MultiLineString is a collection of LineStrings, which may be non homogeneous
 */
open class MultiLineString : MultiCurve {

    override val dimension get() = 1
    override val coordinateDimension get() = -1
    override val spatialDimension get() = -1

    constructor(geometries: List<LineString>) : this(geometries, "MultiLineString")

    constructor(geometries: List<LineString>, geometryType: String) : super(geometries, geometryType)

    override fun generateAsGeoJsonObject(): JsonElement {
        return jsonObject(
                "type" to "MultiLineString",
                "coordinates" to generateAsGeoJsonSubObject())
    }

    override fun generateAsGeoJsonSubObject(): JsonElement {
        return jsonArray(geometries.map { geometry -> geometry.generateAsGeoJsonSubObject() })
    }
}
