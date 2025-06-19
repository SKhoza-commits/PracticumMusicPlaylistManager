package com.example.musicplaylistmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import vcmsa.ci.musicplaylistmanager.R

class PlaylistActivity : AppCompatActivity() {
    private lateinit var outputText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_playlist)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        outputText = findViewById(R.id.tvPlaylistOutput)
        val playlistNameInput = findViewById<EditText>(R.id.etPlaylistName)
        val createPlaylistBtn = findViewById<Button>(R.id.btnCreatePlaylist)
        val addToPlaylistBtn = findViewById<Button>(R.id.btnAddToPlaylist)
        val viewPlaylistsBtn = findViewById<Button>(R.id.btnViewPlaylists)
        val backBtn = findViewById<Button>(R.id.btnBack)

        createPlaylistBtn.setOnClickListener {
            val name = playlistNameInput.text.toString().trim()
            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter a playlist name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            MainActivity.playlistNames.add(name)
            MainActivity.playlists.add(mutableListOf())
            playlistNameInput.text.clear()
            Toast.makeText(this, "Playlist created: $name", Toast.LENGTH_SHORT).show()
        }

        addToPlaylistBtn.setOnClickListener {
            if (MainActivity.songTitles.isEmpty()) {
                Toast.makeText(this, "No songs available to add", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (MainActivity.playlistNames.isEmpty()) {
                Toast.makeText(this, "No playlists available", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            startActivity(Intent(this, AddToPlaylistActivity::class.java))
        }

        viewPlaylistsBtn.setOnClickListener {
            displayPlaylists()
        }

        backBtn.setOnClickListener {
            finish()
        }
    }

    private fun displayPlaylists() {
        val builder = StringBuilder()

        if (MainActivity.playlistNames.isEmpty()) {
            builder.append("No playlists created yet.")
        } else {
            for (i in MainActivity.playlistNames.indices) {
                builder.append("Playlist: ${MainActivity.playlistNames[i]}\n")
                builder.append("Songs (${MainActivity.playlists[i].size}):\n")

                if (MainActivity.playlists[i].isEmpty()) {
                    builder.append("  No songs in this playlist\n")
                } else {
                    for (songIndex in MainActivity.playlists[i]) {
                        builder.append("  - ${MainActivity.songTitles[songIndex]} by ${MainActivity.artists[songIndex]}\n")
                    }
                }
                builder.append("\n")
            }
        }

        outputText.text = builder.toString()
    }
}