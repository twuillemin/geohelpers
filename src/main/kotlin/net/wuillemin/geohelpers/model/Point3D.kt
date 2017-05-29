package net.wuillemin.geohelpers.model

import com.github.salomonbrys.kotson.jsonArray
import com.google.gson.JsonElement

/**
 * A class representing a 3D point
 */
data class Point3D(val x: Double, val y: Double, val z: Double) : Point() {

    override val dimension get() = 0
    override val coordinateDimension get() = 3
    override val spatialDimension get() = 3

    override fun generateAsGeoJsonSubObject(): JsonElement = jsonArray(x, y, z)
}
