package dev.maxsiomin.testdoorscameras.data.remote

import dev.maxsiomin.testdoorscameras.data.remote.dto.CameraResponse
import dev.maxsiomin.testdoorscameras.data.remote.dto.DoorResponse
import dev.maxsiomin.testdoorscameras.domain.CameraModel
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.url
import timber.log.Timber
import javax.inject.Inject

class CamerasApiImpl @Inject constructor(
    private val client: HttpClient
) : CamerasApi {

    override suspend fun getCameras(): ResponseWithMessage<CameraResponse, Exception> {
        try {
            val response: CameraResponse? = client.get  {
                url(HttpRoutes.GET_CAMERAS)
            }
            if (response == null) {
                Timber.e("Response is null")
                return ResponseWithMessage(null, Exception("Response is null"))
            }
            return ResponseWithMessage(response, null)
        } catch (e: RedirectResponseException) {
            Timber.e(e.response.status.description)
            return ResponseWithMessage(null, e)
        } catch (e: ClientRequestException) {
            Timber.e(e.response.status.description)
            return ResponseWithMessage(null, e)
        } catch (e: ServerResponseException) {
            Timber.e(e.response.status.description)
            return ResponseWithMessage(null, e)
        } catch (e: Exception) {
            Timber.e(e.message)
            return ResponseWithMessage(null, e)
        }
    }

    override suspend fun getDoors(): ResponseWithMessage<DoorResponse, Exception> {
        try {
            val response: DoorResponse? = client.get  {
                url(HttpRoutes.GET_DOORS)
            }
            if (response == null) {
                Timber.e("Response is null")
                return ResponseWithMessage(null, Exception("Response is null"))
            }
            return ResponseWithMessage(response, null)
        } catch (e: RedirectResponseException) {
            Timber.e(e.response.status.description)
            return ResponseWithMessage(null, e)
        } catch (e: ClientRequestException) {
            Timber.e(e.response.status.description)
            return ResponseWithMessage(null, e)
        } catch (e: ServerResponseException) {
            Timber.e(e.response.status.description)
            return ResponseWithMessage(null, e)
        } catch (e: Exception) {
            Timber.e(e.message)
            return ResponseWithMessage(null, e)
        }
    }
}