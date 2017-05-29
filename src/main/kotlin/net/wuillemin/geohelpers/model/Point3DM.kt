package net.wuillemin.geohelpers.model

import com.github.salomonbrys.kotson.jsonArray
import com.google.gson.JsonElement

/**
 * A class representing a 3D point with a measurement
 */
data class Point3DM(val x: Double, val y: Double, val z: Double, val m: Double) : Point() {

    override val dimension get() = 0
    override val coordinateDimension get() = 4
    override val spatialDimension get() = 3

    override fun generateAsGeoJsonSubObject(): JsonElement = jsonArray(x, y, z, m)
}
