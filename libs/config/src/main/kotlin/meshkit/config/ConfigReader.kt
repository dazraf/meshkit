package meshkit.config

import com.sksamuel.hoplite.ConfigLoaderBuilder
import com.sksamuel.hoplite.addFileSource
import com.sksamuel.hoplite.addResourceSource
import meshkit.config.catalog.EntityReference

data class ConfigReader(val configSource: List<ConfigSource> = emptyList()) {
    companion object {
        inline fun <reified T : Any> readConfigFile(resourcePath: String): T {
            return ConfigReader().addResourceSource(resourcePath).read<T>()
        }
    }

    sealed interface ConfigSource {
        val path: String

        data class FilePathConfigSource(override val path: String) : ConfigSource
        data class ResourcePathConfigSource(override val path: String) : ConfigSource
    }

    fun addResourceSource(resourcePath: String): ConfigReader {
        return ConfigReader(configSource + ConfigSource.ResourcePathConfigSource(resourcePath))
    }

    fun addFilePathSource(filePath: String): ConfigReader {
        return ConfigReader(configSource + ConfigSource.FilePathConfigSource(filePath))
    }

    /**
     * Reads the configuration files and returns a list of strings.
     * This is a placeholder implementation that simply returns the file names.
     * In a real implementation, this would read the contents of the files.
     */
    inline fun <reified T : Any> read(): T {
        return ConfigLoaderBuilder.default()
            .addDecoder(EntityReference.EntityReferenceDecoder)
//            .withExplicitSealedTypes()
            .apply {
                configSource.forEach { source ->
                    when (source) {
                        is ConfigSource.FilePathConfigSource -> this.addFileSource(source.path)
                        is ConfigSource.ResourcePathConfigSource -> this.addResourceSource(source.path)
                    }
                }
            }
            .build()
            .loadConfigOrThrow<T>()
    }

}