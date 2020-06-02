package dependencies

/**
 * Project Android test dependencies, makes it easy to include external binaries or other library modules to build.
 */
object TestAndroidDependencies {
    const val COMPOSE_CORE = "androidx.ui:ui-core:${BuildDependenciesVersions.COMPOSE}"
    const val COMPOSE_TEST = "androidx.ui:ui-test:${BuildDependenciesVersions.COMPOSE}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${BuildDependenciesVersions.ESPRESSO}"
    const val FRAGMENT_TEST = "androidx.fragment:fragment-testing:${BuildDependenciesVersions.FRAGMENT_TEST}"
    const val JUNIT = "androidx.test.ext:junit:${BuildDependenciesVersions.EXT}"
    const val LEAKCANARY = "com.squareup.leakcanary:leakcanary-android-instrumentation:${BuildDependenciesVersions.LEAKCANARY}"
    const val MOCKITO = "com.nhaarman.mockitokotlin2:mockito-kotlin:${BuildDependenciesVersions.MOCKITO}"
    const val PLAY_CORE = "com.google.android.play:core:${BuildDependenciesVersions.PLAY_CORE}"
    const val RULES = "androidx.test:rules:${BuildDependenciesVersions.TEST}"
    const val RUNNER = "androidx.test:runner:${BuildDependenciesVersions.TEST}"

    /**
     * Method to obtain all the Android test dependencies.
     *
     * @return An array with all the Android test dependencies.
     */
    fun all() = arrayOf(
        COMPOSE_CORE,
        COMPOSE_TEST,
        ESPRESSO,
        FRAGMENT_TEST,
        JUNIT,
        LEAKCANARY,
        MOCKITO,
        PLAY_CORE,
        RULES,
        RUNNER
    )
}
