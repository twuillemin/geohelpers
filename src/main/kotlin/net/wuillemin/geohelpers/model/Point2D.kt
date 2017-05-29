package net.wuillemin.geohelpers.model

import com.github.salomonbrys.kotson.jsonArray
import com.google.gson.JsonElement

/**
 * A class representing a 2D point
 */
data class Point2D(val x: Double, val y: Double) : Point() {

    /**
     * For 2D points, coordinateDimension is always 2 (X and Y)
     */
    final override val coordinateDimension get() = 2

    /**
     * For 2D points, spatialDimension is always 2 (X and Y)
     */
    override val spatialDimension get() = 2

    override fun generateAsGeoJsonSubObject(): JsonElement = jsonArray(x, y)
}