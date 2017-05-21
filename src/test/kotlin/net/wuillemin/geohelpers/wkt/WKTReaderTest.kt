package net.wuillemin.geohelpers.wkt

import org.junit.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

/**
 * Tests for the WTKReader class
 */

class WKTReaderTest {

    @Test
    fun testPoint() {

        // POINT (30 10)
        val pointFile = File(javaClass.classLoader.getResource("wkt\\point.txt").toURI())

        val shape = readWKT(pointFile)

        assertNotNull(shape)
        assertEquals(1,shape.size)
        assertEquals(1,shape[0].points.size)
        assertEquals(30.0, shape[0].points[0].x)
        assertEquals(10.0, shape[0].points[0].y)
    }

    @Test
    fun testLineString() {

        //LINESTRING (30 10, 10 30, 40 40)
        val lineStringFile = File(javaClass.classLoader.getResource("wkt\\linestring.txt").toURI())

        val shape = readWKT(lineStringFile)

        assertNotNull(shape)
        assertEquals(1,shape.size)
        assertEquals(3,shape[0].points.size)
        assertEquals(30.0, shape[0].points[0].x)
        assertEquals(10.0, shape[0].points[0].y)
        assertEquals(10.0, shape[0].points[1].x)
        assertEquals(30.0, shape[0].points[1].y)
        assertEquals(40.0, shape[0].points[2].x)
        assertEquals(40.0, shape[0].points[2].y)
    }

    @Test
    fun testPolygon() {

        //POLYGON((1 1,5 1,5 5,1 5,1 1))
        val polygonFile = File(javaClass.classLoader.getResource("wkt\\polygon.txt").toURI())

        val shape = readWKT(polygonFile)

        assertNotNull(shape)
        assertEquals(1,shape.size)
        assertEquals(5,shape[0].points.size)
        assertEquals(1.0, shape[0].points[0].x)
        assertEquals(1.0, shape[0].points[0].y)
        assertEquals(5.0, shape[0].points[1].x)
        assertEquals(1.0, shape[0].points[1].y)
        assertEquals(5.0, shape[0].points[2].x)
        assertEquals(5.0, shape[0].points[2].y)
        assertEquals(1.0, shape[0].points[3].x)
        assertEquals(5.0, shape[0].points[3].y)
        assertEquals(1.0, shape[0].points[4].x)
        assertEquals(1.0, shape[0].points[4].y)
    }

    @Test
    fun testMultiPoint() {

        //MULTIPOINT((3.5 5.6), (4.8 10.5))
        val multiPointFile = File(javaClass.classLoader.getResource("wkt\\multipoint.txt").toURI())

        val shape = readWKT(multiPointFile)

        assertNotNull(shape)
        assertEquals(2,shape.size)
        assertEquals(1,shape[0].points.size)
        assertEquals(3.5, shape[0].points[0].x)
        assertEquals(5.6, shape[0].points[0].y)
        assertEquals(1,shape[1].points.size)
        assertEquals(4.8, shape[1].points[0].x)
        assertEquals(10.5, shape[1].points[0].y)
    }
}