package com.demo.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.app.domain.interactor.GetCurrenciesUseCase
import com.demo.app.domain.interactor.GetFactUseCase
import com.demo.app.domain.models.CurrencyResponseUiModel
import com.demo.app.domain.models.FactResponseUiModel
import com.demo.app.presentation.resource.Resource
import com.demo.app.presentation.resource.ResourceState
import com.demo.app.presentation.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val getFactUseCase: GetFactUseCase) : ViewModel() {

    val currenciesObserver = SingleLiveEvent<Resource<List<CurrencyResponseUiModel>>>()
    val factObserver = SingleLiveEvent<Resource<FactResponseUiModel>>()

    fun getCurrenciesList() {
        viewModelScope.launch {
            currenciesObserver.value = Resource(ResourceState.LOADING)
            getCurrenciesUseCase.invoke().catch {
                Timber.d("error${it}")
                currenciesObserver.value = Resource(ResourceState.ERROR, errorMessage = it.message)
            }.collect { remoteResponse ->
                Timber.d("success")
                currenciesObserver.value = Resource(ResourceState.SUCCESS, remoteResponse)
            }
        }
    }

    private fun getFact() {
        viewModelScope.launch {
            factObserver.value = Resource(ResourceState.LOADING)
            getFactUseCase.invoke().catch {
                Timber.d("error${it}")
                factObserver.value = Resource(ResourceState.ERROR, errorMessage = it.message)
            }.collect { remoteResponse ->
                Timber.d("success")
                factObserver.value = Resource(ResourceState.SUCCESS, remoteResponse)
            }
        }
    }

    fun callFactApi(uiModel: CurrencyResponseUiModel) {
        getFact()
    }
}