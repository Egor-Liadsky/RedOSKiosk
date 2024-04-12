package components.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import components.BaseComponent
import components.home.HomeComponentImpl
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent

class RootComponentImpl(
    componentContext: ComponentContext
) : BaseComponent<RootState>(componentContext, RootState()), RootComponent, KoinComponent {

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialConfiguration = Config.Home,
            childFactory = ::childFactory
        )

    override fun onBackButtonClick() {
        navigation.pop()
    }

    private fun childFactory(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child =
        when (config) {
            Config.Home -> homeComponent(componentContext)
        }


    private fun homeComponent(componentContext: ComponentContext): RootComponent.Child =
        RootComponent.Child.HomeChild(
            HomeComponentImpl(componentContext = componentContext)
        )


    @Serializable
    private sealed interface Config {

        @Serializable
        data object Home : Config
    }
}