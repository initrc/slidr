package io.github.initrc.slidr.feature.portfolio

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.initrc.slidr.core.data.PortfolioRepository
import io.github.initrc.slidr.core.model.Portfolio
import io.github.initrc.slidr.feature.portfolio.navigation.toPortfolioArgs
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    portfolioRepository: PortfolioRepository,
) : ViewModel() {
    private val userId: String = savedStateHandle.toPortfolioArgs().userId
    val uiState: StateFlow<PortfolioUiState> =
        portfolioRepository.getPortfolio(userId)
            .map<Portfolio, PortfolioUiState>(PortfolioUiState::Success)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = PortfolioUiState.Loading
            )
}

sealed class PortfolioUiState {
    data object Loading : PortfolioUiState()
    data class Success(val portfolio: Portfolio) : PortfolioUiState()
}
