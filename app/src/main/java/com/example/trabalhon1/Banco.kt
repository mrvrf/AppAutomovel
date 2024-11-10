package com.example.trabalhon1

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Banco(context: Context) : SQLiteOpenHelper(context, NAME , null, VERSION) {
    companion object{
        private const val VERSION = 1
        private const val NAME = "automoveis.db"
        public  const val AUTOMOVEL = "automovel"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS " + AUTOMOVEL + " ( " +
            " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , " +
            " marca TEXT NOT NULL , " +
            " modelo TEXT NOT NULL , " +
            " ano INTEGER , " +
            " cor TEXT NOT NULL ); "
        )
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }
}