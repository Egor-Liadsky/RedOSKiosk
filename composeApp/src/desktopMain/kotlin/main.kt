import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import components.root.RootComponentImpl
import di.initKoin
import ui.root.RootScreen
import utils.runOnUiThread

internal val koin = initKoin().koin
@OptIn(ExperimentalDecomposeApi::class)
fun main() {

    val lifecycle = LifecycleRegistry()

    val root = runOnUiThread {
        RootComponentImpl(componentContext = DefaultComponentContext(lifecycle = lifecycle))
    }

    application {

        val windowState = rememberWindowState(
//            size = DpSize(1080.dp, 750.dp)
            placement = WindowPlacement.Fullscreen
        )

        LifecycleController(lifecycle, windowState)

        Window(
            onCloseRequest = ::exitApplication,
            title = "Kiosk",
            state = windowState
        ) {
            MaterialTheme {
                Surface {
                    RootScreen(component = root, modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}