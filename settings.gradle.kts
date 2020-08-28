pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

rootProject.name = "spring-cloud-sleuth"

include("module-main",
        "module-account",
        "module-order-history",
        "module-examples")