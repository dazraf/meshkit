package meshkit.config.catalog

import meshkit.config.catalog.CatalogInfo.Companion.readCatalogInfo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CatalogInfoTest {

    @Test
    fun `can load catalog-info yaml`() {
        readCatalogInfo().apply {
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