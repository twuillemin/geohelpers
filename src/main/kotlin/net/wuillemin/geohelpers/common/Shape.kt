package net.wuillemin.geohelpers.common

/**
 * A shape is a geometric form
 */
enum class ShapeType {point, polygon, polyline}
data class Shape(val points: List<SpatialPoint>, val type: ShapeType)
