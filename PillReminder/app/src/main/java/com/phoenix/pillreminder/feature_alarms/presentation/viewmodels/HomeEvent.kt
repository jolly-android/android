package com.phoenix.pillreminder.feature_alarms.presentation.viewmodels

import androidx.annotation.StringRes
import com.phoenix.pillreminder.feature_alarms.domain.model.Medicine

sealed interface HomeEvent {
    data class ShowUsageWarning(val medicine: Medicine) : HomeEvent
    data class ShowToast(@StringRes val messageRes: Int) : HomeEvent
}

