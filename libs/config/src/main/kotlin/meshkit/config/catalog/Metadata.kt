package meshkit.config.catalog

data class Metadata(
    val name: String,
    val namespace: String? = null,
    val description: String? = null,
    val tags: List<String>? = null,
    val annotations: Map<String, String>? = null,
    val labels: Map<String, String>? = null,
    val links: List<Link>? = null,
)