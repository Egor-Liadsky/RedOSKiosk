package components.home

import com.arkivanov.decompose.ComponentContext
import components.BaseComponent
import org.koin.core.component.KoinComponent

class HomeComponentImpl(
    componentContext: ComponentContext,
) : BaseComponent<HomeState>(componentContext, HomeState()), HomeComponent, KoinComponent {

}