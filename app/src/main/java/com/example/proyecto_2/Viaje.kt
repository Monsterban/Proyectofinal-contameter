package com.example.proyecto_2

import ViewModel.MainViewModel
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText


class Viaje : AppCompatActivity() {

    private lateinit var Menu: Button

    private lateinit var distancia : TextInputEditText
    private lateinit var guardar: Button
    private lateinit var VImageViews: List<ImageView>
    private lateinit var selectedVImageView: ImageView
    private lateinit var mainViewModel: MainViewModel
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.journey)
        Menu = findViewById(R.id.btn_cancelar)
        distancia = findViewById(R.id.txt_distancia)
        guardar = findViewById(R.id.btn_Gvehiculo)
        VImageViews = listOf(
            findViewById(R.id.auto),
            findViewById(R.id.tren),
            findViewById(R.id.bicicleta),
            findViewById(R.id.bus),
            findViewById(R.id.moto),
            findViewById(R.id.avion),
            findViewById(R.id.caminar)
        )
        selectedVImageView = VImageViews[0] // Initialize with the first image view

        Menu.setOnClickListener(){
            PasarMenu()
        }

        guardar.setOnClickListener {

            enviarSolicitudV(distancia.text.toString().toInt())
            Toast.makeText(this, "Solicitud enviada correctamente", Toast.LENGTH_SHORT).show()


        }

        VImageViews.forEach { imageView ->
            imageView.setOnClickListener {
                handleFoodSelection(imageView)
                Toast.makeText(this, "ImageView seleccionado: ${resources.getResourceEntryName(imageView.id)}", Toast.LENGTH_SHORT).show()
            }
        }

    }


    private fun handleFoodSelection(selectedImageView: ImageView) {
        selectedVImageView.isSelected = false // Desmarcar la imagen previamente seleccionada
        selectedVImageView.isSelected = true // Marcar la imagen seleccionada actualmente
        selectedVImageView = selectedImageView // Actualizar la referencia a la imagen seleccionada
    }

    private fun enviarSolicitudV(distancia : Int) {
        val activityId = when (selectedVImageView) {
            findViewById<ImageView>(R.id.auto) -> {
                MainViewModel.getInstance().RecibirSolicitudVehiculos("passenger_vehicle-vehicle_type_car-fuel_source_bev-engine_size_na-vehicle_age_na-vehicle_weight_na",distancia)
            }
            findViewById<ImageView>(R.id.pocacarne) -> {

                MainViewModel.getInstance().RecibirSolicitudVehiculos("passenger_vehicle-vehicle_type_car-fuel_source_bev-engine_size_na-vehicle_age_na-vehicle_weight_na",distancia)
            }
            findViewById<ImageView>(R.id.btn_mPescado) -> {
                MainViewModel.getInstance().RecibirSolicitudVehiculos("passenger_vehicle-vehicle_type_car-fuel_source_bev-engine_size_na-vehicle_age_na-vehicle_weight_na",distancia)

            }
            findViewById<ImageView>(R.id.btn_Vegano) -> {
                MainViewModel.getInstance().RecibirSolicitudVehiculos("passenger_vehicle-vehicle_type_car-fuel_source_bev-engine_size_na-vehicle_age_na-vehicle_weight_na",distancia)

            }
            else -> throw IllegalStateException("No se ha seleccionado ninguna opción de comida.")
        }
    }


    private fun PasarMenu() {
        // Crear un Intent para la actividad de destino
        val intent = Intent(this, PromedioActivity::class.java)

        // Iniciar la actividad usando el Intent
        startActivity(intent)
    }
}