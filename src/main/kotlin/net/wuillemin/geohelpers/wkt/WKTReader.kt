package net.wuillemin.geohelpers.wkt

import net.wuillemin.geohelpers.common.Shape
import net.wuillemin.geohelpers.common.ShapeCollection
import net.wuillemin.geohelpers.common.ShapeType
import net.wuillemin.geohelpers.common.SpatialPoint
import java.io.File

/**
 * A Simple class for reading WKT Data. Supported data are: POINT, LINESTRING, POLYGON and MULTIPOINT
 */
class WKTReader {

    // Companion object for providing static access in java client
    companion object {

        /**
         * Static function for reading a WKT file
         * @param file The file to read. Will be read as an ASCII file
         * @return A list of the shapes in the file
         */
        @JvmStatic
        fun readWKT(file: File): ShapeCollection {
            return WKTReader().readWKTFile(file)
        }

        /**
         * Static function for reading a WKT file
         * @param str The WKT data to read
         * @return A list of the shapes in the file
         */
        @JvmStatic
        fun readWKT(str: String): ShapeCollection {
            return WKTReader().readWKTString(str)
        }
    }

    /**
     * Reader for WKT file
     * @param file The file to read. Will be read as an ASCII file
     * @return A list of the shapes in the file
     */
    fun readWKTFile(file: File): ShapeCollection {
        return readWKT(file.readText(Charsets.US_ASCII))
    }

    /**
     * Reader for WKT string
     * @param str The WKT data to read
     * @return A list of the shapes in the file
     */
    fun readWKTString(str: String): ShapeCollection {
        val content = str.trim()
        return when {
            content.startsWith("POINT") -> readWKTPoint(content)
            content.startsWith("LINESTRING") -> readWKTPolyline(content)
            content.startsWith("POLYGON") -> readWKTPolygon(content)
            content.startsWith("MULTIPOINT") -> readWKTMultiPoint(content)
            content.startsWith("MULTILINESTRING") -> readWKTMultiPolyline(content)
            content.startsWith("MULTIPOLYGON") -> readWKTMultiPolygon(content)
            else -> throw AssertionError("Unknown format")
        }
    }

    /**
     * Read a point WKT file content
     * @param content The string to be read
     * @return a list of Shape, that will just contain one shape, the point
     */
    private fun readWKTPoint(content: String): ShapeCollection {

        val regexPoint = Regex("""POINT\s*\((.*)\s*\)""")

        val matchPointString = regexPoint.matchEntire(content) ?: throw AssertionError("Bad POINT format \"$content\"")

        val singlePoint = matchPointString.groups[1]?.value ?: throw AssertionError("No point in POINT \"$content\"")

        return ShapeCollection(
                listOf(Shape(listOf(toSpatialPoint(singlePoint)))),
                ShapeType.point)
    }

    /**
     * Read a multi point WKT file content
     * @param content The string to be read
     * @return a list of Shape, that will contain one shape by point
     */
    private fun readWKTMultiPoint(content: String): ShapeCollection {

        val regexMultiPoint = Regex("""MULTIPOINT\s*\(\s*(?:\(([\d\s.-]*)\)\s*,\s*)*\(([\d\s.-]*)\)\s*\)\s*""")

        val matchPointString = regexMultiPoint.matchEntire(content) ?: throw AssertionError("Bad MULTIPOINT format \"$content\"")

        var shapes = listOf<Shape>()
        for (i in 1..matchPointString.groups.size - 1) {
            val textPoint = matchPointString.groups[i]?.value ?: throw AssertionError("Bad MULTIPOINT format \"$content\"")
            shapes += Shape(listOf(toSpatialPoint(textPoint)))
        }

        return ShapeCollection(shapes, ShapeType.multiPoint)
    }

    /**
     * Read a polyline (polystring) WKT file content
     * @param content The string to be read
     * @return a list of Shape, that will just contain one shape, the polyline
     */
    private fun readWKTPolyline(content: String): ShapeCollection {

        val regexLineString = Regex("""LINESTRING\s*\((.*)\s*\)""")

        val matchLineString = regexLineString.matchEntire(content) ?: throw AssertionError("Bad LINESTRING format \"$content\"")

        val singlePoints = matchLineString.groups[1]?.value?.split(",") ?: throw AssertionError("No points in LINESTRING \"$content\"")

        return ShapeCollection(
                listOf(Shape(singlePoints.map { singlePoint -> toSpatialPoint(singlePoint) })),
                ShapeType.polyline)
    }

