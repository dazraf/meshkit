plugins {
    kotlin("jvm") version "2.1.21"
}

group = "io.dazraf"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":libs:config"))
    implementation("io.nats:jnats:2.11.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}