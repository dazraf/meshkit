package meshkit.config

import com.sksamuel.hoplite.ConfigLoaderBuilder
import com.sksamuel.hoplite.addResourceSource

data class ConfigReader(val files: List<String>) {
    /**
     * Reads the configuration files and returns a list of strings.
     * This is a placeholder implementation that simply returns the file names.
     * In a real implementation, this would read the contents of the files.
     */
    inline fun <reified T: Any> read(): T {
        return ConfigLoaderBuilder.default()
            .apply {
                files.forEach { file ->
                    this.addResourceSource(file)
                }
            }
            .build()
            .loadConfigOrThrow<T>()
    }

}