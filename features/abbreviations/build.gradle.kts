import dependencies.Dependencies
import extensions.implementation

plugins {
    id("commons.android-dynamic-feature")
}

dependencies {
    implementation(project(BuildModules.Features.HOME))

    implementation(Dependencies.RECYCLER_VIEW)
    implementation(Dependencies.PAGING)
    implementation(Dependencies.FIREBASE_ANALYTICS)
    implementation(Dependencies.FIREBASE_FIRESTORE)
    /* coroutines support for firebase operations */
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.1.1")
}
