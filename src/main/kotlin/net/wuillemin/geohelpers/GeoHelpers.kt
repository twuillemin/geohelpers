package net.wuillemin.geohelpers

import com.google.gson.JsonObject
import net.wuillemin.geohelpers.geojson.GeoJsonWriter
import net.wuillemin.geohelpers.wkt.WktReader
import java.io.InputStream

/**
 * GeoHelpers class, providing simple access to underlying component
 */
class GeoHelpers {

    /**
     * Convert a WKT formatted string to a GeoJson object
     * @param str The string containing the Geometry to be converted
     * @return A GeoJson object correctly formatted
     */
    fun wktToGeoJson(str: String): JsonObject = GeoJsonWriter().writeGeometryGeoJsonString(WktReader().readWKT(str))

    /**
     * Convert a WKT formatted string to a GeoJson object
     * @param stream The stream containing the Geometry to be converted
     * @return A GeoJson object correctly formatted
     */
    fun wktToGeoJson(stream: InputStream): JsonObject = GeoJsonWriter().writeGeometryGeoJsonString(WktReader().readWKT(stream))
}
