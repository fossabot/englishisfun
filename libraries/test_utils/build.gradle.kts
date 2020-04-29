
import dependencies.AnnotationProcessorsDependencies
import dependencies.Dependencies
import dependencies.TestDependencies
import extensions.implementation

plugins {
    id("commons.android-library")
}

dependencies {
    implementation(Dependencies.PAGING)
    implementation(Dependencies.NAVIGATION_UI)

    implementation(TestDependencies.JUNIT)
    implementation(TestDependencies.MOCKITO)
    implementation(TestDependencies.ASSERTJ)
    implementation(TestDependencies.ROBOELECTRIC)
    implementation(TestDependencies.ROOM)
    implementation(TestDependencies.CORE)
    implementation(TestDependencies.ARCH_CORE)
    implementation(TestDependencies.RULES)
    implementation(TestDependencies.RUNNER)
    implementation(TestDependencies.COROUTINES_TEST)
    implementation(TestDependencies.FRAGMENT_TEST)
    implementation(TestDependencies.EXT)
    implementation(TestDependencies.MOCK_WEB_SERVER)

    annotationProcessor(AnnotationProcessorsDependencies.AUTO_SERVICE)
}
