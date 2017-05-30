package net.wuillemin.geohelpers.wkt

import net.wuillemin.geohelpers.model.LineString
import net.wuillemin.geohelpers.model.LinearRing
import net.wuillemin.geohelpers.model.MultiLineString
import net.wuillemin.geohelpers.model.MultiPoint
import net.wuillemin.geohelpers.model.MultiPolygon
import net.wuillemin.geohelpers.model.Point3DM
import net.wuillemin.geohelpers.model.Polygon
import org.junit.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Tests for the WTKReader class
 */

class WktReaderTest3DM {

    /**
     * Test the read Point capability
     */
    @Test
    fun testPoint() {

        val pointFile = File(javaClass.classLoader.getResource("wkt\\3dm\\point.txt").toURI()).inputStream()

        val point = WktReader().readWKT(pointFile)
        assertEquals(Point3DM(30.0, 10.0, 10.0, 10.0), point)
    }

    /**
     * Test the read LineString capability
     */
    @Test
    fun testLineString() {

        val lineStringFile = File(javaClass.classLoader.getResource("wkt\\3dm\\linestring.txt").toURI()).inputStream()

        val line = WktReader().readWKT(lineStringFile)

        if (line is LineString) {
            assertEquals(3, line.vertices.size)
            assertEquals(Point3DM(30.0, 10.0, 10.0, 10.0), line.vertices[0])
            assertEquals(Point3DM(10.0, 30.0, 30.0, 30.0), line.vertices[1])
            assertEquals(Point3DM(40.0, 40.0, 40.0, 40.0), line.vertices[2])
        }
        else {
            assertTrue(line is LineString)
        }
    }

    /**
     * Test the read Polygon capability
     */
    @Test
    fun testPolygon() {

        val polygonFile = File(javaClass.classLoader.getResource("wkt\\3dm\\polygon.txt").toURI()).inputStream()

        val polygon = WktReader().readWKT(polygonFile)

        if (polygon is Polygon) {

            assertEquals(1, polygon.rings.size)

            val line = polygon.rings[0]
            if (line is LinearRing) {
                assertEquals(5, line.vertices.size)
                assertEquals(Point3DM(1.0, 1.0, 1.0, 1.0), line.vertices[0])
                assertEquals(Point3DM(5.0, 1.0, 1.0, 1.0), line.vertices[1])
                assertEquals(Point3DM(5.0, 5.0, 5.0, 5.0), line.vertices[2])
                assertEquals(Point3DM(1.0, 5.0, 5.0, 5.0), line.vertices[3])
                assertEquals(Point3DM(1.0, 1.0, 1.0, 1.0), line.vertices[4])
            }
            else {
                assertTrue(line is LineString)
            }
        }
        else {
            assertTrue(polygon is Polygon)
        }
    }

    /**
     * Test the read MultiPoint capability
     */
    @Test
    fun testMultiPoint() {

        val multiPointFile = File(javaClass.classLoader.getResource("wkt\\3dm\\multipoint.txt").toURI()).inputStream()

        val multiPoint = WktReader().readWKT(multiPointFile)

        if (multiPoint is MultiPoint) {
            assertEquals(2, multiPoint.geometries.size)
            assertEquals(Point3DM(3.5, 5.6, 5.6, 5.6), multiPoint.geometries[0])
            assertEquals(Point3DM(4.8, 10.5, 10.5, 10.5), multiPoint.geometries[1])
        }
        else {
            assertTrue(multiPoint is MultiPoint)
        }
    }

