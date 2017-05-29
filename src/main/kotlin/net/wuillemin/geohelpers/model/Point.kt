package net.wuillemin.geohelpers.model

import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonElement

/**
 * Abstract class for all Points
 */
abstract class Point : Geometry("Point") {

    override val dimension get() = 0

    override fun generateAsGeoJsonObject(): JsonElement {
        return jsonObject(
                "type" to "Point",
                "coordinates" to generateAsGeoJsonSubObject())
    }
}
