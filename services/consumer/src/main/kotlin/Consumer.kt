package io.nats.client

import meshkit.config.catalog.CatalogInfo
import java.time.Duration

fun main() {
    try {
        val catalogInfo = CatalogInfo.readCatalogInfo()

        val options = Options.Builder()
            .server("nats://localhost:4222")
            .connectionName(catalogInfo.metadata.name)
            .build()
        Nats.connect(options).use { nc ->
            val dispatcher = nc.createDispatcher { it ->
                println("Received ${it.subject} ${String(it.data)}")
                it.ack()
            }
            val subject = catalogInfo.entityReference.id
            println("Waiting for messages on subject '$subject'...")
            dispatcher.subscribe(subject)
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