    /**
     * Test the read MultiString capability
     */
    @Test
    fun testMultiLineString() {

        val multiLineStringFile = File(javaClass.classLoader.getResource("wkt\\3dm\\multilinestring.txt").toURI()).inputStream()

        val multiLineString = WktReader().readWKT(multiLineStringFile)

        if (multiLineString is MultiLineString) {

            assertEquals(2, multiLineString.geometries.size)
            val line1 = multiLineString.geometries[0]
            if (line1 is LineString) {
                assertEquals(3, line1.vertices.size)
                assertEquals(Point3DM(3.0, 4.0, 4.0, 4.0), line1.vertices[0])
                assertEquals(Point3DM(10.0, 50.0, 50.0, 50.0), line1.vertices[1])
                assertEquals(Point3DM(20.0, 25.0, 25.0, 25.0), line1.vertices[2])
            }
            else {
                assertTrue(line1 is LineString)
            }

            val line2 = multiLineString.geometries[1]
            if (line2 is LineString) {
                assertEquals(3, line2.vertices.size)
                assertEquals(Point3DM(-5.0, -8.0, -8.0, -8.0), line2.vertices[0])
                assertEquals(Point3DM(-10.0, -8.0, -8.0, -8.0), line2.vertices[1])
                assertEquals(Point3DM(-15.0, -4.0, -4.0, -4.0), line2.vertices[2])
            }
            else {
                assertTrue(line2 is LineString)
            }
        }
        else {
            assertTrue(multiLineString is MultiLineString)
        }
    }

    /**
     * Test the read MultiPolygon capability
     */
    @Test
    fun testMultiPolygon() {

        val multiPolygonFile = File(javaClass.classLoader.getResource("wkt\\3dm\\multipolygon.txt").toURI()).inputStream()

        val multipolygon = WktReader().readWKT(multiPolygonFile)

        if (multipolygon is MultiPolygon) {

            assertEquals(2, multipolygon.geometries.size)

            val polygon1 = multipolygon.geometries[0]
            if (polygon1 is Polygon) {
                assertEquals(2, polygon1.rings.size)

                val line1 = polygon1.rings[0]
                if (line1 is LinearRing) {
                    assertEquals(5, line1.vertices.size)
                    assertEquals(Point3DM(1.0, 1.0, 1.0, 1.0), line1.vertices[0])
                    assertEquals(Point3DM(5.0, 1.0, 1.0, 1.0), line1.vertices[1])
                    assertEquals(Point3DM(5.0, 5.0, 5.0, 5.0), line1.vertices[2])
                    assertEquals(Point3DM(1.0, 5.0, 5.0, 5.0), line1.vertices[3])
                    assertEquals(Point3DM(1.0, 1.0, 1.0, 1.0), line1.vertices[4])
                }
                else {
                    assertTrue(line1 is LinearRing)
                }

                val line2 = polygon1.rings[1]
                if (line2 is LinearRing) {
                    assertEquals(5, line2.vertices.size)
                    assertEquals(Point3DM(2.0, 2.0, 2.0, 2.0), line2.vertices[0])
                    assertEquals(Point3DM(2.0, 3.0, 3.0, 3.0), line2.vertices[1])
                    assertEquals(Point3DM(3.0, 3.0, 3.0, 3.0), line2.vertices[2])
                    assertEquals(Point3DM(3.0, 2.0, 2.0, 2.0), line2.vertices[3])
                    assertEquals(Point3DM(2.0, 2.0, 2.0, 2.0), line2.vertices[4])
                }
                else {
                    assertTrue(line2 is LinearRing)
                }
            }
            else {
                assertTrue(polygon1 is Polygon)
            }

            val polygon2 = multipolygon.geometries[1]
            if (polygon2 is Polygon) {
                assertEquals(1, polygon2.rings.size)

                val line3 = polygon2.rings[0]
                if (line3 is LinearRing) {
                    assertEquals(4, line3.vertices.size)
                    assertEquals(Point3DM(6.0, 3.0, 3.0, 3.0), line3.vertices[0])
                    assertEquals(Point3DM(9.0, 2.0, 2.0, 2.0), line3.vertices[1])
                    assertEquals(Point3DM(9.0, 4.0, 4.0, 4.0), line3.vertices[2])
                    assertEquals(Point3DM(6.0, 3.0, 3.0, 3.0), line3.vertices[3])
                }
                else {
                    assertTrue(line3 is LinearRing)
                }
            }
            else {
                assertTrue(polygon1 is Polygon)
            }
        }
    }
}