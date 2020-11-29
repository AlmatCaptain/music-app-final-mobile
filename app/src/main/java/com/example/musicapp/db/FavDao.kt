package com.example.musicapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.musicapp.model.Favourites

@Dao
interface FavDao {

    @Insert
    fun insertFav(favourites: Favourites)

    @Query("SELECT * FROM favs WHERE email =:email")
    fun getArtistsByEmail(email: String): List<Favourites>
}