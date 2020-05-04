package extensions

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * Adds a dependency to the `debugImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.debugImplementation(dependencyNotation: String) {
    add("debugImplementation", dependencyNotation)
}

/**
 * Adds a list of dependencies to the `debugImplementation` configuration.
 *
 * @param dependencies List of dependencies to add at specific configuration
 */
fun DependencyHandler.debugImplementation(dependencies: Array<String>) =
    dependencies.forEach { debugImplementation(it) }

/**
 * Adds a dependency to the `implementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.implementation(dependencyNotation: String): Dependency? =
    add("implementation", dependencyNotation)

/**
 * Adds a list of dependencies to the `implementation` configuration.
 *
 * @param dependencies List of dependencies to add at specific configuration
 */
fun DependencyHandler.implementation(dependencies: Array<String>) = dependencies.forEach { implementation(it) }

/**
 * Adds a dependency to the `api` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.api(dependencyNotation: String): Dependency? =
    add("api", dependencyNotation)

/**
 * Adds a dependency to the `api` configuration.
 *
 * @param dependencyNotation List of dependencies to add at specific configuration
 */
fun DependencyHandler.api(dependencies: Array<String>) = dependencies.forEach { api(it) }

/**
 * Adds a dependency to the `kapt` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.kapt(dependencyNotation: String): Dependency? =
    add("kapt", dependencyNotation)

/**
 * Adds a dependency to the `kapt` configuration.
 *
 * @param dependencyNotation List of dependencies to add at specific configuration
 */
fun DependencyHandler.kapt(dependencies: Array<String>) = dependencies.forEach { kapt(it) }

/**
 * Adds a dependency to the `testImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.testImplementation(dependencyNotation: String): Dependency? =
    add("testImplementation", dependencyNotation)

/**
 * Adds a dependency to the `testImplementation` configuration.
 *
 * @param dependencyNotation List of dependencies to add at specific configuration
 */
fun DependencyHandler.testImplementation(dependencies: Array<String>) = dependencies.forEach { testImplementation(it) }

/**
 * Adds a dependency to the `androidTestImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.androidTestImplementation(dependencyNotation: String): Dependency? =
    add("androidTestImplementation", dependencyNotation)

/**
 * Adds a dependency to the `androidTestImplementation` configuration.
 *
 * @param dependencyNotation List of dependencies to add at specific configuration
 */
fun DependencyHandler.androidTestImplementation(dependencies: Array<String>) =
    dependencies.forEach { androidTestImplementation(it) }
