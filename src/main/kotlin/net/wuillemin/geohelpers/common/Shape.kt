package net.wuillemin.geohelpers.common

/**
 * A shape is just a collection of points. A shape is defined with no type as
 * the type is owned by the ShapeCollection
 */
data class Shape(val pts: List<SpatialPoint>) : AbstractShape(pts)
