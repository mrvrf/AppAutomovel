package com.example.trabalhon1

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Listagem : AppCompatActivity() {

    private lateinit var btnVoltar: Button
    private lateinit var lvAutos: ListView
    private lateinit var listAutos: MutableList<Automovel>
    private lateinit var adapAutos: ArrayAdapter<Automovel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listagem)

        btnVoltar = findViewById(R.id.btnVoltar)
        lvAutos = findViewById(R.id.lvAutomoveis)

        loadAutos()

        lvAutos.setOnItemClickListener { _, _, position, _ ->
            val auto = listAutos[position]
            val intent = Intent(this, AutomovelA::class.java).apply {
                putExtra("id", auto.id)
                putExtra("marca", auto.marca)
                putExtra("modelo", auto.modelo)
                putExtra("ano", auto.ano)
                putExtra("cor", auto.cor)
            }
            startActivity(intent)
        }

        /*lvAutos.setOnItemClickListener { _ , _ , position, _ ->
            delete( position )
        }*/

        btnVoltar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.listagem)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }

    private fun loadAutos(){
        listAutos = AutoD.getAutomoveis(this)
        if ( listAutos.isEmpty() ){
            listAutos.add( Automovel("Nenhum automovel encontrado!", "", 0, ""))
            lvAutos.isEnabled = false
        } else {
            lvAutos.isEnabled = true
        }
        adapAutos = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, listAutos)
        lvAutos.adapter = adapAutos
    }


}