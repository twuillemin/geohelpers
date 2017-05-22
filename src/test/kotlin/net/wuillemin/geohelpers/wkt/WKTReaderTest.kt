package net.wuillemin.geohelpers.wkt

import net.wuillemin.geohelpers.common.ShapeType
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

        val shapeCollection = WKTReader.readWKT(pointFile)

        assertNotNull(shapeCollection)
        assertEquals(1, shapeCollection.shapes.size)
        assertEquals(ShapeType.point, shapeCollection.type)

        assertEquals(1, shapeCollection.shapes[0].points.size)
        assertEquals(30.0, shapeCollection.shapes[0].points[0].x)
        assertEquals(10.0, shapeCollection.shapes[0].points[0].y)
    }

    @Test
    fun testLineString() {

        //LINESTRING (30 10, 10 30, 40 40)
        val lineStringFile = File(javaClass.classLoader.getResource("wkt\\linestring.txt").toURI())

        val shapeCollection = WKTReader.readWKT(lineStringFile)

        assertNotNull(shapeCollection)
        assertEquals(1, shapeCollection.shapes.size)
        assertEquals(ShapeType.polyline, shapeCollection.type)

        assertEquals(3, shapeCollection.shapes[0].points.size)
        assertEquals(30.0, shapeCollection.shapes[0].points[0].x)
        assertEquals(10.0, shapeCollection.shapes[0].points[0].y)
        assertEquals(10.0, shapeCollection.shapes[0].points[1].x)
        assertEquals(30.0, shapeCollection.shapes[0].points[1].y)
        assertEquals(40.0, shapeCollection.shapes[0].points[2].x)
        assertEquals(40.0, shapeCollection.shapes[0].points[2].y)
    }

    @Test
    fun testPolygon() {

        //POLYGON((1 1,5 1,5 5,1 5,1 1))
        val polygonFile = File(javaClass.classLoader.getResource("wkt\\polygon.txt").toURI())

        val shapeCollection = WKTReader.readWKT(polygonFile)

        assertNotNull(shapeCollection)
        assertEquals(1, shapeCollection.shapes.size)
        assertEquals(ShapeType.polygon, shapeCollection.type)

        assertEquals(5, shapeCollection.shapes[0].points.size)
        assertEquals(1.0, shapeCollection.shapes[0].points[0].x)
        assertEquals(1.0, shapeCollection.shapes[0].points[0].y)
        assertEquals(5.0, shapeCollection.shapes[0].points[1].x)
        assertEquals(1.0, shapeCollection.shapes[0].points[1].y)
        assertEquals(5.0, shapeCollection.shapes[0].points[2].x)
        assertEquals(5.0, shapeCollection.shapes[0].points[2].y)
        assertEquals(1.0, shapeCollection.shapes[0].points[3].x)
        assertEquals(5.0, shapeCollection.shapes[0].points[3].y)
        assertEquals(1.0, shapeCollection.shapes[0].points[4].x)
        assertEquals(1.0, shapeCollection.shapes[0].points[4].y)
    }

    @Test
    fun testMultiPoint() {

        //MULTIPOINT((3.5 5.6), (4.8 10.5))
        val multiPointFile = File(javaClass.classLoader.getResource("wkt\\multipoint.txt").toURI())

        val shapeCollection = WKTReader.readWKT(multiPointFile)

        assertNotNull(shapeCollection)
        assertEquals(2, shapeCollection.shapes.size)
        assertEquals(ShapeType.multiPoint, shapeCollection.type)

        assertEquals(1, shapeCollection.shapes[0].points.size)
        assertEquals(3.5, shapeCollection.shapes[0].points[0].x)
        assertEquals(5.6, shapeCollection.shapes[0].points[0].y)

        assertEquals(1, shapeCollection.shapes[1].points.size)
        assertEquals(4.8, shapeCollection.shapes[1].points[0].x)
        assertEquals(10.5, shapeCollection.shapes[1].points[0].y)
    }

    @Test
    fun testMultiPolyline() {

        //MULTILINESTRING((3 4,10 50,20 25),(-5 -8,-10 -8,-15 -4))
        val multiPolyLineFile = File(javaClass.classLoader.getResource("wkt\\multipolyline.txt").toURI())

        val shapeCollection = WKTReader.readWKT(multiPolyLineFile)

        assertNotNull(shapeCollection)
        assertEquals(2, shapeCollection.shapes.size)
        assertEquals(ShapeType.multiPolyLine, shapeCollection.type)

        assertEquals(3, shapeCollection.shapes[0].points.size)
        assertEquals(3.0, shapeCollection.shapes[0].points[0].x)
        assertEquals(4.0, shapeCollection.shapes[0].points[0].y)
        assertEquals(10.0, shapeCollection.shapes[0].points[1].x)
        assertEquals(50.0, shapeCollection.shapes[0].points[1].y)
        assertEquals(20.0, shapeCollection.shapes[0].points[2].x)
        assertEquals(25.0, shapeCollection.shapes[0].points[2].y)

        assertEquals(3, shapeCollection.shapes[1].points.size)
        assertEquals(-5.0, shapeCollection.shapes[1].points[0].x)
        assertEquals(-8.0, shapeCollection.shapes[1].points[0].y)
        assertEquals(-10.0, shapeCollection.shapes[1].points[1].x)
        assertEquals(-8.0, shapeCollection.shapes[1].points[1].y)
        assertEquals(-15.0, shapeCollection.shapes[1].points[2].x)
        assertEquals(-4.0, shapeCollection.shapes[1].points[2].y)
    }

    @Test
    fun testMultiPolygon() {

        //MULTIPOLYGON(((1 1,5 1,5 5,1 5,1 1),(2 2,2 3,3 3,3 2,2 2)),((6 3,9 2,9 4,6 3)))
        val multiPolygonFile = File(javaClass.classLoader.getResource("wkt\\multipolygon.txt").toURI())

        val shapeCollection = WKTReader.readWKT(multiPolygonFile)

        assertNotNull(shapeCollection)
        assertEquals(3, shapeCollection.shapes.size)
        assertEquals(ShapeType.multiPolygon, shapeCollection.type)

        assertEquals(5, shapeCollection.shapes[0].points.size)
        assertEquals(1.0, shapeCollection.shapes[0].points[0].x)
        assertEquals(1.0, shapeCollection.shapes[0].points[0].y)
        assertEquals(5.0, shapeCollection.shapes[0].points[1].x)
        assertEquals(1.0, shapeCollection.shapes[0].points[1].y)
        assertEquals(5.0, shapeCollection.shapes[0].points[2].x)
        assertEquals(5.0, shapeCollection.shapes[0].points[2].y)
        assertEquals(1.0, shapeCollection.shapes[0].points[3].x)
        assertEquals(5.0, shapeCollection.shapes[0].points[3].y)
        assertEquals(1.0, shapeCollection.shapes[0].points[4].x)
        assertEquals(1.0, shapeCollection.shapes[0].points[4].y)

        assertEquals(5, shapeCollection.shapes[1].points.size)
        assertEquals(2.0, shapeCollection.shapes[1].points[0].x)
        assertEquals(2.0, shapeCollection.shapes[1].points[0].y)
        assertEquals(2.0, shapeCollection.shapes[1].points[1].x)
        assertEquals(3.0, shapeCollection.shapes[1].points[1].y)
        assertEquals(3.0, shapeCollection.shapes[1].points[2].x)
        assertEquals(3.0, shapeCollection.shapes[1].points[2].y)
        assertEquals(3.0, shapeCollection.shapes[1].points[3].x)
        assertEquals(2.0, shapeCollection.shapes[1].points[3].y)
        assertEquals(2.0, shapeCollection.shapes[1].points[4].x)
        assertEquals(2.0, shapeCollection.shapes[1].points[4].y)

        assertEquals(4, shapeCollection.shapes[2].points.size)
        assertEquals(6.0, shapeCollection.shapes[2].points[0].x)
        assertEquals(3.0, shapeCollection.shapes[2].points[0].y)
        assertEquals(9.0, shapeCollection.shapes[2].points[1].x)
        assertEquals(2.0, shapeCollection.shapes[2].points[1].y)
        assertEquals(9.0, shapeCollection.shapes[2].points[2].x)
        assertEquals(4.0, shapeCollection.shapes[2].points[2].y)
        assertEquals(6.0, shapeCollection.shapes[2].points[3].x)
        assertEquals(3.0, shapeCollection.shapes[2].points[3].y)
    }
}