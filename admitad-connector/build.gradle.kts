import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.jfrog.bintray.gradle.BintrayExtension

plugins {
    kotlin("jvm")
    `maven-publish`
    id("com.jfrog.bintray")
}

version = "0.0.4"

dependencies {
    implementation(project(":shared-mapper"))
    implementation(kotlin("stdlib-jdk8"))
    api(project(":shared-retrofit-extensions"))

    api("com.squareup.retrofit2:retrofit:${property("retrofit.version")}")
    implementation("com.squareup.retrofit2:converter-jackson:${property("retrofit.version")}")

    implementation("com.fasterxml:aalto-xml:1.2.0")
    implementation("commons-io:commons-io:2.6")

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
    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = "repo"
        name = "admitad-connector"
        userOrg = "besttoolbars"
        websiteUrl = "https://github.com/Besttoolbars/affiliate-network-connectors/admitad-connector"
        githubRepo = "Besttoolbars/affiliate-network-connectors"
        vcsUrl = "https://github.com/Besttoolbars/affiliate-network-connectors.git"
        description = "Admitad jvm connector"
        setLabels("kotlin", "jvm", "admitad")
        setLicenses("Apache-2.0")
    })
}

tasks.withType<Test> {
    useJUnitPlatform()
}