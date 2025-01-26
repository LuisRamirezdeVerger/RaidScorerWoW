package com.wito.raidscorerapp.utils

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wito.raidscorerapp.model.Player
import java.io.File


object JsonUtils {

    private const val JSON = "players.json"

    //Save player list on JSON file
    fun savePlayersToFile (context: Context, players: List<Player>){
        val gson = Gson()
        val jsonString = gson.toJson(players)
        val file = File(context.filesDir, JSON)
        file.writeText(jsonString)
    }

    //Load player list from JSON
    fun loadPlayersFromFile(context: Context): List<Player>{
        val file = File(context.filesDir, JSON)
        if (!file.exists()){
            return emptyList()
        }
        val jsonString = file.readText()
        val gson = Gson()
        val type = object : TypeToken<List<Player>>() { }.type
        return gson.fromJson(jsonString, type)
    }

    //Delete players from list
    fun removePlayersAndSave(context: Context, player: Player, players: MutableList<Player>){
        try {
            players.remove(player)
            savePlayersToFile(context, players)
        } catch (e: Exception){
            Log.e("RemovePlayer", "Error al eliminar el jugador, ${e.message}")
        }
    }
}