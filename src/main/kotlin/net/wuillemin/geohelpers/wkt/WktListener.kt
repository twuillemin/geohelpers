package net.wuillemin.geohelpers.wkt

import net.wuillemin.geohelpers.model.Geometry
import net.wuillemin.geohelpers.model.LineString
import net.wuillemin.geohelpers.model.LinearRing
import net.wuillemin.geohelpers.model.MultiLineString
import net.wuillemin.geohelpers.model.MultiPoint
import net.wuillemin.geohelpers.model.MultiPolygon
import net.wuillemin.geohelpers.model.Point
import net.wuillemin.geohelpers.model.Point2D
import net.wuillemin.geohelpers.model.Point2DM
import net.wuillemin.geohelpers.model.Point3D
import net.wuillemin.geohelpers.model.Point3DM
import net.wuillemin.geohelpers.model.Polygon
import net.wuillemin.wktgrammar.WktBaseListener
import net.wuillemin.wktgrammar.WktParser

@Suppress("KDocMissingDocumentation")
/**
 * A listener for receiving events while the wkt is parsed
 */
class WktListener : WktBaseListener() {

    var geometry: Geometry? = null

    override fun enterWtk(ctx: WktParser.WtkContext?) {
        //println("Entering Wtk : " + ctx!!.text)
    }

    // -------------------------------------------------------------------------------
    //                               2D GEOMETRIES
    // -------------------------------------------------------------------------------

    //
    // Simple helper functions
    //
    fun buildPoint(point: WktParser.PointContext): Point {
        return Point2D(point.x().text.toDouble(), point.y().text.toDouble())
    }

    fun buildLineString(line: WktParser.LineStringTextContext): LineString {
        return LineString(line.point().map { buildPoint(it) })
    }

    fun buildLinearRing(line: WktParser.LineStringTextContext): LinearRing {
        return LinearRing(line.point().map { buildPoint(it) })
    }

    //
    // Callbacks from the parser
    //
    override fun exitPointTaggedText(ctx: WktParser.PointTaggedTextContext) {
        geometry = buildPoint(ctx.pointText().point())
    }

    override fun exitLineStringTaggedText(ctx: WktParser.LineStringTaggedTextContext) {
        geometry = LineString(ctx.lineStringText().point().map { buildPoint(it) })
    }

    override fun exitPolygonTaggedText(ctx: WktParser.PolygonTaggedTextContext) {
        geometry = Polygon(ctx.polygonText().lineStringText().map { buildLinearRing(it) })
    }

    override fun exitMultiPointTaggedText(ctx: WktParser.MultiPointTaggedTextContext) {
        geometry = MultiPoint(ctx.multiPointText().pointText().map { buildPoint(it.point()) })
    }

    override fun exitMultiLineStringTaggedText(ctx: WktParser.MultiLineStringTaggedTextContext) {
        geometry = MultiLineString(ctx.multiLineStringText().lineStringText().map { buildLineString(it) })
    }

    override fun exitMultiPolygonTaggedText(ctx: WktParser.MultiPolygonTaggedTextContext) {
        geometry = MultiPolygon(ctx.multiPolygonText().polygonText().map {
            Polygon(it.lineStringText().map {
                buildLinearRing(it)
            })
        })
    }

    // -------------------------------------------------------------------------------
    //                               3D GEOMETRIES
    // -------------------------------------------------------------------------------
    //
    // Simple helper functions
    //
    fun buildPointZ(point: WktParser.PointZContext): Point {
        return Point3D(point.x().text.toDouble(), point.y().text.toDouble(), point.z().text.toDouble())
    }

    fun buildLineStringZ(line: WktParser.LineStringTextZContext): LineString {
        return LineString(line.pointZ().map { buildPointZ(it) })
    }

    fun buildLinearRingZ(line: WktParser.LineStringTextZContext): LinearRing {
        return LinearRing(line.pointZ().map { buildPointZ(it) })
    }

    //
    // Callbacks from the parser
    //
    override fun exitPointTaggedTextZ(ctx: WktParser.PointTaggedTextZContext) {
        geometry = buildPointZ(ctx.pointTextZ().pointZ())
    }

    override fun exitLineStringTaggedTextZ(ctx: WktParser.LineStringTaggedTextZContext) {
        geometry = LineString(ctx.lineStringTextZ().pointZ().map { buildPointZ(it) })
    }

    override fun exitPolygonTaggedTextZ(ctx: WktParser.PolygonTaggedTextZContext) {
        geometry = Polygon(ctx.polygonTextZ().lineStringTextZ().map { buildLinearRingZ(it) })
    }

    override fun exitMultiPointTaggedTextZ(ctx: WktParser.MultiPointTaggedTextZContext) {
        geometry = MultiPoint(ctx.multiPointTextZ().pointTextZ().map { buildPointZ(it.pointZ()) })
    }

