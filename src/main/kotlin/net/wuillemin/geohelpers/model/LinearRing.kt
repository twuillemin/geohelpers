package net.wuillemin.geohelpers.model

/**
 * A class representing a LinearRing. A LinearRing must have its first and its last point equal
 */
class LinearRing(vertices: List<Point>) : LineString(vertices, "LinearRing") {

    init {
        if (vertices.first() != vertices.last()) {
            throw AssertionError("Bad LinearRing format - first and last point must be equal")
        }
    }
}
