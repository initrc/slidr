package io.github.initrc.slidr.feature.portfolio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.initrc.slidr.core.data.PortfolioRepository
import io.github.initrc.slidr.core.model.Portfolio
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val portfolioRepository: PortfolioRepository,
) : ViewModel() {
    val uiState: StateFlow<PortfolioUiState> =
        portfolioRepository.getPortfolio("kyz")
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
