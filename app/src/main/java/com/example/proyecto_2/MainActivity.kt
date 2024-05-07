package com.example.proyecto_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(){

    private lateinit var btnLogin: Button
    private lateinit var txt_user: EditText
    private lateinit var txt_password: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnLogin = findViewById(R.id.btn_Login)
        txt_user = findViewById(R.id.text_usuario)
        txt_password = findViewById(R.id.text_password)

        btnLogin.setOnClickListener{

            val usuario = txt_user.text.toString()
            val contrasena = txt_password.text.toString()

            if (usuario == "santi" && contrasena == "1"){
                startActivity(Intent(this, PromedioActivity::class.java))
                finish()
            }else{
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }

        }



    }

}