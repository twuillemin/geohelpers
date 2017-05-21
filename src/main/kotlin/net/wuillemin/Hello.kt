package net.wuillemin

import net.wuillemin.geohelpers.wkt.readWKT
import java.io.File

fun main(args: Array<String>) {
    val shape = readWKT(File("C:\\tmp\\multipoint.txt"))
    println(shape)
}

