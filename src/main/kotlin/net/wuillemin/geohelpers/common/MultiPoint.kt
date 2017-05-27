package net.wuillemin.geohelpers.common

/**
 * A Simple class representing a MultiPoint
 * Created by Thomas on 20/05/2017.
 */
data class MultiPoint(val points: List<Point>) : Shape()