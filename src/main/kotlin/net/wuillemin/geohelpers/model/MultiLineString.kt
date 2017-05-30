package net.wuillemin.geohelpers.model

import com.github.salomonbrys.kotson.jsonArray
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonElement

/**
 * A MultiLineString is a collection of LineStrings, which may be non homogeneous
 */
open class MultiLineString : MultiCurve {

    /**
     * Constructor for MultiLineString objects
     *
     * @param geometries the list of LineString to be referenced in the collection
     */
    constructor(geometries: List<LineString>) : super(geometries, "MultiLineString")

    override fun generateAsGeoJsonObject(): JsonElement {
        return jsonObject(
                "type" to "MultiLineString",
                "coordinates" to generateAsGeoJsonSubObject())
    }

    override fun generateAsGeoJsonSubObject(): JsonElement {
        return jsonArray(geometries.map { it.generateAsGeoJsonSubObject() })
    }
}
