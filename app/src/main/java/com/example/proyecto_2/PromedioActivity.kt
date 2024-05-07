package com.example.proyecto_2

import android.app.Activity
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.echo.holographlibrary.PieGraph
import com.echo.holographlibrary.PieSlice
import com.example.proyecto_2.databinding.ActivityMainBinding
import com.example.proyecto_2.databinding.AppBarMainBinding
import com.example.proyecto_2.databinding.PromedioBinding
import com.google.android.material.navigation.NavigationView
import kotlin.math.roundToInt

class PromedioActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private lateinit var binding: PromedioBinding
    private lateinit var barMainBinding: AppBarMainBinding

    private var listapromedio = ArrayList<Huella>()

    private lateinit var piegrafica: PieGraph
    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PromedioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

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
            R.id.nav_item_uno -> Toast.makeText(this, "Item 1", Toast.LENGTH_SHORT).show()
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}