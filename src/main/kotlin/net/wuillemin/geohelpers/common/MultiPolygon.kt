package net.wuillemin.geohelpers.common

/**
 * A Simple class representing a MultiPolygon
 *
 * Created by Thomas on 20/05/2017.
 */
data class MultiPolygon(val polygons: List<Polygon>) : Shape()
