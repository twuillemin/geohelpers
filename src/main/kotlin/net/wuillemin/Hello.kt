package net.wuillemin

import net.wuillemin.geohelpers.geojson.GeoJsonWriter
import net.wuillemin.geohelpers.wkt.WktReader

fun main(args: Array<String>) {


    // val shapeCollection = WKTReader.readWKT("MULTIPOLYGON(((1 1,5 1,5 5,1 5,1 1),(2 2,2 3,3 3,3 2,2 2)),((6 3,9 2,9 4,6 3)))")
    // val shapeCollection = WktReader.readWKT("MULTIPOLYGON( ((1 1,2 2,3 3,4 4,1 1),(2 2,3 3,4 4,5 5,2 2)),((3 3,4 4,5 5,3 3),(4 4,5 5,6 6,4 4)),((5 5,6 6,7 7,5 5)))")
    // val shapeCollection = WKTReader.readWKT(File("C:\\tmp\\multipoint.txt"))

    val shapeCollection = WktReader.readWKT("LINESTRING(3 4,10 50,20 25)")

    val geoJson = GeoJsonWriter.writeGeoJson(shapeCollection)

    System.out.println(geoJson)
}

