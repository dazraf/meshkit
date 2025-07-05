package meshkit.config.entity

/**
 * Data classes for Backstage Software Catalog Component entity, compatible with Hoplite.
 * Reference: https://backstage.io/docs/features/software-catalog/descriptor-format
 */

enum class EntityKind {
    Component,
    API,
    Resource,
    System,
    Domain,
    Location,
    User,
    Group,
    Template,
    Policy
}

data class Entity(
    val apiVersion: String,
    val kind: EntityKind = EntityKind.Component,
    val metadata: Metadata,
    val spec: ComponentSpec
)

data class Metadata(
    val name: String,
    val description: String? = null,
    val tags: List<String>? = null,
    val annotations: Map<String, String>? = null,
    val labels: Map<String, String>? = null,
    val links: List<Link>? = null,
    val namespace: String? = null
)

data class Link(
    val url: String,
    val title: String? = null,
    val icon: String? = null
)

data class ComponentSpec(
    val type: String,
    val lifecycle: String,
    val owner: String,
    val system: String? = null,
    val subcomponentOf: String? = null,
    val providesApis: List<String>? = null,
    val consumesApis: List<String>? = null,
    val dependsOn: List<String>? = null,
    val partOf: List<String>? = null,
    val implementsApis: List<String>? = null,
    val environment: String? = null,
    val links: List<Link>? = null
)
