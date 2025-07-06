package meshkit.config.catalog

data class ComponentSpec(
    val type: String,
    val lifecycle: String,
    val owner: String,
    val system: String? = null,
    val subcomponentOf: String? = null,
    val providesApis: List<EntityReference>? = null,
    val consumesApis: List<EntityReference>? = null,
    val dependsOn: List<EntityReference>? = null,
    val dependencyOf: List<EntityReference>? = null,
    val partOf: List<String>? = null,
    val implementsApis: List<String>? = null,
    val environment: String? = null,
    val links: List<Link>? = null,
    val children: List<EntityReference>? = null,
    val members: List<EntityReference>? = null,
    val memberOf: List<EntityReference>? = null
)