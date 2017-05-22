package net.wuillemin.geohelpers.common

/**
 * A shape composed of two list of points, the first one for the shape itself, and the second one for the hole
 */
data class HoleShape(val pts: List<SpatialPoint>, val hole: List<SpatialPoint>) : AbstractShape(pts)
