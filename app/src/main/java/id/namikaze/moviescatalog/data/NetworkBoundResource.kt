package id.namikaze.moviescatalog.data

import id.namikaze.moviescatalog.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) { //cek perlu ambil data dari network atau tidak
            emit(Resource.Loading())
            when (val apiResponse = createCall().first()) { //ambil data dari network
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data) //menyimpan data network ke db
                    emitAll(loadFromDB().map { //ambil data dari DB
                        Resource.Success(it)
                    })
                }
                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map { //ambil data dari DB
                        Resource.Success(it)
                    })
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error<ResultType>(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDB().map { Resource.Success(it) }) //ambil data dari DB
        }
    }

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}