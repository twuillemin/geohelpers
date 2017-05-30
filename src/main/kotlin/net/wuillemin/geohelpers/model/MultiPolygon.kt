package net.wuillemin.geohelpers.model

import com.github.salomonbrys.kotson.jsonArray
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonElement

/**
 * A MultiPolygon is a collection of Polygons, which may be non homogeneous
 */
open class MultiPolygon : MultiSurface {

    /**
     * Constructor for MultiPolygon objects
     *
     * @param geometries the list of Polygon to be referenced in the collection
     */
    constructor(geometries: List<Polygon>) : super(geometries, "MultiPolygon")

    override fun generateAsGeoJsonObject(): JsonElement {
        return jsonObject(
                "type" to "MultiPolygon",
                "coordinates" to generateAsGeoJsonSubObject())
    }

    override fun generateAsGeoJsonSubObject(): JsonElement {
        return jsonArray(geometries.map { it.generateAsGeoJsonSubObject() })
    }
}
