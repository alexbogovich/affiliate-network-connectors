import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    `maven-publish`
    id("com.jfrog.bintray")
}

group = "net.besttoolbars"
version = "0.0.7"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${property("jackson.version")}")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${property("jackson.version")}")
    api("com.squareup.retrofit2:retrofit:${property("retrofit.version")}")
    implementation("com.squareup.retrofit2:converter-jackson:${property("retrofit.version")}")

    testImplementation("org.junit.jupiter:junit-jupiter:5.5.2")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.2.0")
    testImplementation("commons-io:commons-io:2.6")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile>().all {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType(JavaCompile::class) {
    options.compilerArgs.add("-parameters")
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

publishing {
    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])
            artifact(sourcesJar.get())
        }
    }
}

bintray {
    user = System.getenv("BINTRAY_USER")
    key = System.getenv("BINTRAY_KEY")
    publish = true
    override = true
    setPublications("mavenJava")
    pkg(delegateClosureOf<com.jfrog.bintray.gradle.BintrayExtension.PackageConfig> {
        repo = "repo"
        name = "city-ads-connector"
        userOrg = "besttoolbars"
        websiteUrl = "https://github.com/Besttoolbars/affiliate-network-connectors/city-ads-connector"
        githubRepo = "Besttoolbars/affiliate-network-connectors"
        vcsUrl = "https://github.com/Besttoolbars/affiliate-network-connectors.git"
        description = "City Ads jvm connector"
        setLabels("kotlin", "jvm", "cj")
        setLicenses("Apache-2.0")
    })
}

tasks.withType<Test> {
    useJUnitPlatform()
}
