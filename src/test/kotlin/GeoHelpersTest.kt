import com.google.gson.JsonArray
import com.google.gson.JsonObject
import net.wuillemin.geohelpers.GeoHelpers
import org.junit.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Tests for the GeoHelpers class
 */

class GeoHelpersTest {

    /**
     * Test the read Point capability
     */
    @Test
    fun testConvertFile() {

        // POINT (30 10)
        val pointFile = File(javaClass.classLoader.getResource("wkt\\2d\\point.txt").toURI()).inputStream()

        val result = GeoHelpers().wktToGeoJson(pointFile)

        System.out.println(result.toString())

        assertEquals("Feature", result.get("type").asString)

        val geometry = result.get("geometry")
        if (geometry is JsonObject) {
            assertEquals("Point", geometry.get("type").asString)

            val coordinates = geometry.get("coordinates")
            if (coordinates is JsonArray) {
                assertEquals(2, coordinates.size())
                assertEquals(30.0, coordinates[0].asDouble)
                assertEquals(10.0, coordinates[1].asDouble)
            }
            else {
                assertTrue(coordinates is JsonArray)
            }
        }
        else {
            assertTrue(geometry is JsonObject)
        }
    }

    /**
     * Test the read Point capability
     */
    @Test
    fun testConvertString() {

        val result = GeoHelpers().wktToGeoJson("POINT (30 10)")

        System.out.println(result.toString())

        assertEquals("Feature", result.get("type").asString)

        val geometry = result.get("geometry")
        if (geometry is JsonObject) {
            assertEquals("Point", geometry.get("type").asString)

            val coordinates = geometry.get("coordinates")
            if (coordinates is JsonArray) {
                assertEquals(2, coordinates.size())
                assertEquals(30.0, coordinates[0].asDouble)
                assertEquals(10.0, coordinates[1].asDouble)
            }
            else {
                assertTrue(coordinates is JsonArray)
            }
        }
        else {
            assertTrue(geometry is JsonObject)
        }
    }
}