    override fun exitMultiLineStringTaggedTextZ(ctx: WktParser.MultiLineStringTaggedTextZContext) {
        geometry = MultiLineString(ctx.multiLineStringTextZ().lineStringTextZ().map { buildLineStringZ(it) })
    }

    override fun exitMultiPolygonTaggedTextZ(ctx: WktParser.MultiPolygonTaggedTextZContext) {
        geometry = MultiPolygon(ctx.multiPolygonTextZ().polygonTextZ().map {
            Polygon(it.lineStringTextZ().map {
                buildLinearRingZ(it)
            })
        })
    }

    // -------------------------------------------------------------------------------
    //                               2D + M GEOMETRIES
    // -------------------------------------------------------------------------------
    //
    // Simple helper functions
    //
    fun buildPointM(point: WktParser.PointMContext): Point {
        return Point2DM(point.x().text.toDouble(), point.y().text.toDouble(), point.m().text.toDouble())
    }

    fun buildLineStringM(line: WktParser.LineStringTextMContext): LineString {
        return LineString(line.pointM().map { buildPointM(it) })
    }

    fun buildLinearRingM(line: WktParser.LineStringTextMContext): LinearRing {
        return LinearRing(line.pointM().map { buildPointM(it) })
    }

    //
    // Callbacks from the parser
    //
    override fun exitPointTaggedTextM(ctx: WktParser.PointTaggedTextMContext) {
        geometry = buildPointM(ctx.pointTextM().pointM())
    }

    override fun exitLineStringTaggedTextM(ctx: WktParser.LineStringTaggedTextMContext) {
        geometry = LineString(ctx.lineStringTextM().pointM().map { buildPointM(it) })
    }

    override fun exitPolygonTaggedTextM(ctx: WktParser.PolygonTaggedTextMContext) {
        geometry = Polygon(ctx.polygonTextM().lineStringTextM().map { buildLinearRingM(it) })
    }

    override fun exitMultiPointTaggedTextM(ctx: WktParser.MultiPointTaggedTextMContext) {
        geometry = MultiPoint(ctx.multiPointTextM().pointTextM().map { buildPointM(it.pointM()) })
    }

    override fun exitMultiLineStringTaggedTextM(ctx: WktParser.MultiLineStringTaggedTextMContext) {
        geometry = MultiLineString(ctx.multiLineStringTextM().lineStringTextM().map { buildLineStringM(it) })
    }

    override fun exitMultiPolygonTaggedTextM(ctx: WktParser.MultiPolygonTaggedTextMContext) {
        geometry = MultiPolygon(ctx.multiPolygonTextM().polygonTextM().map {
            Polygon(it.lineStringTextM().map {
                buildLinearRingM(it)
            })
        })
    }

    // -------------------------------------------------------------------------------
    //                               3D + M GEOMETRIES
    // -------------------------------------------------------------------------------
    //
    // Simple helper functions
    //
    fun buildPointZM(point: WktParser.PointZMContext): Point {
        return Point3DM(
                point.x().text.toDouble(),
                point.y().text.toDouble(),
                point.z().text.toDouble(),
                point.m().text.toDouble())
    }

    fun buildLineStringZM(line: WktParser.LineStringTextZMContext): LineString {
        return LineString(line.pointZM().map { buildPointZM(it) })
    }

    fun buildLinearRingZM(line: WktParser.LineStringTextZMContext): LinearRing {
        return LinearRing(line.pointZM().map { buildPointZM(it) })
    }

    //
    // Callbacks from the parser
    //
    override fun exitPointTaggedTextZM(ctx: WktParser.PointTaggedTextZMContext) {
        geometry = buildPointZM(ctx.pointTextZM().pointZM())
    }

    override fun exitLineStringTaggedTextZM(ctx: WktParser.LineStringTaggedTextZMContext) {
        geometry = LineString(ctx.lineStringTextZM().pointZM().map { buildPointZM(it) })
    }

    override fun exitPolygonTaggedTextZM(ctx: WktParser.PolygonTaggedTextZMContext) {
        geometry = Polygon(ctx.polygonTextZM().lineStringTextZM().map { buildLinearRingZM(it) })
    }

    override fun exitMultiPointTaggedTextZM(ctx: WktParser.MultiPointTaggedTextZMContext) {
        geometry = MultiPoint(ctx.multiPointTextZM().pointTextZM().map { buildPointZM(it.pointZM()) })
    }

    override fun exitMultiLineStringTaggedTextZM(ctx: WktParser.MultiLineStringTaggedTextZMContext) {
        geometry = MultiLineString(ctx.multiLineStringTextZM().lineStringTextZM().map { buildLineStringZM(it) })
    }

    override fun exitMultiPolygonTaggedTextZM(ctx: WktParser.MultiPolygonTaggedTextZMContext) {
        geometry = MultiPolygon(ctx.multiPolygonTextZM().polygonTextZM().map {
            Polygon(it.lineStringTextZM().map {
                buildLinearRingZM(it)
            })
        })
    }

}