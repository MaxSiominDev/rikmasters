package dev.maxsiomin.testdoorscameras.data.repository

import dev.maxsiomin.testdoorscameras.data.local.CameraEntity
import dev.maxsiomin.testdoorscameras.data.local.DoorEntity
import dev.maxsiomin.testdoorscameras.data.mapper.toCameraEntity
import dev.maxsiomin.testdoorscameras.data.mapper.toCameraModel
import dev.maxsiomin.testdoorscameras.data.mapper.toDoorEntity
import dev.maxsiomin.testdoorscameras.data.mapper.toDoorModel
import dev.maxsiomin.testdoorscameras.data.remote.CamerasApi
import dev.maxsiomin.testdoorscameras.domain.CameraModel
import dev.maxsiomin.testdoorscameras.domain.DoorModel
import dev.maxsiomin.testdoorscameras.domain.repository.Repository
import dev.maxsiomin.testdoorscameras.util.Resource
import io.ktor.utils.io.writer
import io.realm.kotlin.Deleteable
import io.realm.kotlin.Realm
import io.realm.kotlin.delete
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.IllegalStateException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: CamerasApi,
    private val realm: Realm,
) : Repository {

    override suspend fun getCameras(fetchFromRemote: Boolean): Flow<Resource<List<CameraModel>>> {
        return flow {
            emit(Resource.Loading(true))
            val localData = realm.query<CameraEntity>().find().map {
                it.toCameraModel()
            }.sortedBy { it.id }
            emit(Resource.Success(data = localData))

            val isDbEmpty = localData.isEmpty()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if(shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val apiResponse = api.getCameras()
            val remoteData = apiResponse.response?.data?.cameras?.sortedBy { it.id }
            if (remoteData != null) {
                realm.write {
                    this.delete<CameraEntity>()
                    remoteData.forEach {
                        copyToRealm(it.toCameraModel().toCameraEntity())
                    }
                }
                emit(Resource.Success(remoteData.map { it.toCameraModel() }))
            } else {
                emit(Resource.Error(apiResponse.error?.message ?: "unknown error"))
            }
            emit(Resource.Loading(false))
        }
    }

    override suspend fun getDoors(fetchFromRemote: Boolean): Flow<Resource<List<DoorModel>>> {
        return flow {
            emit(Resource.Loading(true))
            val localData = realm.query<DoorEntity>().find().map {
                it.toDoorModel()
            }.sortedBy { it.id }
            emit(Resource.Success(data = localData))

            val isDbEmpty = localData.isEmpty()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if(shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val apiResponse = api.getDoors()
            val remoteData = apiResponse.response?.data?.sortedBy { it.id }
            if (remoteData != null) {
                realm.write {
                    this.delete<DoorEntity>()
                    remoteData.forEach {
                        copyToRealm(it.toDoorModel().toDoorEntity())
                    }
                }
                emit(Resource.Success(remoteData.map { it.toDoorModel() }))
            } else {
                emit(Resource.Error(apiResponse.error?.message ?: "unknown error"))
            }
            emit(Resource.Loading(false))
        }
    }

    override suspend fun updateCamera(camera: CameraModel, callback: () -> Unit) {
        realm.query<CameraEntity>("id == ${camera.id} LIMIT(1)")
            .first()
            .find()
            ?.also { foundCamera ->
                // Add a dog in a transaction
                realm.writeBlocking {
                    val newEntity = camera.toCameraEntity()
                    findLatest(foundCamera)?.apply {
                        name = newEntity.name
                        rec = newEntity.rec
                        snapshot = newEntity.snapshot
                        room = newEntity.room
                        favorites = newEntity.favorites
                    }
                }
            }

        callback.invoke()
    }

    override suspend fun updateDoor(door: DoorModel, callback: () -> Unit) {
        realm.query<DoorEntity>("id == ${door.id} LIMIT(1)")
            .first()
            .find()
            ?.also { foundCamera ->
                // Add a dog in a transaction
                realm.writeBlocking {
                    val newEntity = door.toDoorEntity()
                    findLatest(foundCamera)?.apply {
                        name = newEntity.name
                        snapshot = newEntity.snapshot
                        room = newEntity.room
                        favorites = newEntity.favorites
                    }
                }
            }

        callback.invoke()
    }

}