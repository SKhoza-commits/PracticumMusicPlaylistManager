package com.example.musicplaylistmanager

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import vcmsa.ci.musicplaylistmanager.R

class ViewSongsActivity : AppCompatActivity() {
    private lateinit var outputText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_songs)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        outputText = findViewById(R.id.tvSongsOutput)
        val viewAllBtn = findViewById<Button>(R.id.btnViewAll)
        val viewHighlyRatedBtn = findViewById<Button>(R.id.btnViewHighlyRated)
        val backBtn = findViewById<Button>(R.id.btnBack)

        viewAllBtn.setOnClickListener {
            displaySongs(showAll = true)
        }

        viewHighlyRatedBtn.setOnClickListener {
            displaySongs(showAll = false)
        }

        backBtn.setOnClickListener {
            finish()
        }
    }

    private fun displaySongs(showAll: Boolean) {
        val builder = StringBuilder()

        if (MainActivity.songTitles.isEmpty()) {
            builder.append("No songs added yet.")
        } else {
            for (i in MainActivity.songTitles.indices) {
                if (showAll || MainActivity.ratings[i] >= 4) {
                    builder.append("Song: ${MainActivity.songTitles[i]}\n")
                    builder.append("Artist: ${MainActivity.artists[i]}\n")
                    builder.append("Album: ${MainActivity.albums[i]}\n")
                    builder.append("Rating: ${MainActivity.ratings[i]}/5\n")
                    builder.append("Comments: ${MainActivity.comments[i]}\n\n")
                }
            }
        }

        outputText.text = if (builder.isEmpty()) "No highly rated songs (4+ stars)." else builder.toString()
    }
}