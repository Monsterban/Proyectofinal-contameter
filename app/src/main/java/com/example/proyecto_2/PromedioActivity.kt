package com.example.proyecto_2

import ViewModel.MainViewModel
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.echo.holographlibrary.PieGraph
import com.echo.holographlibrary.PieSlice
import com.ekn.gruzer.gaugelibrary.ArcGauge
import com.ekn.gruzer.gaugelibrary.Range
import com.example.proyecto_2.databinding.AppBarMainBinding
import com.example.proyecto_2.databinding.PromedioBinding
import com.google.android.material.navigation.NavigationView
import kotlin.math.roundToInt

class PromedioActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: PromedioBinding
    private lateinit var barMainBinding: AppBarMainBinding

    private var listapromedio = ArrayList<Huella>()
    private lateinit var journey1: Button
    private lateinit var alimento: Button
    private lateinit var idmedidor: ArcGauge
    private lateinit var gra: Button

    private lateinit var piegrafica: PieGraph
    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var rango1: com.ekn.gruzer.gaugelibrary.Range
    private lateinit var rango2: com.ekn.gruzer.gaugelibrary.Range
    private lateinit var rango3: com.ekn.gruzer.gaugelibrary.Range
    private lateinit var mainViewModel: MainViewModel
    var setear: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            binding = PromedioBinding.inflate(layoutInflater)
            setContentView(binding.root)
            gra = findViewById(R.id.btn_graficar)

            journey1 = findViewById(R.id.btn_agregarV)
            alimento = findViewById(R.id.btn_agregarA)
            idmedidor = findViewById(R.id.arcGauge)

            // Log: ViewModel initialization
            Log.d("PromedioActivity", "Initializing ViewModel")
            mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

            val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_main)
            setSupportActionBar(toolbar)

            drawer = findViewById(R.id.drawer_layout)
            toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
            drawer.addDrawerListener(toggle)

            journey1.setOnClickListener {
                Log.d("PromedioActivity", "Journey1 button clicked")
                PasarActividadDetalle()
            }
            alimento.setOnClickListener {
                Log.d("PromedioActivity", "Alimento button clicked")
                PasarAlimento()
            }

            val rango1 = Range().apply {
                color = Color.parseColor("#00b20b")
                from = 0.0
                to = 50.0
            }

            val range2 = Range().apply {
                color = Color.parseColor("#FFFF00")
                from = 50.0
                to = 100.0
            }

            val range3 = Range().apply {
                color = Color.parseColor("#ce0000")
                from = 100.0
                to = 150.0
            }

            // Añadir los rangos de color al gauge
            idmedidor.addRange(rango1)
            idmedidor.addRange(range2)
            idmedidor.addRange(range3)

            // Configurar el valor mínimo, máximo y actual del gauge
            idmedidor.minValue = 0.0
            idmedidor.maxValue = 150.0


            // Observer para el valor acumulado de CO2e
            gra.setOnClickListener{
                Toast.makeText(this, "grafica", Toast.LENGTH_SHORT).show()

                idmedidor.value = MainViewModel.getInstance()._accumulatedCo2e.value ?: 0.0
                Toast.makeText(this," $(mainViewModel._accumulatedCo2e.value)", Toast.LENGTH_SHORT).show()
                Log.e("PromedioActivity","Error:${mainViewModel._accumulatedCo2e.value}" )
            }
//            mainViewModel._accumulatedCo2e.observe(this, Observer { accumulatedCo2e ->
//                idmedidor.value = mainViewModel._accumulatedCo2e.value ?: 1.0
//                Toast.makeText(this, "Valor actualizado: $accumulatedCo2e", Toast.LENGTH_SHORT).show()
//                Log.d("PromedioActivity", "Valor actualizado: $accumulatedCo2e")
//            })

            // Observer para el último valor de CO2e




            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeButtonEnabled(true)
        } catch (e: Exception) {
            Log.e("PromedioActivity", "Error in onCreate: ${e.message}")
        }
    }
    private fun updateArcGauge(value: Double) {
        idmedidor.value = value
    }

    private fun showLatestValue(value: Double) {
        Toast.makeText(this, "Último valor de CO2e: $value", Toast.LENGTH_SHORT).show()
    }

//    private fun crearRequestBody(dataVersion: String): RequestBody {
//        val json = """
//            {
//                "data_version": "$dataVersion"
//
//            }
//        """.trimIndent()
//        return json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
//    }


    private fun PasarActividadDetalle() {
        // Crear un Intent para la actividad de destino
        val intent = Intent(this, Viaje::class.java)

        // Iniciar la actividad usando el Intent
        startActivity(intent)
    }

    private fun PasarAlimento() {
        // Crear un Intent para la actividad de destino
        val intent = Intent(this, Alimentos::class.java)

        // Iniciar la actividad usando el Intent
        startActivity(intent)
    }

    fun graficarpie(){
        for(i in 0 until listapromedio.size){
            val rebanada = PieSlice()
            rebanada.color=Color.parseColor(listapromedio[i].color)
            rebanada.value = listapromedio[i].cantidad.toString().toFloat()
            piegrafica.addSlice(rebanada)
        }
    }

    fun generarcolor(): String{
        var letras = arrayOf("0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F")
        var color = "#"
        for(i in 0..5){
            color+= letras[(Math.random()*15).roundToInt()]

        }
        return color
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.Cuenta1 -> {
                    PasarCuenta()

                return true
            }
            R.id.nav_item_dos -> Toast.makeText(this, "Item 2", Toast.LENGTH_SHORT).show()
            R.id.nav_item_tres -> Toast.makeText(this, "Item 3", Toast.LENGTH_SHORT).show()
        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?){
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }
    private fun PasarCuenta() {
        Log.d("PasarCuenta", "Se ha llamado al método PasarCuenta()")
        // Crear un Intent para la actividad de destino
        val intent = Intent(this, Cuenta::class.java)

        // Iniciar la actividad usando el Intent
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}