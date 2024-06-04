package com.example.proyecto_2

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class Registro : AppCompatActivity() {
    private lateinit var btnRegistrar: Button
    private lateinit var correo: EditText
    private lateinit var txt_password: EditText
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_registro)
        txt_password = findViewById(R.id.txt_contraR)

        btnRegistrar = findViewById(R.id.btn_registrarR)
        correo = findViewById(R.id.editTextTextEmailAddress)


    }



}