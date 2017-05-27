package net.wuillemin.geohelpers.common

/**
 * A Simple class representing a MultiLineString.
 * Created by Thomas on 20/05/2017.
 */
data class MultiLineString(val lines: List<LineString>) : Shape()
