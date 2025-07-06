package meshkit.config.catalog

import meshkit.config.ConfigReader

data class CatalogInfo(
    val apiVersion: String,
    val kind: EntityKind = EntityKind.Component,
    val metadata: Metadata,
    val spec: ComponentSpec
) {
    companion object {
        fun readCatalogInfo(): CatalogInfo {
            return readCatalogInfo("/catalog-info.yaml")
        }

        fun readCatalogInfo(resourcePath: String): CatalogInfo {
            return ConfigReader.readConfigFile(resourcePath)
        }
    }

    val entityReference get() = EntityReference(kind, metadata.namespace, metadata.name)
}

