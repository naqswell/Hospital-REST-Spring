import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.4"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
//	базовый плагин Kotlin для работы на JVM.
//	Без которого не заведется ни одно приложение на Java-стеке.
	kotlin("jvm") version "1.4.31"
//	поскольку классы в Kotlin по умолчанию финальны,
//	то этот плагин автоматически сделает классы, помеченные аннотациями
//	@Component, @Async, @Transactional, @Cacheable и @SpringBootTest открытыми к наследованию,
//	а в тематике, относящейся этой статье, это позволит классам,
//	написанным на Kotlin быть проксированными в Spring через CGLib прокси.
	kotlin("plugin.spring") version "1.4.31"
//	Если предыдущие два плагина применяются к любому приложению на Kotlin + Spring Boot,
//	то следующий, уже относится напрямую к Hibernate. А он, как известно,
//	для инициализации Entity использует рефлексию и инициализирует класс
//	с конструктором без аргументов. Но так как мы пишем на Kotlin,
//	такового конструктора может и не найтись. Если мы определили свой
//	собственный первичный конструктор (primary constructor),
//	то при загрузке Entity у нас выкинет исключение:
	kotlin("plugin.jpa") version "1.4.31"
}

group = "com.naqswell"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-rest")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-security")

//	ужен для рефлексии на Kotlin, которая уже поддерживается в
//	Spring Boot и широко используется для инициализации классов.
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//	добавляет возможность работать с коллекциями Java, поддержку стримов и многое другое.
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}
//Важно отметить, что сущности, помеченные аннотациями @Entity, @MappedSuperclass и @Embaddable,
// не станут open после подключения плагина. Более того, get accessor’ы тоже будут финальными,
// и тогда мы потеряем возможность работать с entity reference.
// Чтобы этого избежать и сделать Entity и его поля open, добавим в build.gradle.kts:
allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.MappedSuperclass")
	annotation("javax.persistence.Embeddable")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
