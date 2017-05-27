package net.wuillemin.geohelpers.wkt

import net.wuillemin.geohelpers.common.LineString
import net.wuillemin.geohelpers.common.MultiLineString
import net.wuillemin.geohelpers.common.MultiPoint
import net.wuillemin.geohelpers.common.MultiPolygon
import net.wuillemin.geohelpers.common.Point
import net.wuillemin.geohelpers.common.Polygon
import net.wuillemin.geohelpers.common.Shape
import net.wuillemin.wktgrammar.WktBaseListener
import net.wuillemin.wktgrammar.WktParser

/**
 * A listener for receiving events while the wkt is parsed
 */
class WktListener : WktBaseListener() {

    var shape : Shape? = null

    override fun enterWtk(ctx: WktParser.WtkContext?) {
        println("Entering Wtk : " + ctx!!.text)
    }

    //
    // Simple helper functions
    //
    fun buildPoint(point: WktParser.PointTextContext): Point {
        return Point(point.point().x().text.toDouble(), point.point().y().text.toDouble())
    }

    fun buildPoint(point: WktParser.PointContext): Point {
        return Point(point.x().text.toDouble(), point.y().text.toDouble())
    }

    fun buildLineString(line: WktParser.LineStringTextContext): LineString {
        return LineString(line.point().map { point -> buildPoint(point) })
    }

    //
    // Callbacks from the parser
    //
    override fun exitPointTaggedText(ctx: WktParser.PointTaggedTextContext) {
        shape = buildPoint(ctx.pointText())
    }

    override fun exitLineStringTaggedText(ctx: WktParser.LineStringTaggedTextContext) {
        shape = LineString(ctx.lineStringText().point().map { point -> buildPoint(point) })
    }

    override fun exitPolygonTaggedText(ctx: WktParser.PolygonTaggedTextContext) {
        shape = Polygon(ctx.polygonText().lineStringText().map { line -> buildLineString(line) })
    }

    override fun exitMultiPointTaggedText(ctx: WktParser.MultiPointTaggedTextContext) {
        shape = MultiPoint(ctx.multiPointText().pointText().map { point -> buildPoint(point) })
    }

    override fun exitMultiLineStringTaggedText(ctx: WktParser.MultiLineStringTaggedTextContext) {
        shape = MultiLineString(ctx.multiLineStringText().lineStringText().map { line -> buildLineString(line) })
    }

    override fun exitMultiPolygonTaggedText(ctx: WktParser.MultiPolygonTaggedTextContext) {
        shape = MultiPolygon(ctx.multiPolygonText().polygonText().map {
            polygon ->
            Polygon(polygon.lineStringText().map {
                line ->
                buildLineString(line)
            })
        })
    }
}