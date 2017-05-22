package net.wuillemin

import net.wuillemin.geohelpers.geojson.GeoJsonWriter
import net.wuillemin.geohelpers.wkt.WKTReader
import java.io.File

fun main(args: Array<String>) {

    val shapeCollection = WKTReader.readWKT(File("C:\\tmp\\multipoint.txt"))

    val geoJson = GeoJsonWriter.writeGeoJson(shapeCollection)

    System.out.println(geoJson)
}

