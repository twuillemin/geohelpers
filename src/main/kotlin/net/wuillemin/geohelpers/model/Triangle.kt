package net.wuillemin.geohelpers.model

/**
 * Class for triangle polygons. All linear rings must have four vertices (points)
 * In case they are not, an AssertionError is thrown
 */
class Triangle(rings: List<LinearRing>) : Polygon(rings, "Triangle") {

    init {
        if (rings.any { it.vertices.size != 4 }) {
            throw AssertionError("Bad Triangle format - a LinearRing with just 4 points is needed")
        }
    }
}