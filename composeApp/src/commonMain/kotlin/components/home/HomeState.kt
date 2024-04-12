package components.home

import utils.LoadingState

data class HomeState(
    val productsLoadingState: LoadingState = LoadingState.Loading,
)