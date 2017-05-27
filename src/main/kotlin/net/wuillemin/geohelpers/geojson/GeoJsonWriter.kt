package net.wuillemin.geohelpers.geojson

import com.github.salomonbrys.kotson.jsonArray
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonObject
import net.wuillemin.geohelpers.common.LineString
import net.wuillemin.geohelpers.common.Point
import net.wuillemin.geohelpers.common.Polygon
import net.wuillemin.geohelpers.common.Shape

/**
 * A Simple class for writing GeoJson Data. Supported data are: POINT, LINESTRING, POLYGON and MULTIPOINT
 */
class GeoJsonWriter {

    // Companion object for providing static access in java client
    companion object {

        /**
         * Static function for reading a WKT file
         * @param shape The shape to write
         * @return A string with the shapes at the GeoJson format
         */
        @JvmStatic
        fun writeGeoJson(shape: Shape): String {
            return GeoJsonWriter().writeGeoJsonString(shape)
        }
    }

    /**
     * Writer for a GeoJson String
     * @param shape The shape to write
     * @return A string with the shapes at the GeoJson format
     */
    fun writeGeoJsonString(shape: Shape): String = when (shape) {
        is Point      -> generatePoint(shape)
        is LineString -> generateLineString(shape)
        is Polygon    -> generatePolygon(shape)
        else          -> ""
    }.toString()

    private fun generatePoint(point: Point): JsonObject {
        return jsonObject(
                "type" to "Point",
                "coordinates" to jsonArray(point.x, point.y))
    }

    private fun generateLineString(lineString: LineString): JsonObject {
        return jsonObject(
                "type" to "MultiPoint",
                "coordinates" to jsonArray(
                        lineString.points.map {
                            point ->
                            jsonArray(point.x, point.y)
                        }))
    }

    private fun generatePolygon(polygon: Polygon): JsonObject {
        return jsonObject(
                "type" to "Polygon",
                "coordinates" to jsonArray(
                        polygon.lines.map {
                            line ->
                            jsonArray(line.points.map {
                                point ->
                                jsonArray(point.x, point.y)
                            })
                        }))
    }
}
