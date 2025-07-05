package meshkit.config

import meshkit.config.ConfigReader.Companion.readConfigFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ConfigReaderTest {
    data class AppConfig(val value: String)

    @Test
    fun `that we can read an entity configuration`() {
        readConfigFile<AppConfig>("/app-config.yaml").apply {
            assertEquals("Hello, World!", value)
        }
    }

    @Test
    fun `that addFilePathSource adds a file path source and reads config`() {
        val testFilePath = "src/test/resources/app-config.yaml"
        val configReader = ConfigReader().addFilePathSource(testFilePath)
        val config = configReader.read<ConfigReaderTest.AppConfig>()
        assertEquals("Hello, World!", config.value)
    }
}