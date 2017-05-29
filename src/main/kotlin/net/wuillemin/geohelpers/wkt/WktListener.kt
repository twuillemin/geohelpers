package net.wuillemin.geohelpers.wkt

import net.wuillemin.geohelpers.model.Geometry
import net.wuillemin.geohelpers.model.LineString
import net.wuillemin.geohelpers.model.LinearRing
import net.wuillemin.geohelpers.model.MultiLineString
import net.wuillemin.geohelpers.model.MultiPoint
import net.wuillemin.geohelpers.model.MultiPolygon
import net.wuillemin.geohelpers.model.Point2D
import net.wuillemin.geohelpers.model.Polygon
import net.wuillemin.wktgrammar.WktBaseListener
import net.wuillemin.wktgrammar.WktParser

/**
 * A listener for receiving events while the wkt is parsed
 */
class WktListener : WktBaseListener() {

    var geometry: Geometry? = null

    override fun enterWtk(ctx: WktParser.WtkContext?) {
        println("Entering Wtk : " + ctx!!.text)
    }

    //
    // Simple helper functions
    //
    fun buildPoint(point: WktParser.PointContext): Point2D {
        return Point2D(point.x().text.toDouble(), point.y().text.toDouble())
    }

    fun buildLineString(line: WktParser.LineStringTextContext): LineString {
        return LineString(line.point().map { point -> buildPoint(point) })
    }

    fun buildLinearRing(line: WktParser.LineStringTextContext): LinearRing {
        return LinearRing(line.point().map { point -> buildPoint(point) })
    }

    //
    // Callbacks from the parser
    //
    override fun exitPointTaggedText(ctx: WktParser.PointTaggedTextContext) {
        geometry = buildPoint(ctx.pointText().point())
    }

    override fun exitLineStringTaggedText(ctx: WktParser.LineStringTaggedTextContext) {
        geometry = LineString(ctx.lineStringText().point().map { point -> buildPoint(point) })
    }

    override fun exitPolygonTaggedText(ctx: WktParser.PolygonTaggedTextContext) {
        geometry = Polygon(ctx.polygonText().lineStringText().map { line -> buildLinearRing(line) })
    }

    override fun exitMultiPointTaggedText(ctx: WktParser.MultiPointTaggedTextContext) {
        geometry = MultiPoint(ctx.multiPointText().pointText().map { point -> buildPoint(point.point()) })
    }

    override fun exitMultiLineStringTaggedText(ctx: WktParser.MultiLineStringTaggedTextContext) {
        geometry = MultiLineString(ctx.multiLineStringText().lineStringText().map { line -> buildLineString(line) })
    }

    override fun exitMultiPolygonTaggedText(ctx: WktParser.MultiPolygonTaggedTextContext) {
        geometry = MultiPolygon(ctx.multiPolygonText().polygonText().map {
            polygon ->
            Polygon(polygon.lineStringText().map {
                line ->
                buildLinearRing(line)
            })
        })
    }
}