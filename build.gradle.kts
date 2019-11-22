import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// In addition to the obvious Kotlin Gradle plugin,
// the default configuration declares the kotlin-spring plugin
// which automatically opens classes and methods (unlike in Java, the default qualifier is final in Kotlin)
// annotated or meta-annotated with Spring annotations.
// This is useful to be able to create @Configuration or @Transactional beans
// without having to add the open qualifier required by CGLIB proxies for example.
//
// In order to be able to use Kotlin non-nullable properties with JPA,
// Kotlin JPA plugin is also enabled.
// It generates no-arg constructors for any class annotated with @Entity, @MappedSuperclass or @Embeddable.

plugins {
	id("org.springframework.boot") version "2.2.1.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	kotlin("jvm") version "1.3.50"
	kotlin("plugin.spring") version "1.3.50"
	kotlin("plugin.jpa") version "1.3.50"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_12

val developmentOnly by configurations.creating
configurations {
	runtimeClasspath {
		extendsFrom(developmentOnly)
	}
}

repositories {
	mavenCentral()
}

// 3 Kotlin specific libraries are required for such Spring Boot web application and configured by default:
//
// - kotlin-stdlib-jdk8 is the Java 8 variant of Kotlin standard library
// - kotlin-reflect is Kotlin reflection library
// - jackson-module-kotlin adds support for serialization/deserialization of Kotlin classes
//   and data classes (single constructor classes can be used automatically,
//   and those with secondary constructors or static factories are also supported)

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-mustache")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// One of Kotlin’s key features is null-safety - which cleanly deals with null values at compile time
// rather than bumping into the famous NullPointerException at runtime.
// This makes applications safer through nullability declarations and expressing "value or no value" semantics
// without paying the cost of wrappers like Optional.
// Note that Kotlin allows using functional constructs with nullable values;
// check out this comprehensive guide to Kotlin null-safety.
//
// Although Java does not allow one to express null-safety in its type-system,
// Spring Framework provides null-safety of the whole Spring Framework API via tooling-friendly annotations
// declared in the org.springframework.lang package.
// By default, types from Java APIs used in Kotlin are recognized as platform types
// for which null-checks are relaxed.
// Kotlin support for JSR 305 annotations + Spring nullability annotations provide null-safety
// for the whole Spring Framework API to Kotlin developers,
// with the advantage of dealing with null related issues at compile time.
//
// This feature can be enabled by adding the -Xjsr305 compiler flag with the strict options.
//
// Notice also that Kotlin compiler is configured to generate Java 8 bytecode (Java 6 by default).

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "12"
	}
}