    /**
     * Read a multi polyline WKT file content
     * @param content The string to be read
     * @return a list of Shape, that will contain one shape by polyline
     */
    private fun readWKTMultiPolyline(content: String): ShapeCollection {

        val regexMultiLine = Regex("""MULTILINESTRING\s*\(\s*(?:\(([\d\s.,-]*)\)\s*,\s*)*\(([\d\s.,-]*)\)\s*\)\s*""")

        val matchMultiLine = regexMultiLine.matchEntire(content) ?: throw AssertionError("Bad MULTILINESTRING format \"$content\"")

        // For each polyline
        var shapes = listOf<Shape>()
        for (i in 1..matchMultiLine.groups.size - 1) {

            // Extract the point
            val singlePoints = matchMultiLine.groups[i]?.value?.split(",") ?: throw AssertionError("No points in MULTILINESTRING \"$content\"")

            // Add the new shape to the list
            shapes += Shape(singlePoints.map { singlePoint -> toSpatialPoint(singlePoint) })
        }

        return ShapeCollection(shapes, ShapeType.multiPolyLine)
    }


    /**
     * Read a polygon (polygon) WKT file content
     * @param content The string to be read
     * @return a list of Shape, that will just contain one shape, the polygon
     */
    private fun readWKTPolygon(content: String): ShapeCollection {

        val regexLineString = Regex("""POLYGON\s*\(\s*\((.*)\s*\)\s*\)""")

        val matchLineString = regexLineString.matchEntire(content) ?: throw AssertionError("Bad POLYGON format \"$content\"")

        val singlePoints = matchLineString.groups[1]?.value?.split(",") ?: throw AssertionError("No points in POLYGON \"$content\"")

        val spatialPoints = singlePoints.map { singlePoint -> toSpatialPoint(singlePoint) }
        if ((spatialPoints.first().x != spatialPoints.last().x) || (spatialPoints.first().y != spatialPoints.last().y)) {
            throw AssertionError("Bad POLYGON format first points is different from last point in \"$content\"")
        }

        return ShapeCollection(
                listOf(Shape(spatialPoints)),
                ShapeType.polygon)
    }

    /**
     * Read a multi polygon WKT file content
     * @param content The string to be read
     * @return a list of Shape, that will contain one shape by polyline
     */
    private fun readWKTMultiPolygon(content: String): ShapeCollection {

        val regexMultiPolygon = Regex("""MULTIPOLYGON\s*\(\s*(?:\(([\d\s.,-]*)\)\s*,\s*)*\(([\d\s.,-]*)\)\s*\)\s*""")

        val matchMultiPolygon = regexMultiPolygon.matchEntire(content) ?: throw AssertionError("Bad MULTIPOLYGON format \"$content\"")

        // For each polyline
        var shapes = listOf<Shape>()
        for (i in 1..matchMultiPolygon.groups.size - 1) {

            // Extract the points of the polygon
            val singlePoints = matchMultiPolygon.groups[1]?.value?.split(",") ?: throw AssertionError("No points in MULTIPOLYGON \"$content\"")

            // Convert the text points to spatial points and check if the last one is the first one
            val spatialPoints = singlePoints.map { singlePoint -> toSpatialPoint(singlePoint) }
            if ((spatialPoints.first().x != spatialPoints.last().x) || (spatialPoints.first().y != spatialPoints.last().y)) {
                throw AssertionError("Bad MULTIPOLYGON format first points is different from last point in \"$content\"")
            }

            // Add the new shape to the list
            shapes += Shape(singlePoints.map { singlePoint -> toSpatialPoint(singlePoint) })
        }

        return ShapeCollection(shapes, ShapeType.multiPolygon)
    }

    /**
     * Convert a String holding a singe spatial point xxx.xxxx yyy.yyyy to a spatial point
     * @param str The string to read
     * @return a spatial point
     */
    private fun toSpatialPoint(str: String): SpatialPoint {

        val regexPoint = Regex("""\s*(\s*-?\s*\d+(?:\.\d*)?)\s+(\s*-?\s*\d+(?:\.\d*)?)\s*""")

        val matchPoint = regexPoint.matchEntire(str) ?: throw AssertionError("Unable to read point in \"$str\"")

        return SpatialPoint(
                matchPoint.groups[1]?.value?.toDouble() ?: 0.0,
                matchPoint.groups[2]?.value?.toDouble() ?: 0.0)
    }

    val twoNumbers = """\s*(\s*-?\s*\d+(?:\.\d*)?)\s+(\s*-?\s*\d+(?:\.\d*)?)\s*"""
    val twoNumbersList = "(?:$twoNumbers\\s*,\\s*)$twoNumbers"

    val capturePoint = "\\(($twoNumbers)\\)"
    val capturePointsList = "(?:$capturePoint\\s*,\\s*)$capturePoint"

    val captureTwoNumbersList = "\\(($twoNumbersList)\\)"
    val captureTwoNumbersListsList = "(?:$captureTwoNumbersList\\s*,\\s*)$captureTwoNumbersList"

}

