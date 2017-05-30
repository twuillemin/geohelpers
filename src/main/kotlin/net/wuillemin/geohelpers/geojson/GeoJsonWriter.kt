package net.wuillemin.geohelpers.geojson

import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonObject
import net.wuillemin.geohelpers.model.Geometry

/**
 * A Simple class for writing GeoJson Data.
 */
class GeoJsonWriter {

    /**
     * Generate a Json object containing a single GeoJsonFeature, the feature is defined by the given
     * geometry
     * @param geometry The geometry to generate
     * @return  A Json object
     */
    fun writeGeometryGeoJsonString(geometry: Geometry): JsonObject {
        return jsonObject(
                "type" to "Feature",
                "geometry" to geometry.generateAsGeoJsonObject())
    }
}
