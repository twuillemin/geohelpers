package net.wuillemin.geohelpers.model

import com.github.salomonbrys.kotson.jsonArray
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonElement

/**
 * A GeometryCollection is just a collection of Geometry, which may be non homogeneous
 */
open class GeometryCollection : Geometry {

    /**
     * The list of the objects in the collection
     */
    val geometries: List<Geometry>

    /**
     * Get the number of intrinsic dimensions of the object. As the list does not have a dimension
     * the number returned is either the number of dimensions of the objects in the list if they are
     * all equal, or -1 if the list is empty or references objects of different dimension
     */
    override val dimension get() = checkGeometriesDimensionCoherence()

    /**
     * Get the number of coordinate dimensions of the object. As the list does not have a coordinate dimension
     * the number returned is either the number of coordinate dimensions of the objects in the list if they are
     * all equal, or -1 if the list is empty or references objects of different coordinate dimension
     */
    override val coordinateDimension get() = checkGeometriesCoordinateCoherence()

    /**
     * Get the number of spatial dimensions of the object. As the list does not have a spatial dimension
     * the number returned is either the number of  spatial dimensions of the objects in the list if they are
     * all equal, or -1 if the list is empty or references objects of different spatial dimension
     */
    override val spatialDimension get() = checkGeometriesSpatialCoherence()

    /**
     * Constructor for GeometryCollection objects
     *
     * @param geometries the list of geometries to be referenced in the collection
     */
    constructor(geometries: List<Geometry>) : this(geometries, "GeometryCollection")

    /**
     * Constructor to be used by sub classes that allows to define the geometryType
     *
     * @param geometries the list of geometries to be referenced in the collection
     * @param geometryType the name of the geometry type, for example "MultiPolygon"
     */
    protected constructor(geometries: List<Geometry>, geometryType: String) : super(geometryType) {
        this.geometries = geometries
    }

    override fun generateAsGeoJsonObject(): JsonElement {
        return jsonObject(
                "type" to "GeometryCollection",
                "coordinates" to generateAsGeoJsonSubObject())
    }

    override fun generateAsGeoJsonSubObject(): JsonElement {
        return jsonArray(geometries.map { geometry -> geometry.generateAsGeoJsonSubObject() })
    }

    private fun checkGeometriesDimensionCoherence() : Int {

        if (this.geometries.isEmpty()) {
            return -1
        }

        // Check that all geometries have the same coordinateDimension
        val first: Geometry = geometries.first()
        if (geometries
                .filter({it.dimension != first.dimension})
                .isNotEmpty()) {
            // If not return -1
            return -1
        }

        return first.dimension
    }

    private fun checkGeometriesCoordinateCoherence() : Int {

        if (this.geometries.isEmpty()) {
            return -1
        }

        // Check that all geometries have the same coordinateDimension
        val first: Geometry = geometries.first()
        if (geometries
                .filter({it.coordinateDimension != first.coordinateDimension})
                .isNotEmpty()) {
            // If not return -1
            return -1
        }

        return first.coordinateDimension
    }

    private fun checkGeometriesSpatialCoherence() : Int {

        if (this.geometries.isEmpty()) {
            return -1
        }

        // Check that all geometries have the same coordinateDimension
        val first: Geometry = geometries.first()
        if (geometries
                .filter({it.spatialDimension != first.spatialDimension})
                .isNotEmpty()) {
            // If not return -1
            return -1
        }

        return first.spatialDimension
    }
}
