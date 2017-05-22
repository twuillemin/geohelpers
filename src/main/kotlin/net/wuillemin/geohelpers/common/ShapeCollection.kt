package net.wuillemin.geohelpers.common

/**
 * A ShapeCollection is a collection of shape. The ShapeCollection is typed, so that
 * every shape in the collection is to be interpreted by the same type
 */
data class ShapeCollection(val shapes: List<AbstractShape>, val type: ShapeType)



