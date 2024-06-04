package com.example.proyecto_2

import ViewModel.MainViewModel
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class Alimentos : AppCompatActivity() {

    private lateinit var menuButton: Button
    private lateinit var guardarButton: Button
    private lateinit var foodImageViews: List<ImageView>
    private lateinit var selectedFoodImageView: ImageView
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.food)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        menuButton = findViewById(R.id.btn_Cancelar)
        guardarButton = findViewById(R.id.btn_guardar)
        foodImageViews = listOf(
            findViewById(R.id.btn_mCarne),
            findViewById(R.id.pocacarne),
            findViewById(R.id.btn_mPescado),
            findViewById(R.id.btn_Vegano)
        )
        selectedFoodImageView = foodImageViews[0] // Initialize with the first image view

        menuButton.setOnClickListener {
            PasarMenu()
        }

        guardarButton.setOnClickListener {
            enviarSolicitudAlimentos()
            Toast.makeText(this, "Solicitud enviada correctamente", Toast.LENGTH_SHORT).show()
        }

        foodImageViews.forEach { imageView ->
            imageView.setOnClickListener {
                handleFoodSelection(imageView)
                Toast.makeText(this, "ImageView seleccionado: ${resources.getResourceEntryName(imageView.id)}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleFoodSelection(selectedImageView: ImageView) {
        selectedFoodImageView.isSelected = false // Desmarcar la imagen previamente seleccionada
        selectedFoodImageView.isSelected = true // Marcar la imagen seleccionada actualmente
        selectedFoodImageView = selectedImageView // Actualizar la referencia a la imagen seleccionada
    }

    private fun enviarSolicitudAlimentos() {
        val activityId = when (selectedFoodImageView) {
            findViewById<ImageView>(R.id.btn_mCarne) -> {
                MainViewModel.getInstance().RecibirSolicitudAlimentos("consumer_goods-type_meat_products_beef")
            }
            findViewById<ImageView>(R.id.pocacarne) -> {
                mostrarToastConImagenSeleccionada("Poca carne")
                MainViewModel.getInstance().RecibirSolicitudAlimentos("consumer_goods-type_meat_and_offal")
            }
            findViewById<ImageView>(R.id.btn_mPescado) -> {
                mostrarToastConImagenSeleccionada("Pescado")
                MainViewModel.getInstance().RecibirSolicitudAlimentos("consumer_goods-type_fish_products")
            }
            findViewById<ImageView>(R.id.btn_Vegano) -> {
                mostrarToastConImagenSeleccionada("Vegano")
                MainViewModel.getInstance().RecibirSolicitudAlimentos("arable_farming-type_vegetables")
            }
            else -> throw IllegalStateException("No se ha seleccionado ninguna opción de comida.")
        }
    }


    private fun mostrarToastConImagenSeleccionada(nombreImagen: String) {
        Toast.makeText(this, "Imagen seleccionada: $nombreImagen", Toast.LENGTH_SHORT).show()
    }

    private fun PasarMenu() {
        val intent = Intent(this, PromedioActivity::class.java)
        startActivity(intent)
    }
}
