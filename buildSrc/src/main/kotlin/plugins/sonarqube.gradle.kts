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

package plugins

import org.sonarqube.gradle.SonarQubeExtension
import org.sonarqube.gradle.SonarQubePlugin
import utils.getLocalProperty

apply<SonarQubePlugin>()

configure<SonarQubeExtension> {
    androidVariant = "devDebug"
    properties {
        property("sonar.projectKey", getLocalProperty("sonar.projectKey", project))
        property("sonar.organization", getLocalProperty("sonar.organization", project))
        property("sonar.host.url", getLocalProperty("sonar.host.url", project))
        property("sonar.login", getLocalProperty("sonar.login", project))
        property("sonar.androidLint.reportPaths", "build/reports/lint-results-devDebug.xml")
        property("sonar.kotlin.detekt.reportPaths", "build/reports/detekt/report.xml")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/devDebug/jacoco.xml")
    }
}
