package net.wuillemin.geohelpers.wkt

import net.wuillemin.geohelpers.common.Shape
import net.wuillemin.geohelpers.common.ShapeType
import net.wuillemin.geohelpers.common.SpatialPoint
import java.io.File

/**
 * Reader for WKT file
 */

fun readWKT(file: File): List<Shape> {

    val content = file.readText(Charsets.US_ASCII).trim()
    return when {
        content.startsWith("POINT") -> readWKTPoint(content)
        content.startsWith("LINESTRING") -> readWKTPolyLine(content)
        content.startsWith("POLYGON") -> readWKTPolygon(content)
        content.startsWith("MULTIPOINT") -> readWKTMultiPoint(content)
        else -> throw AssertionError("Unknown format")
    }
}

/**
 * Read a point WKT file content
 * @param content The string to be read
 * @return a list of Shape, that will just contain one shape, the point
 */
private fun readWKTPoint(content: String): List<Shape> {

    val regexPoint = Regex("""POINT\s*\((.*)\s*\)""")

    val matchPointString = regexPoint.matchEntire(content) ?: throw AssertionError("Bad POINT format \"$content\"")

    val singlePoint = matchPointString.groups[1]?.value ?: throw AssertionError("No point in POINT \"$content\"")

    return listOf(Shape(listOf(toSpatialPoint(singlePoint)), ShapeType.point))
}

/**
 * Read a multi point WKT file content
 * @param content The string to be read
 * @return a list of Shape, that will contain one shape by point
 */
private fun readWKTMultiPoint(content: String): List<Shape> {

    val regexPoint = Regex("""MULTIPOINT\s*\(\s*(?:\(([\d\s\.]*)\)\s*\,\s*)*\(([\d\s\.]*)\)\s*\)\s*""")

    val matchPointString = regexPoint.matchEntire(content) ?: throw AssertionError("Bad MULTIPOINT format \"$content\"")

    var shapes = listOf<Shape>()
    for(i in 1..matchPointString.groups.size-1) {
        val textPoint = matchPointString.groups[i]?.value ?: throw AssertionError("Bad MULTIPOINT format \"$content\"")
        shapes += Shape(listOf(toSpatialPoint(textPoint)), ShapeType.point)
    }

    return shapes
}

/**
 * Read a polyline (polystring) WKT file content
 * @param content The string to be read
 * @return a list of Shape, that will just contain one shape, the polyline
 */
private fun readWKTPolyLine(content: String): List<Shape> {

    val regexLineString = Regex("""LINESTRING\s*\((.*)\s*\)""")

    val matchLineString = regexLineString.matchEntire(content) ?: throw AssertionError("Bad LINESTRING format \"$content\"")

    val singlePoints = matchLineString.groups[1]?.value?.split(",") ?: throw AssertionError("No points in LINESTRING \"$content\"")

    return listOf(Shape(singlePoints.map { singlePoint -> toSpatialPoint(singlePoint) }, ShapeType.polyline))
}

/**
 * Read a polygon (polygon) WKT file content
 * @param content The string to be read
 * @return a list of Shape, that will just contain one shape, the polygon
 */
private fun readWKTPolygon(content: String): List<Shape> {

    val regexLineString = Regex("""POLYGON\s*\(\s*\((.*)\s*\)\s*\)""")

    val matchLineString = regexLineString.matchEntire(content) ?: throw AssertionError("Bad POLYGON format \"$content\"")

    val singlePoints = matchLineString.groups[1]?.value?.split(",") ?: throw AssertionError("No points in POLYGON \"$content\"")

    val spatialPoints = singlePoints.map { singlePoint -> toSpatialPoint(singlePoint) }
    if ( (spatialPoints.first().x != spatialPoints.last().x) || (spatialPoints.first().y != spatialPoints.last().y) ) {
        throw AssertionError("Bad POLYGON format first points is different from last point in \"$content\"")
    }

    return listOf(Shape(spatialPoints, ShapeType.polygon))
}


// Define the regex for reading a spatial point
private val regexPoint = Regex("""\s*(\d+(?:\.\d*)?)\s+(\d+(?:\.\d*)?)\s*""")

/**
 * Convert a String holding a singe spatial point xxx.xxxx yyy.yyyy to a spatial point
 * @param str The string to read
 * @return a spatial point
 */
private fun toSpatialPoint(str: String): SpatialPoint {

    val matchPoint = regexPoint.matchEntire(str) ?: throw AssertionError("Unable to read point in \"$str\"")

    return SpatialPoint(
            matchPoint.groups[1]?.value?.toDouble() ?: 0.0,
            matchPoint.groups[2]?.value?.toDouble() ?: 0.0)
}

