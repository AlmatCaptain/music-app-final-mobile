package com.example.musicapp.activities

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapp.Firebase
import com.example.musicapp.R
import com.example.musicapp.adapters.FavAdapter
import com.example.musicapp.db.AppDatabase
import kotlinx.android.synthetic.main.activity_fav.*

class FavActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.logout_button -> {
            Firebase.auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun init() {
        if (Firebase.auth.currentUser?.email == null) {
            startActivity(
                Intent(
                    this,
                    LoginActivity::class.java
                )
            )
            finish()
        } else {
            AsyncTask.execute {
                val favs = AppDatabase.getInstance(applicationContext)!!
                    .getFavs()
                    .getArtistsByEmail(
                        Firebase.auth.currentUser?.email.toString()
                    )
                runOnUiThread {
                    recycler_favs.layoutManager = LinearLayoutManager(this)
                    recycler_favs.adapter =
                        FavAdapter(favs)
                }
            }
        }


    }
}
