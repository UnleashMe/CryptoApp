package com.example.cryptoapp.data.repositories

import com.example.cryptoapp.data.mapper.toAsset
import com.example.cryptoapp.data.mapper.toMarket
import com.example.cryptoapp.data.remote.CryptoService
import com.example.cryptoapp.domain.model.Asset
import com.example.cryptoapp.domain.model.Market
import com.example.cryptoapp.domain.repositories.CryptoRepository
import com.example.cryptoapp.util.dispatchers.DispatcherProvider
import com.example.cryptoapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class CryptoRepositoryImpl(
    private val cryptoService: CryptoService,
    private val dispatcherProvider: DispatcherProvider
) : CryptoRepository {
    override suspend fun getAssets(page: Int, quan: Int): Result<List<Asset>> {
        return withContext(dispatcherProvider.io) {
             try {
                val startingIndex = page * quan
                val result = cryptoService.getAssets().data.map { it.toAsset() }
                if (startingIndex + quan > result.size) {
                    Result.success(emptyList())
                } else {
                    Result.success(result.slice(startingIndex until startingIndex + quan))
                }
            } catch (e: IOException) {
                Result.failure(Exception("Couldn't load the data"))
            } catch (e: HttpException) {
                Result.failure(Exception("Couldn't load the data"))
            }
        }
    }

    override suspend fun getAssetById(id: String): Flow<Resource<Asset>> = flow {
        emit(Resource.Loading())
        try {
            val result = cryptoService.getAssetById(id).data.toAsset()
            emit(Resource.Success(result))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't load the data"))
        } catch (e: HttpException) {
            emit(Resource.Error("Couldn't load the data"))
        }
    }.flowOn(dispatcherProvider.io)

    override suspend fun getMarketById(id: String): Flow<Resource<List<Market>>> = flow {
        emit(Resource.Loading())
        try {
            val result = cryptoService.getMarketsById(id).data.map { it.toMarket() }
            emit(Resource.Success(result))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't load the data"))
        } catch (e: HttpException) {
            emit(Resource.Error("Couldn't load the data"))
        }
    }.flowOn(dispatcherProvider.io)
}