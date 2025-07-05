package io.dazraf

import io.nats.client.Nats
import io.nats.client.Options
import meshkit.config.ConfigReader.Companion.readConfigFile
import meshkit.config.catalog.CatalogInfo
import java.time.Duration
import java.util.*

fun main(args: Array<String>) {
    val appConfig = readConfigFile<AppConfig>("/app-config.yaml")
    val catalogInfo = CatalogInfo.readCatalogInfo()
    val options = Options.Builder()
        .server("nats://localhost:4222")
        .connectionName(catalogInfo.metadata.name)
        .build()
    try {
        Nats.connect(options).use { nc ->
            while (true) {
                val payload = "payload @ ${Date()}"

                println(payload)

                nc.publish(appConfig.publishTopic, payload.toByteArray())
                nc.flush(Duration.ofSeconds(5))
                Thread.sleep(1000)
            }
        }
    } catch (exp: Exception) {
        exp.printStackTrace()
    }
}