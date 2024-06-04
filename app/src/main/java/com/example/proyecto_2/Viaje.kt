package com.example.proyecto_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class Viaje : AppCompatActivity() {

    private lateinit var Menu: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.journey)
        Menu = findViewById(R.id.btn_cancelar)

        Menu.setOnClickListener(){
            PasarMenu()
        }

    }


    private fun PasarMenu() {
        // Crear un Intent para la actividad de destino
        val intent = Intent(this, PromedioActivity::class.java)

        // Iniciar la actividad usando el Intent
        startActivity(intent)
    }
}