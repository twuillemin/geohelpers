package net.wuillemin.geohelpers.model

import com.github.salomonbrys.kotson.jsonArray
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonElement

/**
 * A GeometryCollection is just a collection of Geometry, which may be non homogeneous
 */
open class GeometryCollection : Geometry {

    val geometries: List<Geometry>

    override val dimension get() = -1
    override val coordinateDimension get() = -1
    override val spatialDimension get() = -1

    constructor(geometries: List<Geometry>) : this(geometries, "GeometryCollection")

    constructor(geometries: List<Geometry>, geometryType: String) : super(geometryType) {
        this.geometries = geometries
    }

    override fun generateAsGeoJsonObject(): JsonElement {
        return jsonObject(
                "type" to "GeometryCollection",
                "coordinates" to generateAsGeoJsonSubObject())
    }

    override fun generateAsGeoJsonSubObject(): JsonElement {
        return jsonArray(geometries.map { geometry -> geometry.generateAsGeoJsonSubObject() })
    }
}
