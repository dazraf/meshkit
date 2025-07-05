package io.nats.client

import meshkit.config.entity.Entity

data class AppConfig(val subscribeTopic: String, val component: Entity)
