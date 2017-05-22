package net.wuillemin.geohelpers.geojson

import com.github.salomonbrys.kotson.jsonArray
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonArray
import net.wuillemin.geohelpers.common.ShapeCollection
import net.wuillemin.geohelpers.common.ShapeType
import net.wuillemin.geohelpers.common.SpatialPoint

/**
 * A Simple class for writing GeoJson Data. Supported data are: POINT, LINESTRING, POLYGON and MULTIPOINT
 */
class GeoJsonWriter {

    // Companion object for providing static access in java client
    companion object {

        /**
         * Static function for reading a WKT file
         * @param shapeCollection The list of Shape to write
         * @return A string with the shapes at the GeoJson format
         */
        @JvmStatic
        fun writeGeoJson(shapeCollection: ShapeCollection): String {
            return GeoJsonWriter().writeGeoJsonString(shapeCollection)
        }
    }

    /**
     * Writer for a GeoJson String
     * @param shapeCollection The list of Shape to write
     * @return A string with the shapes at the GeoJson format
     */
    fun writeGeoJsonString(shapeCollection: ShapeCollection): String {

        return jsonObject(
                "type" to shapeTypeToGeoJsonType(shapeCollection.type),
                "coordinates" to when (shapeCollection.type) {
                            ShapeType.point -> pointToJson(shapeCollection.shapes[0].points[0])
                            ShapeType.polyline -> jsonArray(shapeCollection.shapes[0].points.map { p -> pointToJson(p) })
                            ShapeType.polygon -> jsonArray(jsonArray(shapeCollection.shapes[0].points.map { p -> pointToJson(p) }))
                            ShapeType.multiPoint -> jsonArray(shapeCollection.shapes.map { s -> pointToJson(s.points[0]) })
                            else -> null
                        }).toString()
    }

    /**
     * Simple helper for converting a point to a JsonArray
     * @param point The point to convert
     * @result a JsonArray with the coordinates x and y as [x y]
     */
    private fun pointToJson(point: SpatialPoint): JsonArray {
        return jsonArray(point.x, point.y)
    }

    /**
     * Convert the ShapeType to the string value compatible with GeoJson
     */
    private fun shapeTypeToGeoJsonType(type: ShapeType): String {
        return when (type) {
            ShapeType.point -> "Point"
            ShapeType.multiPoint -> "MultiPoint"
            ShapeType.polyline -> "LineString"
            ShapeType.multiPolyLine -> "MultiLineString"
            ShapeType.polygon -> "Polygon"
            ShapeType.multiPolygon -> "MultiPolygon"
        }
    }
}
