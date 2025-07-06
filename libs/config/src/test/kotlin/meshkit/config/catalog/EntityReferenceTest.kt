package meshkit.config.catalog

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class EntityReferenceTest {

    @Test
    fun `parse with kind and name only`() {
        val ref = EntityReference.parse("Component:my-service")
        assertEquals(EntityKind.Component, ref.kind)
        assertNull(ref.namespace)
        assertEquals("my-service", ref.name)
    }

    @Test
    fun `parse with kind, namespace and name`() {
        val ref = EntityReference.parse("Resource:prod/my-db")
        assertEquals(EntityKind.Resource, ref.kind)
        assertEquals("prod", ref.namespace)
        assertEquals("my-db", ref.name)
    }

    @Test
    fun `parse with lowercase kind`() {
        val ref = EntityReference.parse("system:core")
        assertEquals(EntityKind.System, ref.kind)
        assertNull(ref.namespace)
        assertEquals("core", ref.name)
    }

    @Test
    fun `parse with mixed case kind`() {
        val ref = EntityReference.parse("API:public/endpoint")
        assertEquals(EntityKind.API, ref.kind)
        assertEquals("public", ref.namespace)
        assertEquals("endpoint", ref.name)
    }

    @Test
    fun `parse throws on invalid format - missing colon`() {
        assertThrows<IllegalArgumentException> {
            EntityReference.parse("Component-my-service")
        }
    }

    @Test
    fun `parse throws on invalid format - too many slashes`() {
        assertThrows<IllegalArgumentException> {
            EntityReference.parse("Component:ns1/ns2/name")
        }
    }

    @Test
    fun `parse throws on invalid kind`() {
        assertThrows<IllegalArgumentException> {
            EntityReference.parse("Unknown:foo")
        }
    }

    @Test
    fun `toString with namespace`() {
        val ref = EntityReference(EntityKind.Resource, "prod", "my-db")
        assertEquals("Resource:prod/my-db", ref.toString())
    }

    @Test
    fun `toString without namespace`() {
        val ref = EntityReference(EntityKind.Component, null, "my-service")
        assertEquals("Component:my-service", ref.toString())
    }

    @Test
    fun `id with namespace`() {
        val ref = EntityReference(EntityKind.Resource, "prod", "my-db")
        assertEquals("prod/my-db", ref.id)
    }

    @Test
    fun `id without namespace`() {
        val ref = EntityReference(EntityKind.Component, null, "my-service")
        assertEquals("my-service", ref.id)
    }

    @Test
    fun `id with blank namespace`() {
        val ref = EntityReference(EntityKind.Component, "", "my-service")
        assertEquals("/my-service", ref.id)
    }
}