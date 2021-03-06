/*
 * Copyright 2020 Jose Maria Payá Castillo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import dependencies.AnnotationProcessorsDependencies
import dependencies.Dependencies
import extensions.implementation
import extensions.kapt

plugins {
    id("commons.android-library")
}

allOpen {
    // allows mocking for classes w/o directly opening them for release builds
    annotation("com.jpaya.core.annotations.OpenClass")
}

dependencies {
    implementation(
        arrayOf(
            Dependencies.ROOM,
            Dependencies.ROOM_KTX,
            Dependencies.LIFECYCLE_EXTENSIONS,
            Dependencies.NAVIGATION_UI,
            Dependencies.FRAGMENT_KTX,
            Dependencies.CORE_KTX,
            Dependencies.RETROFIT,
            Dependencies.RETROFIT_CONVERTER,
            Dependencies.LOGGING,
            Dependencies.MOSHI,
            Dependencies.MOSHI_KTX,
            Dependencies.FIREBASE_FIRESTORE,
            Dependencies.FIREBASE_AUTH
        )
    )
    kapt(
        arrayOf(
            AnnotationProcessorsDependencies.DATABINDING,
            AnnotationProcessorsDependencies.ROOM
        )
    )
}
