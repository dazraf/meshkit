package io.nats.client

import meshkit.config.ConfigReader
import java.time.Duration

fun main(args: Array<String>) {
    try {
        val config = ConfigReader(listOf("/app-config.yml")).read<AppConfig>()
        val options = Options.Builder()
            .server("nats://localhost:4222")
            .connectionName("consumer")
            .build()
        Nats.connect(options).use { nc ->
            print("Waiting for a message...")

            val dispatcher = nc.createDispatcher { it ->
                println("Received ${it.subject} ${String(it.data)}")
                it.ack();
            }
            dispatcher.subscribe(config.subscribeTopic)
            nc.flush(Duration.ofSeconds(5))
            println("Subscribed to 'kotlin-nats-demo'")
            while (true) {
                Thread.sleep(5000);
            }
        }
    } catch (exp: Exception) {
        exp.printStackTrace()
    }
}