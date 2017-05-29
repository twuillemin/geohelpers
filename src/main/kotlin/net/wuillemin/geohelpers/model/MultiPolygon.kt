package net.wuillemin.geohelpers.model

import com.github.salomonbrys.kotson.jsonArray
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonElement

/**
 * A MultiPolygon is a collection of Polygons, which may be non homogeneous
 */
open class MultiPolygon : MultiSurface {

    override val dimension get() = 2
    override val coordinateDimension get() = -1
    override val spatialDimension get() = -1

    constructor(geometries: List<Polygon>) : this(geometries, "MultiPolygon")

    constructor(geometries: List<Polygon>, geometryType: String) : super(geometries, geometryType)

    override fun generateAsGeoJsonObject(): JsonElement {
        return jsonObject(
                "type" to "MultiPolygon",
                "coordinates" to generateAsGeoJsonSubObject())
    }

    override fun generateAsGeoJsonSubObject(): JsonElement {
        return jsonArray(geometries.map { geometry -> geometry.generateAsGeoJsonSubObject() })
    }
}
