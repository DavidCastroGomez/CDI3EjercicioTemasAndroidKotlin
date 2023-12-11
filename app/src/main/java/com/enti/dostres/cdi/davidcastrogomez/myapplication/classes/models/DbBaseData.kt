package com.enti.dostres.cdi.davidcastrogomez.myapplication.classes.models

interface DbBaseData {
    var id: String?

    fun getTable(): String
}