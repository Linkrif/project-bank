plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"

	id("org.springframework.boot") version "2.7.1"
	id("io.spring.dependency-management") version "1.1.6"
	kotlin("plugin.jpa") version "1.7.21"
	application

}

group = "br.com.projectbank"
version = "1.0.0"

repositories {
	mavenCentral()
}



dependencies {
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-tomcat")

	implementation("com.microsoft.sqlserver:mssql-jdbc")
	implementation("net.sourceforge.jtds:jtds:1.3.1")
	implementation("org.springdoc:springdoc-openapi-ui:1.8.0")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.auth0:java-jwt:4.4.0")
	implementation("se.transmode.gradle:gradle-docker:1.2")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("com.h2database:h2:2.3.232")
	testImplementation("org.mockito:mockito-core")
	testImplementation("org.powermock:powermock-api-mockito2:2.0.9")
	testImplementation("org.powermock:powermock-module-junit4:2.0.9")

	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

