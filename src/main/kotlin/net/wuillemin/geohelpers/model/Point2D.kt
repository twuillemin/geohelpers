package net.wuillemin.geohelpers.model

import com.github.salomonbrys.kotson.jsonArray
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonElement

/**
 * A class representing a 2D point
 */
data class Point2D(val x: Double, val y: Double) : Point() {

    override val dimension get() = 0
    override val coordinateDimension get() = 2
    override val spatialDimension get() = 2

    override fun generateAsGeoJsonSubObject(): JsonElement = jsonArray(x, y)
}