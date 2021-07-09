import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    `maven-publish`
    id("com.jfrog.artifactory")
}

version = "1.1.1"

dependencies {
    implementation(project(":shared-mapper"))
    implementation(project(":shared-retrofit-extensions"))
    implementation(kotlin("stdlib-jdk8"))

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

artifactory {
    setContextUrl("https://softomate.jfrog.io/artifactory")
    clientConfig.setIncludeEnvVars(true)
    clientConfig.info.setBuildName("awin-connector")
    publish(closureOf<org.jfrog.gradle.plugin.artifactory.dsl.PublisherConfig> {
        repository(delegateClosureOf<groovy.lang.GroovyObject> {
            setProperty("repoKey", "jvm-modules")
            setProperty("username", System.getenv("JFROG_MODULES_USER"))
            setProperty("password", System.getenv("JFROG_MODULES_PASS"))
        })
        defaults(delegateClosureOf<groovy.lang.GroovyObject> {
            invokeMethod("publications", "mavenJava")
            setProperty("publishPom", true)
            setProperty("publishArtifacts", true)
        })
    })
}

tasks.withType<Test> {
    useJUnitPlatform()
}
