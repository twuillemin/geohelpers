package net.wuillemin.geohelpers.wkt

import net.wuillemin.geohelpers.common.LineString
import net.wuillemin.geohelpers.common.MultiLineString
import net.wuillemin.geohelpers.common.MultiPoint
import net.wuillemin.geohelpers.common.MultiPolygon
import net.wuillemin.geohelpers.common.Point
import net.wuillemin.geohelpers.common.Polygon
import org.junit.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Tests for the WTKReader class
 */

class WktReaderTest {

    @Test
    fun testPoint() {

        // POINT (30 10)
        val pointFile = File(javaClass.classLoader.getResource("wkt\\point.txt").toURI()).inputStream()

        val point = WktReader.readWKT(pointFile)

        if (point is Point) {
            assertEquals(30.0, point.x)
            assertEquals(10.0, point.y)
        }
        else {
            assertTrue(point is Point)
        }
    }

    @Test
    fun testLineString() {

        //LINESTRING (30 10, 10 30, 40 40)
        val lineStringFile = File(javaClass.classLoader.getResource("wkt\\linestring.txt").toURI()).inputStream()

        val line = WktReader.readWKT(lineStringFile)

        if (line is LineString) {
            assertEquals(3, line.points.size)
            assertEquals(30.0, line.points[0].x)
            assertEquals(10.0, line.points[0].y)
            assertEquals(10.0, line.points[1].x)
            assertEquals(30.0, line.points[1].y)
            assertEquals(40.0, line.points[2].x)
            assertEquals(40.0, line.points[2].y)
        }
        else {
            assertTrue(line is LineString)
        }
    }

    @Test
    fun testPolygon() {

        //POLYGON((1 1,5 1,5 5,1 5,1 1))
        val polygonFile = File(javaClass.classLoader.getResource("wkt\\polygon.txt").toURI()).inputStream()

        val polygon = WktReader.readWKT(polygonFile)

        if (polygon is Polygon) {

            assertEquals(1, polygon.lines.size)

            val line = polygon.lines[0]
            if (line is LineString) {
                assertEquals(5, line.points.size)
                assertEquals(1.0, line.points[0].x)
                assertEquals(1.0, line.points[0].y)
                assertEquals(5.0, line.points[1].x)
                assertEquals(1.0, line.points[1].y)
                assertEquals(5.0, line.points[2].x)
                assertEquals(5.0, line.points[2].y)
                assertEquals(1.0, line.points[3].x)
                assertEquals(5.0, line.points[3].y)
                assertEquals(1.0, line.points[4].x)
                assertEquals(1.0, line.points[4].y)
            }
            else {
                assertTrue(line is LineString)
            }
        }
        else {
            assertTrue(polygon is Polygon)
        }
    }

    @Test
    fun testMultiPoint() {

        //MULTIPOINT((3.5 5.6), (4.8 10.5))
        val multiPointFile = File(javaClass.classLoader.getResource("wkt\\multipoint.txt").toURI()).inputStream()

        val multiPoint = WktReader.readWKT(multiPointFile)

        if (multiPoint is MultiPoint) {

            assertEquals(2, multiPoint.points.size)

            val point1 = multiPoint.points[0]
            assertEquals(3.5, point1.x)
            assertEquals(5.6, point1.y)

            val point2 = multiPoint.points[1]
            assertEquals(4.8, point2.x)
            assertEquals(10.5, point2.y)
        }
        else {
            assertTrue(multiPoint is MultiPoint)
        }
    }

    @Test
    fun testMultiLineString() {

        //MULTILINESTRING((3 4,10 50,20 25),(-5 -8,-10 -8,-15 -4))
        val multiLineStringFile = File(javaClass.classLoader.getResource("wkt\\multilinestring.txt").toURI()).inputStream()

        val multiLineString = WktReader.readWKT(multiLineStringFile)

        if (multiLineString is MultiLineString) {

            assertEquals(2, multiLineString.lines.size)

            val line1 = multiLineString.lines[0]
            assertEquals(3, line1.points.size)
            assertEquals(3.0, line1.points[0].x)
            assertEquals(4.0, line1.points[0].y)
            assertEquals(10.0, line1.points[1].x)
            assertEquals(50.0, line1.points[1].y)
            assertEquals(20.0, line1.points[2].x)
            assertEquals(25.0, line1.points[2].y)

            val line2 = multiLineString.lines[1]
            assertEquals(3, line2.points.size)
            assertEquals(-5.0, line2.points[0].x)
            assertEquals(-8.0, line2.points[0].y)
            assertEquals(-10.0, line2.points[1].x)
            assertEquals(-8.0, line2.points[1].y)
            assertEquals(-15.0, line2.points[2].x)
            assertEquals(-4.0, line2.points[2].y)
        }
        else {
            assertTrue(multiLineString is MultiLineString)
        }
    }

    @Test
    fun testMultiPolygon() {

        //MULTIPOLYGON(((1 1,5 1,5 5,1 5,1 1),(2 2,2 3,3 3,3 2,2 2)),((6 3,9 2,9 4,6 3)))
        val multiPolygonFile = File(javaClass.classLoader.getResource("wkt\\multipolygon.txt").toURI()).inputStream()

        val multipolygon = WktReader.readWKT(multiPolygonFile)

        if (multipolygon is MultiPolygon) {

            assertEquals(2, multipolygon.polygons.size)

            val polygon1 = multipolygon.polygons[0]
            assertEquals(2, polygon1.lines.size)

            val line1 = polygon1.lines[0]
            assertEquals(5, line1.points.size)
            assertEquals(1.0, line1.points[0].x)
            assertEquals(1.0, line1.points[0].y)
            assertEquals(5.0, line1.points[1].x)
            assertEquals(1.0, line1.points[1].y)
            assertEquals(5.0, line1.points[2].x)
            assertEquals(5.0, line1.points[2].y)
            assertEquals(1.0, line1.points[3].x)
            assertEquals(5.0, line1.points[3].y)
            assertEquals(1.0, line1.points[4].x)
            assertEquals(1.0, line1.points[4].y)

            val line2 = polygon1.lines[1]
            assertEquals(5, line2.points.size)
            assertEquals(2.0, line2.points[0].x)
            assertEquals(2.0, line2.points[0].y)
            assertEquals(2.0, line2.points[1].x)
            assertEquals(3.0, line2.points[1].y)
            assertEquals(3.0, line2.points[2].x)
            assertEquals(3.0, line2.points[2].y)
            assertEquals(3.0, line2.points[3].x)
            assertEquals(2.0, line2.points[3].y)
            assertEquals(2.0, line2.points[4].x)
            assertEquals(2.0, line2.points[4].y)

            val polygon2 = multipolygon.polygons[1]
            assertEquals(1, polygon2.lines.size)

            val line3 = polygon2.lines[0]
            assertEquals(4, line3.points.size)
            assertEquals(6.0, line3.points[0].x)
            assertEquals(3.0, line3.points[0].y)
            assertEquals(9.0, line3.points[1].x)
            assertEquals(2.0, line3.points[1].y)
            assertEquals(9.0, line3.points[2].x)
            assertEquals(4.0, line3.points[2].y)
            assertEquals(6.0, line3.points[3].x)
            assertEquals(3.0, line3.points[3].y)
        }
        else {
            assertTrue(multipolygon is MultiPolygon)
        }
    }
}