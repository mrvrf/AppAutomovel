package com.example.trabalhon1

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log


class AutoD {

    companion object {
        fun insert(context: Context, auto: Automovel) {
            val conn = Banco(context)
            val db = conn.writableDatabase
            val values = ContentValues().apply {
                put("marca", auto.marca)
                put("modelo", auto.modelo)
                put("ano", auto.ano)
                put("cor", auto.cor)
            }
            db.insert(Banco.AUTOMOVEL, null, values)
            db.close()
        }

        fun edit(context: Context, auto: Automovel) {
            val conn = Banco(context)
            val db = conn.writableDatabase
            val values = ContentValues().apply {
                put("marca", auto.marca)
                put("modelo", auto.modelo)
                put("ano", auto.ano)
                put("cor", auto.cor)
            }
            db.update(Banco.AUTOMOVEL, values, "id = " + auto.id, null)
            db.close()
        }

        fun delete(context: Context, id: Int) {
            val conn = Banco(context)
            val db = conn.writableDatabase
            val rowsDeleted = db.delete(Banco.AUTOMOVEL, "id = ?", arrayOf(id.toString()))
            db.close()
            Log.d("AutoD", "Linhas deletadas: $rowsDeleted")
        }

        fun getAutomoveis(context: Context): MutableList<Automovel> {
            val automoveis = mutableListOf<Automovel>()
            val conn = Banco(context)
            val db = conn.readableDatabase
            val query = "SELECT * FROM " + Banco.AUTOMOVEL + " ORDER BY marca"
            val cursor: Cursor = db.rawQuery(query, null)
            if (cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    val auto = Automovel()
                    auto.id = cursor.getInt(0)
                    auto.marca = cursor.getString(1)
                    auto.modelo = cursor.getString(2)
                    auto.ano = cursor.getInt(3)
                    auto.cor = cursor.getString(4)
                    automoveis.add( auto )
                } while (cursor.moveToNext())
            }
            cursor.close()
            conn.close()
            return automoveis
        }

        /*fun getProductById(context: Context, id: Int): Automovel?{
            var auto: Automovel? = null
            val conn = Banco(context)
            val db = conn.writableDatabase
            val query = "SELECT * FROM " + Banco.AUTOMOVEL + " WHERE id = ?"
            val cursor: Cursor = db.rawQuery( query, arrayOf( id.toString() ) )
            if ( cursor.count > 0){
                cursor.moveToFirst()
                auto = Automovel()
                auto.id = cursor.getInt(0)
                auto.marca = cursor.getString(1)
                auto.modelo = cursor.getString(2)
                auto.ano = cursor.getInt(3)
                auto.cor = cursor.getString(4)
            }
            cursor.close()
            conn.close()
            return auto
        }*/

        fun aExist( context: Context, marca: String): Boolean{
            var exist: Boolean = false
            val conn = Banco(context)
            val db = conn.readableDatabase
            val query = "SELECT * FROM " + Banco.AUTOMOVEL + " WHERE marca = ?"
            val cursor: Cursor = db.rawQuery( query, arrayOf( marca ) )
            if ( cursor.count > 0 ){
                exist = true
            }
            cursor.close()
            conn.close()
            return exist
        }
    }
}