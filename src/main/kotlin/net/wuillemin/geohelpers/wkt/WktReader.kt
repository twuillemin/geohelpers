package net.wuillemin.geohelpers.wkt

import net.wuillemin.geohelpers.model.Geometry
import net.wuillemin.wktgrammar.WktLexer
import net.wuillemin.wktgrammar.WktParser
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.InputStream

/**
 * A Simple class for reading WKT Data.
 */
class WktReader {

    /**
     * Read a WTK from an InputStream and give back the Geometry object
     * @param str The string containing the WKT data to read
     * @return The Geometry
     */
    fun readWKT(str: String): Geometry {
        return WktReader().readWktStream(CharStreams.fromString(str))
    }

    /**
     * Read a WTK from an InputStream and give back the Geometry object
     * @param stream The stream containing the WKT data to read
     * @return The Geometry
     */
    fun readWKT(stream: InputStream): Geometry {
        return WktReader().readWktStream(CharStreams.fromStream(stream))
    }

    /**
     * Read a WTK from an InputStream and give back the Geometry object
     * @param stream An ANTLR stream containing the WKT data to read
     * @return The Geometry
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

