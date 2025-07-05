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
}