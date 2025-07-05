package meshkit.config

import meshkit.config.entity.Entity
import meshkit.config.entity.EntityKind
import meshkit.config.entity.Link
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ConfigReaderTest {

    @Test
    fun `that we can read an entity configuration`() {
        ConfigReader(listOf("/test-component.yml")).read<Entity>().apply {
            assertEquals("backstage.io/v1alpha1", apiVersion)
            assertEquals(EntityKind.Component, kind)
            metadata.apply {
                assertEquals("artist-web", name)
                assertEquals("The place to be, for great artists", description)
                assertTrue(tags?.contains("java") ?: false)
                assertTrue(annotations?.containsKey("example.com/service-discovery") ?: false)
                assertTrue(annotations?.containsKey("circleci.com/project-slug") ?: false)
                assertEquals("meshkit", namespace)
                assertNotNull(links)
                links?.apply {
                    val link = Link("https://admin.example-org.com", "Admin Dashboard", "dashboard")
                    assertTrue(contains(link))
                }
            }

            spec.apply {
                assertEquals("website", type)
                assertEquals("production", lifecycle)
                assertEquals("artist-relations-team", owner)
                assertEquals("public-websites", system)
            }



        }
    }

}