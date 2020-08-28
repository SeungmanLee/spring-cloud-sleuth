import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

repositories {
    jcenter()
    gradlePluginPortal()
    mavenCentral()
}

plugins {
    java
    groovy
    kotlin("jvm") version "1.3.50"
    kotlin("plugin.spring") version "1.3.50"
    id("org.springframework.boot") version "2.3.2.RELEASE" apply false
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
}

subprojects {
    group = "com.wwz.example"
    version = "1.0.0-SNAPSHOT" // 프로젝트의 버전

    apply(plugin = "java")
    apply(plugin = "groovy")
    apply(plugin = "kotlin")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    java {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    sourceSets {
        test {
            withConvention(GroovySourceSet::class) {
                groovy.srcDir("test/groovy")
            }
        }
    }

    repositories {
//        maven { url = uri("http://repo.daumkakao.io/content/repositories/daum/") }
//        maven { url = uri("http://repo.daumkakao.io/content/repositories/daum-snapshots/") }
//        maven { url = uri("http://repo.daumkakao.io/content/repositories/daum-thirdparty/") }
//        maven { url = uri("http://repo.daumkakao.io/content/groups/kakao-talkserver-group/") }
        jcenter()
        mavenCentral()
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:Hoxton.SR7")
        }
    }


    val queryDSLVersion = "4.2.1"

    //전체 프로젝트에 공통으로 가지는 의존성을 추가 추가한다.
    dependencies {
        // kotlin
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        // QueryDSL
//        implementation("com.querydsl:querydsl-core:${queryDSLVersion}")
//        implementation("com.querydsl:querydsl-jpa:${queryDSLVersion}")

        // webflux
//        implementation("org.springframework.boot:spring-boot-starter-webflux")

        // spring cloud
        implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
        implementation("org.springframework.cloud:spring-cloud-starter-zipkin")

        // database & jpa
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//        runtimeOnly("mysql:mysql-connector-java")
        runtimeOnly("com.h2database:h2")

        // https://mvnrepository.com/artifact/org.modelmapper/modelmapper
        implementation("org.modelmapper:modelmapper:2.3.4")
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

        // redis
        implementation("org.springframework.boot:spring-boot-starter-cache")
//        implementation("org.springframework.boot:spring-boot-starter-data-redis")

        // es
//        implementation("org.elasticsearch.client:elasticsearch-rest-client:7.1.0")
//        implementation("org.elasticsearch:elasticsearch:7.1.0")
//        implementation("org.elasticsearch.client:elasticsearch-rest-high-level-client:7.1.0")

        compileOnly("org.projectlombok:lombok")

        annotationProcessor("org.projectlombok:lombok")
//        annotationProcessor("com.querydsl:querydsl-apt:${queryDSLVersion}:jpa")
//        annotationProcessor("org.springframework.boot:spring-boot-starter-data-jpa")

        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(module = "junit")
        }

        testImplementation("org.junit.platform:junit-platform-commons:1.6.0")
        testImplementation("org.junit.platform:junit-platform-engine:1.6.0")

        testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
        testImplementation("org.junit.jupiter:junit-jupiter-params:5.6.0")
        testImplementation("org.assertj:assertj-core:3.9.1")
        testImplementation("org.mockito:mockito-junit-jupiter:2.23.0")
        testImplementation("org.mockito:mockito-core")
        testImplementation("org.spockframework:spock-spring:1.2-groovy-2.5")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
        testCompileOnly("org.projectlombok:lombok")
        testAnnotationProcessor("org.projectlombok:lombok")
        implementation("org.jetbrains.kotlin:kotlin-test:1.3.71")
    }

    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "1.8"
            }
        }

        test {
            useJUnitPlatform()
            failFast = true
            testLogging {
                events("PASSED", "FAILED", "SKIPPED")
            }
        }
    }
}



project(":module-account") {
    dependencies {
//        implementation(project(":core"))
    }
}

project(":module-main") {
    dependencies {
//        implementation(project(":core"))
    }
}


project(":module-order-history") {
    dependencies {
//        implementation(project(":core"))
    }
}

project(":module-examples") {
    dependencies {
//        implementation(project(":core"))
    }
}