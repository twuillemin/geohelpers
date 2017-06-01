package net.wuillemin.geohelpers.wkt

/**
 * Tests for the WTKReader class
 */

class WktReader3DMTest {

    /**
     * Test the read Point capability
     */
    @org.junit.Test
    fun testPoint() {

        val pointFile = java.io.File(javaClass.classLoader.getResource("wkt\\3dm\\point.txt").toURI()).inputStream()

        val point = net.wuillemin.geohelpers.wkt.WktReader().readWKT(pointFile)
        kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(30.0, 10.0, 10.0, 10.0), point)
    }

    /**
     * Test the read LineString capability
     */
    @org.junit.Test
    fun testLineString() {

        val lineStringFile = java.io.File(javaClass.classLoader.getResource("wkt\\3dm\\linestring.txt").toURI()).inputStream()

        val line = net.wuillemin.geohelpers.wkt.WktReader().readWKT(lineStringFile)

        if (line is net.wuillemin.geohelpers.model.LineString) {
            kotlin.test.assertEquals(3, line.vertices.size)
            kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(30.0, 10.0, 10.0, 10.0), line.vertices[0])
            kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(10.0, 30.0, 30.0, 30.0), line.vertices[1])
            kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(40.0, 40.0, 40.0, 40.0), line.vertices[2])
        }
        else {
            kotlin.test.assertTrue(line is net.wuillemin.geohelpers.model.LineString)
        }
    }

    /**
     * Test the read Polygon capability
     */
    @org.junit.Test
    fun testPolygon() {

        val polygonFile = java.io.File(javaClass.classLoader.getResource("wkt\\3dm\\polygon.txt").toURI()).inputStream()

        val polygon = net.wuillemin.geohelpers.wkt.WktReader().readWKT(polygonFile)

        if (polygon is net.wuillemin.geohelpers.model.Polygon) {

            kotlin.test.assertEquals(1, polygon.rings.size)

            val line = polygon.rings[0]
            if (line is net.wuillemin.geohelpers.model.LinearRing) {
                kotlin.test.assertEquals(5, line.vertices.size)
                kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(1.0, 1.0, 1.0, 1.0), line.vertices[0])
                kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(5.0, 1.0, 1.0, 1.0), line.vertices[1])
                kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(5.0, 5.0, 5.0, 5.0), line.vertices[2])
                kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(1.0, 5.0, 5.0, 5.0), line.vertices[3])
                kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(1.0, 1.0, 1.0, 1.0), line.vertices[4])
            }
            else {
                kotlin.test.assertTrue(line is net.wuillemin.geohelpers.model.LineString)
            }
        }
        else {
            kotlin.test.assertTrue(polygon is net.wuillemin.geohelpers.model.Polygon)
        }
    }

    /**
     * Test the read MultiPoint capability
     */
    @org.junit.Test
    fun testMultiPoint() {

        val multiPointFile = java.io.File(javaClass.classLoader.getResource("wkt\\3dm\\multipoint.txt").toURI()).inputStream()

        val multiPoint = net.wuillemin.geohelpers.wkt.WktReader().readWKT(multiPointFile)

        if (multiPoint is net.wuillemin.geohelpers.model.MultiPoint) {
            kotlin.test.assertEquals(2, multiPoint.geometries.size)
            kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(3.5, 5.6, 5.6, 5.6), multiPoint.geometries[0])
            kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(4.8, 10.5, 10.5, 10.5), multiPoint.geometries[1])
        }
        else {
            kotlin.test.assertTrue(multiPoint is net.wuillemin.geohelpers.model.MultiPoint)
        }
    }

    /**
     * Test the read MultiString capability
     */
    @org.junit.Test
    fun testMultiLineString() {

        val multiLineStringFile = java.io.File(javaClass.classLoader.getResource("wkt\\3dm\\multilinestring.txt").toURI()).inputStream()

        val multiLineString = net.wuillemin.geohelpers.wkt.WktReader().readWKT(multiLineStringFile)

        if (multiLineString is net.wuillemin.geohelpers.model.MultiLineString) {

            kotlin.test.assertEquals(2, multiLineString.geometries.size)
            val line1 = multiLineString.geometries[0]
            if (line1 is net.wuillemin.geohelpers.model.LineString) {
                kotlin.test.assertEquals(3, line1.vertices.size)
                kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(3.0, 4.0, 4.0, 4.0), line1.vertices[0])
                kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(10.0, 50.0, 50.0, 50.0), line1.vertices[1])
                kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(20.0, 25.0, 25.0, 25.0), line1.vertices[2])
            }
            else {
                kotlin.test.assertTrue(line1 is net.wuillemin.geohelpers.model.LineString)
            }

            val line2 = multiLineString.geometries[1]
            if (line2 is net.wuillemin.geohelpers.model.LineString) {
                kotlin.test.assertEquals(3, line2.vertices.size)
                kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(-5.0, -8.0, -8.0, -8.0), line2.vertices[0])
                kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(-10.0, -8.0, -8.0, -8.0), line2.vertices[1])
                kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(-15.0, -4.0, -4.0, -4.0), line2.vertices[2])
            }
            else {
                kotlin.test.assertTrue(line2 is net.wuillemin.geohelpers.model.LineString)
            }
        }
        else {
            kotlin.test.assertTrue(multiLineString is net.wuillemin.geohelpers.model.MultiLineString)
        }
    }

    /**
     * Test the read MultiPolygon capability
     */
    @org.junit.Test
    fun testMultiPolygon() {

        val multiPolygonFile = java.io.File(javaClass.classLoader.getResource("wkt\\3dm\\multipolygon.txt").toURI()).inputStream()

        val multipolygon = net.wuillemin.geohelpers.wkt.WktReader().readWKT(multiPolygonFile)

        if (multipolygon is net.wuillemin.geohelpers.model.MultiPolygon) {

            kotlin.test.assertEquals(2, multipolygon.geometries.size)

            val polygon1 = multipolygon.geometries[0]
            if (polygon1 is net.wuillemin.geohelpers.model.Polygon) {
                kotlin.test.assertEquals(2, polygon1.rings.size)

                val line1 = polygon1.rings[0]
                if (line1 is net.wuillemin.geohelpers.model.LinearRing) {
                    kotlin.test.assertEquals(5, line1.vertices.size)
                    kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(1.0, 1.0, 1.0, 1.0), line1.vertices[0])
                    kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(5.0, 1.0, 1.0, 1.0), line1.vertices[1])
                    kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(5.0, 5.0, 5.0, 5.0), line1.vertices[2])
                    kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(1.0, 5.0, 5.0, 5.0), line1.vertices[3])
                    kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(1.0, 1.0, 1.0, 1.0), line1.vertices[4])
                }
                else {
                    kotlin.test.assertTrue(line1 is net.wuillemin.geohelpers.model.LinearRing)
                }

                val line2 = polygon1.rings[1]
                if (line2 is net.wuillemin.geohelpers.model.LinearRing) {
                    kotlin.test.assertEquals(5, line2.vertices.size)
                    kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(2.0, 2.0, 2.0, 2.0), line2.vertices[0])
                    kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(2.0, 3.0, 3.0, 3.0), line2.vertices[1])
                    kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(3.0, 3.0, 3.0, 3.0), line2.vertices[2])
                    kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(3.0, 2.0, 2.0, 2.0), line2.vertices[3])
                    kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(2.0, 2.0, 2.0, 2.0), line2.vertices[4])
                }
                else {
                    kotlin.test.assertTrue(line2 is net.wuillemin.geohelpers.model.LinearRing)
                }
            }
            else {
                kotlin.test.assertTrue(polygon1 is net.wuillemin.geohelpers.model.Polygon)
            }

            val polygon2 = multipolygon.geometries[1]
            if (polygon2 is net.wuillemin.geohelpers.model.Polygon) {
                kotlin.test.assertEquals(1, polygon2.rings.size)

                val line3 = polygon2.rings[0]
                if (line3 is net.wuillemin.geohelpers.model.LinearRing) {
                    kotlin.test.assertEquals(4, line3.vertices.size)
                    kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(6.0, 3.0, 3.0, 3.0), line3.vertices[0])
                    kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(9.0, 2.0, 2.0, 2.0), line3.vertices[1])
                    kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(9.0, 4.0, 4.0, 4.0), line3.vertices[2])
                    kotlin.test.assertEquals(net.wuillemin.geohelpers.model.Point3DM(6.0, 3.0, 3.0, 3.0), line3.vertices[3])
                }
                else {
                    kotlin.test.assertTrue(line3 is net.wuillemin.geohelpers.model.LinearRing)
                }
            }
            else {
                kotlin.test.assertTrue(polygon1 is net.wuillemin.geohelpers.model.Polygon)
            }
        }
    }
}