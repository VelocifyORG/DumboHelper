plugins {
    id("common-conventions")
    id("io.freefair.lombok") version "8.4"
}

dependencies {
    implementation("org.yaml:snakeyaml:2.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.15.2")
    implementation("org.reflections:reflections:0.10.2")
}
