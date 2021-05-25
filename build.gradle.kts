import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

defaultTasks("clean", "build")

plugins {
    id("org.springframework.boot") version "2.5.0"
    id("com.expediagroup.graphql") version "4.1.1"
    kotlin("plugin.spring") version "1.5.10"
    kotlin("jvm") version "1.5.10"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.expediagroup", "graphql-kotlin-spring-server", "4.1.1")
    /**
     * I need this for date type support. Details here: https://expediagroup.github.io/graphql-kotlin/docs/schema-generator/writing-schemas/scalars/#extended-scalars
     */
    implementation("com.graphql-java", "graphql-java-extended-scalars", "16.0.1")

    implementation("org.springframework.boot", "spring-boot-starter-data-mongodb-reactive", "2.5.0")
    implementation("de.flapdoodle.embed", "de.flapdoodle.embed.mongo", "3.0.0")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

graphql {
    schema {
        packages = listOf("com.example.newhorizonsgraphql")
    }
}
