package net.wuillemin.geohelpers.model

/**
 * Abstract class for all Triangles
 */
class Triangle(rings: List<LinearRing>) : Polygon(rings, "Triangle") {

    init {
        if (rings.filter { it.vertices.size != 4 }.isNotEmpty()) {
            throw AssertionError("Bad Triangle format - a LinearRing with just 4 points is needed")
        }
    }
}