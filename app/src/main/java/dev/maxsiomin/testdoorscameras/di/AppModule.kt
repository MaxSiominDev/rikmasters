package dev.maxsiomin.testdoorscameras.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.maxsiomin.testdoorscameras.data.local.CameraEntity
import dev.maxsiomin.testdoorscameras.data.local.DoorEntity
import dev.maxsiomin.testdoorscameras.data.remote.CamerasApi
import dev.maxsiomin.testdoorscameras.data.remote.CamerasApiImpl
import dev.maxsiomin.testdoorscameras.data.repository.RepositoryImpl
import dev.maxsiomin.testdoorscameras.domain.repository.Repository
import dev.maxsiomin.testdoorscameras.util.isDebug
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideRealmDatabase(): Realm {
        val configuration = RealmConfiguration.Builder(setOf(CameraEntity::class, DoorEntity::class)).apply {
            if (isDebug()) {
                deleteRealmIfMigrationNeeded()
            }
        }.build()
        return Realm.open(configuration)
    }

    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json()
            }
        }
    }

    @Provides
    @Singleton
    fun provideCamerasApi(client: HttpClient): CamerasApi {
        return CamerasApiImpl(client)
    }

    @Provides
    @Singleton
    fun provideRepo(camerasApi: CamerasApi, realm: Realm): Repository {
        return RepositoryImpl(camerasApi, realm)
    }

}
