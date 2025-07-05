package io.nats.client

import meshkit.config.ConfigReader.Companion.readConfigFile
import meshkit.config.catalog.CatalogInfo
import java.time.Duration

fun main() {
    try {
        val appConfig = readConfigFile<AppConfig>("/app-config.yaml")
        val catelogInfo = CatalogInfo.readCatalogInfo()

        val options = Options.Builder()
            .server("nats://localhost:4222")
            .connectionName(catelogInfo.metadata.name)
            .build()
        Nats.connect(options).use { nc ->
            print("Waiting for a message...")

            val dispatcher = nc.createDispatcher { it ->
                println("Received ${it.subject} ${String(it.data)}")
                it.ack()
            }
            dispatcher.subscribe(appConfig.subscribeTopic)
            nc.flush(Duration.ofSeconds(5))
            println("Subscribed to 'kotlin-nats-demo'")
            while (true) {
                Thread.sleep(5000)
            }
        }
    } catch (exp: Exception) {
        exp.printStackTrace()
    }
}