package net.wuillemin.geohelpers.common

/**
 * A Simple class representing a LineString. A Line string must be composed of at least two points
 *
 * To specify a constraint specific to Polygons, it is useful to  introduce the concept of a linear ring:
 * * A linear ring is a closed LineString with four or more positions.
 * * The first and last positions are equivalent, and they MUST contain identical values; their representation
 *   SHOULD also be identical.
 * * linear ring is the boundary of a surface or the boundary of a hole in a surface.
 * * A linear ring MUST follow the right-hand rule with respect to the area it bounds, i.e., exterior rings are
 *   counterclockwise, and holes are clockwise.
 *
 * Created by Thomas on 20/05/2017.
 */
data class Polygon(val lines: List<LineString>) : Shape() {
    init {
        if (lines.isEmpty()) {
            throw AssertionError("Bad Polygon format - at least one line is needed")
        }
        if(!lines.filter { it.points.size < 4 }.isEmpty()) {
            throw AssertionError("Bad Polygon format - each line must have at least 4 points")
        }
        if(!lines.filter { it.points.first() !=  it.points.last() }.isEmpty()) {
            throw AssertionError("Bad Polygon format - each line must be closed")
        }
    }
}
