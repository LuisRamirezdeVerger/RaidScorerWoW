package com.wito.raidscorerapp.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wito.raidscorerapp.model.Player
import java.io.File


object JsonUtils {

    private const val FILE_NAME = "players.json"

    //Save player list on JSON file
    fun savePlayersToFile (context: Context, players: List<Player>){
        val gson = Gson()
        val jsonString = gson.toJson(players)
        val file = File(context.filesDir, FILE_NAME)
        file.writeText(jsonString)
    }

    //Load player list from JSON
    fun loadPlayersFromFile(context: Context): List<Player>{
        val file = File(context.filesDir, FILE_NAME)
        if (!file.exists()){
            return emptyList()
        }
        val jsonString = file.readText()
        val gson = Gson()
        val type = object : TypeToken<List<Player>>() { }.type
        return gson.fromJson(jsonString, type)
    }
}