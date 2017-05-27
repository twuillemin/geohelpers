package net.wuillemin.geohelpers.common

/**
 * A Simple class representing a LineString. A Line string must be composed of at least two points
 * Created by Thomas on 20/05/2017.
 */
data class LineString(val points: List<Point>) : Shape() {
    init {
        if (points.size < 2) {
            throw AssertionError("Bad LineString format - at least two points are needed")
        }
    }
}
