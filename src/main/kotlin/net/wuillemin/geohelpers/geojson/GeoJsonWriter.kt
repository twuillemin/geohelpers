package net.wuillemin.geohelpers.geojson

import net.wuillemin.geohelpers.model.Geometry

/**
 * A Simple class for writing GeoJson Data. Supported data are: POINT, LINESTRING, POLYGON and MULTIPOINT
 */
class GeoJsonWriter {

    // Companion object for providing static access in java client
    companion object {

        /**
         * Static function for reading a WKT file
         * @param geometry The shape to write
         * @return A string with the shapes at the GeoJson format
         */
        @JvmStatic
        fun writeGeoJson(geometry: Geometry): String {
            return GeoJsonWriter().writeGeoJsonString(geometry)
        }
    }

    /**
     * Writer for a GeoJson String
     * @param geometry The shape to write
     * @return A string with the shapes at the GeoJson format
     */
    fun writeGeoJsonString(geometry: Geometry): String = geometry.generateAsGeoJsonObject().toString()
}
