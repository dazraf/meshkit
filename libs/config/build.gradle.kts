plugins {
    kotlin("jvm") version "2.1.21"
}

group = "io.dazraf"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")
    implementation("com.sksamuel.hoplite:hoplite-core:2.9.0")
    implementation("com.sksamuel.hoplite:hoplite-yaml:2.9.0")
    implementation("com.sksamuel.hoplite:hoplite-json:2.9.0")
    implementation("com.sksamuel.hoplite:hoplite-hocon:2.9.0")
    implementation("com.sksamuel.hoplite:hoplite-toml:2.9.0")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}