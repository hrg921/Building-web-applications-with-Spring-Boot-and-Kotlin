# Building-web-applications-with-Spring-Boot-and-Kotlin

This tutorial shows you how to build efficiently a sample blog application by combining the power of Spring Boot and Kotlin.

If you are starting with Kotlin, you can learn the language by reading the reference documentation, following the online Kotlin Koans tutorial or just using Spring Framework reference documentation which now provides code samples in Kotlin.

Spring Kotlin support is documented in the Spring Framework and Spring Boot reference documentation. If you need help, search or ask questions with the spring and kotlin tags on StackOverflow or come discuss in the #spring channel of Kotlin Slack.

## Creating a New Project

Using the Initializr Website - [https://start.spring.io/#!language=kotlin&type=gradle-project](https://start.spring.io/#!language=kotlin&type=gradle-project)

## Writing your first Kotlin controller

Start the web application by running the main function of BlogApplication.kt, and go to http://localhost:8080/, you should see a sober web page with a "Blog" headline.

## Testing with JUnit 5

JUnit 5 now used by default in Spring Boot provides various features very handy with Kotlin, including autowiring of constructor/method parameters which allows to use non-nullable `val` properties and the possibility to use `@BeforeAll`/`@AfterAll` on regular non-static methods.

### Writing JUnit 5 tests in Kotlin

For the sake of this example, let’s create an integration test in order to demonstrate various features:

- We use real sentences between backticks instead of camel-case to provide expressive test function names
- JUnit 5 allows to inject constructor and method parameters, which is a good fit with Kotlin read-only and non-nullable properties
- This code leverages `getForObject` and `getForEntity` Kotlin extensions (you need to import them)

## Test instance lifecycle

Sometimes you need to execute a method before or after all tests of a given class. Like Junit 4, JUnit 5 requires by default these methods to be static (which translates to `companion object` in Kotlin, which is quite verbose and not straightforward) because test classes are instantiated one time per test.

But Junit 5 allows you to change this default behavior and instantiate test classes one time per class. This can be done in various ways, here we will use a property file to change the default behavior for the whole project:

`src/test/resources/junit-platform.properties`

```properties
junit.jupiter.testinstance.lifecycle.default = per_class
```

With this configuration, we can now use `@BeforeAll` and `@AfterAll` annotations on regular methods like shown in updated version of `IntegrationTests` above.

`src/test/kotlin/com/example/blog/IntegrationTests.kt`
