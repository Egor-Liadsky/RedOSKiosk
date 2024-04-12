import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    id("kotlinx-serialization")
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.buildKonfig)
}

kotlin {
    jvm("desktop")
    
    sourceSets {
        val desktopMain by getting
        
        commonMain.dependencies {
            implementation(libs.decompose)
            implementation(libs.decompose.compose)
            implementation(libs.koin.compose)
            implementation(libs.bundles.ktor.common)

            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.io.core)

            implementation(compose.runtime)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.ktor.client.java)
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}

buildkonfig {
    packageName = "com.turtleteam.red_os_kiosk"

    defaultConfigs {
        buildConfigField(Type.STRING, "BASE_URL", "http://localhost:8087/api/")
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.turtleteam.red_os_kiosk"
            packageVersion = "1.0.0"
        }
    }
}
