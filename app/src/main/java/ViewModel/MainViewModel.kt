package ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.launch
import model.ApiFactoryService
import model.EmissionFactor
import model.EmissionFactorRequest
import model.Parameters
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.IOException

 class MainViewModel : ViewModel() {

     companion object {
         private var instance: MainViewModel? = null

         fun getInstance(): MainViewModel {
             return instance ?: synchronized(this) {
                 instance ?: MainViewModel().also { instance = it }
             }
         }
     }

    private  val _co2e: MutableLiveData<Double> = MutableLiveData()
    val co2e: LiveData<Double> get() = _co2e

     val _accumulatedCo2e: MutableLiveData<Double>  = MutableLiveData()
    val accumulatedCo2e: LiveData<Double> = _accumulatedCo2e

    private val co2eList = mutableListOf<Double>()

    fun calcularEmisiones(requestBody: RequestBody) {
        viewModelScope.launch {
            try {
                val response = ApiFactoryService.service.calcularEmisiones(requestBody)

                Log.e("MainViewModel", "Error en la respuesta: ${response.body()}")

                if (response.isSuccessful) {
                    response.body()?.co2e?.let {
                        _co2e.value = it
                        co2eList.add(it)
                        _accumulatedCo2e.value = co2eList.sum()
                        Log.e("MainViewModel", "Error en la respuesta: ${_accumulatedCo2e.value}")
                    }
                } else {
                    val errorMessage = response.errorBody()?.string()
                    Log.e("MainViewModel", "Error en la respuesta: $errorMessage")
                }
            } catch (e: IOException) {
                Log.e("MainViewModel", "Excepción de red: ${e.message}")
            } catch (e: HttpException) {
                Log.e("MainViewModel", "Excepción HTTP: ${e.message}")
            } catch (e: Exception) {
                Log.e("MainViewModel", "Excepción desconocida: ${e.message}")
            }
        }
    }

     fun RecibirSolicitudAlimentos(activityId: String) {

         val emissionFactorRequest = EmissionFactorRequest(
             emission_factor = EmissionFactor(
                 activity_id = activityId,
                 data_version = "^13"
             ),
             parameters = Parameters(
                 money = 1,
                 money_unit = "eur"
             )
         )

         val requestBody = Gson().toJson(emissionFactorRequest).toRequestBody("application/json".toMediaTypeOrNull())

         calcularEmisiones(requestBody)

         // Mostrar un mensaje de confirmación
         //Toast.makeText(this, "123Solicitud de alimentos enviada correctamente", Toast.LENGTH_SHORT).show()
     }
}
