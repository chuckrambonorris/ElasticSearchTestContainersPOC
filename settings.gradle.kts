/*
 * This file was generated by the Gradle 'init' task.
 *
 * The settings file is used to specify which projects to include in your build.
 * For more detailed information on multi-project builds, please refer to https://docs.gradle.org/8.7/userguide/multi_project_builds.html in the Gradle documentation.
 * This project uses @Incubating APIs which are subject to change.
 */
dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            library("spring-data-elasticsearch", "org.springframework.data", "spring-data-elasticsearch")
            library("spring-boot-starter-webflux", "org.springframework.boot", "spring-boot-starter-webflux")
            library("spring-boot-starter-test", "org.springframework.boot", "spring-boot-starter-test")
            library("spring-boot-starter-graphql", "org.springframework.boot", "spring-boot-starter-graphql")
            library("springframework-graphql-test", "org.springframework.graphql", "spring-graphql-test")
            library("testcontainers-elasticsearch", "org.testcontainers", "elasticsearch")
            library("testcontainers-junit-jupiter", "org.testcontainers", "junit-jupiter")
            library("spring-boot-starter-elastic", "org.springframework.boot", "spring-boot-starter-data-elasticsearch")
        }
    }
}

rootProject.name = "ElasticTestContainersPOC"
include("app")
