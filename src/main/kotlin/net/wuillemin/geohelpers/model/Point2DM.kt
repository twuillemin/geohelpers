package net.wuillemin.geohelpers.model

import com.github.salomonbrys.kotson.jsonArray
import com.google.gson.JsonElement

/**
 * A class representing a 2D point with a measurement
 */
data class Point2DM(val x: Double, val y: Double, val m: Double) : Point() {

    override val dimension get() = 0
    override val coordinateDimension get() = 3
    override val spatialDimension get() = 2

    override fun generateAsGeoJsonSubObject(): JsonElement = jsonArray(x, y, m)
}
