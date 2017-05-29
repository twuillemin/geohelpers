package net.wuillemin.geohelpers.wkt

import net.wuillemin.geohelpers.model.Geometry
import net.wuillemin.wktgrammar.WktLexer
import net.wuillemin.wktgrammar.WktParser
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.InputStream

/**
 * A Simple class for reading WKT Data. Supported data are: POINT, LINESTRING, POLYGON and MULTIPOINT
 */
class WktReader {

    /**
     * Companion object for providing static access in java client
     */
    companion object {

        /**
         * Static function for reading a WKT string
         * @param str The WKT data to read
         * @return A list of the shapes in the file
         */
        @JvmStatic
        fun readWKT(str: String): Geometry {
            return WktReader().readWktStream(CharStreams.fromString(str))
        }

        /**
         * Static function for reading a WKT input stream
         * @param stream The WKT data to read
         * @return A list of the shapes in the file
         */
        @JvmStatic
        fun readWKT(stream: InputStream): Geometry {
            return WktReader().readWktStream(CharStreams.fromStream(stream))
        }
    }

    /**
     * Reader for WKT stream
     * @param stream The stream to read.
     * @return A list of the shapes in the file
     */
    fun readWktStream(stream: CharStream): Geometry {

        // Set the lexer
        val lexer = WktLexer(stream)
        val tokens = CommonTokenStream(lexer)

        // Set the parser
        val parser = WktParser(tokens)

        //Set the listener
        val listener = WktListener()
        parser.addParseListener(listener)

        // Start parsing
        parser.wtk()

        // Return the data if possible
        return listener.geometry ?: throw AssertionError("Nothing was read from the file")
    }
}

