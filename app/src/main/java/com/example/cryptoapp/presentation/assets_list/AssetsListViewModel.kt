package com.example.cryptoapp.presentation.assets_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.data.DataStorePreferences
import com.example.cryptoapp.domain.repositories.CryptoRepository
import com.example.cryptoapp.util.paginator.DefaultPaginator
import kotlinx.coroutines.launch

class AssetsListViewModel(
    private val repository: CryptoRepository,
    private val dataStore: DataStorePreferences
) : ViewModel() {

    var state by mutableStateOf(AssetsListState())

    private val paginator = DefaultPaginator(
        initialKey = state.page,
        onLoadUpdated = {
            state = state.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            repository.getAssets(nextPage, 20)
        },
        getNextKey = {
            state.page + 1
        }, onError = {
            state = state.copy(error = it.localizedMessage)
        }, onSuccess = { items, newKey ->
            state =
                state.copy(
                    list = state.list + items,
                    page = newKey,
                    isEndReached = items.isEmpty()
                )
        })

    init {
        loadNextItems()
    }

    fun onEvent(event: AssetListEvent) {
        when (event) {
            is AssetListEvent.LoadNextItems -> loadNextItems()
            is AssetListEvent.SetTitle -> setTitle(event.t, event.a)
        }
    }


    private fun loadNextItems() = viewModelScope.launch {
        paginator.loadNextItems()
    }

    private fun setTitle(t: String, a: String) = viewModelScope.launch {
        dataStore.setTitle(t)
        dataStore.setAbbreviation(a)
    }
}