package com.example.cryptoapp.di

import com.example.cryptoapp.MainViewModel
import com.example.cryptoapp.data.DataStorePreferences
import com.example.cryptoapp.data.DataStorePreferencesImpl
import com.example.cryptoapp.data.remote.CryptoService
import com.example.cryptoapp.data.repositories.CryptoRepositoryImpl
import com.example.cryptoapp.domain.repositories.CryptoRepository
import com.example.cryptoapp.presentation.asset_info.AssetInfoViewModel
import com.example.cryptoapp.presentation.assets_list.AssetsListViewModel
import com.example.cryptoapp.presentation.navigation.NavigationViewModel
import com.example.cryptoapp.presentation.settings.SettingsViewModel
import com.example.cryptoapp.util.Constants.BASE_URL
import com.example.cryptoapp.util.dispatchers.DispatcherProvider
import com.example.cryptoapp.util.dispatchers.StandardDispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(CryptoService::class.java)
    }
    single<DispatcherProvider>{
        StandardDispatchers()
    }
    single<CryptoRepository> {
        CryptoRepositoryImpl(get(), get())
    }
    viewModel {
        AssetInfoViewModel(get())
    }
    single<DataStorePreferences> {
        DataStorePreferencesImpl(context = androidContext())
    }
    viewModel {
        AssetsListViewModel(get(), get())
    }
    viewModel {
        NavigationViewModel(get())
    }
    viewModel {

        SettingsViewModel(get())
    }
    viewModel {
        MainViewModel(get())
    }
}