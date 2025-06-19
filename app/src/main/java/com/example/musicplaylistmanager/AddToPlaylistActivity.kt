package com.example.musicplaylistmanager

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


class AddToPlaylistActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_to_playlist)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val songListText = findViewById<TextView>(R.id.tvSongList)
        val playlistListText = findViewById<TextView>(R.id.tvPlaylistList)
        val songInput = findViewById<EditText>(R.id.etSongNumber)
        val playlistInput = findViewById<EditText>(R.id.etPlaylistNumber)
        val addButton = findViewById<Button>(R.id.btnAddSelection)
        val backButton = findViewById<Button>(R.id.btnBack)

        // Display available songs
        val songBuilder = StringBuilder()
        songBuilder.append("Available Songs:\n")
        for (i in MainActivity.songTitles.indices) {
            songBuilder.append("${i+1}. ${MainActivity.songTitles[i]} - ${MainActivity.artists[i]}\n")
        }
        songListText.text = songBuilder.toString()

        // Display available playlists
        val playlistBuilder = StringBuilder()
        playlistBuilder.append("Available Playlists:\n")
        for (i in MainActivity.playlistNames.indices) {
            playlistBuilder.append("${i+1}. ${MainActivity.playlistNames[i]}\n")
        }
        playlistListText.text = playlistBuilder.toString()

        addButton.setOnClickListener {
            val songNumber = songInput.text.toString().toIntOrNull()
            val playlistNumber = playlistInput.text.toString().toIntOrNull()

            if (songNumber == null || playlistNumber == null) {
                Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (songNumber < 1 || songNumber > MainActivity.songTitles.size) {
                Toast.makeText(this, "Invalid song number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (playlistNumber < 1 || playlistNumber > MainActivity.playlistNames.size) {
                Toast.makeText(this, "Invalid playlist number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val songIndex = songNumber - 1
            val playlistIndex = playlistNumber - 1

            if (MainActivity.playlists[playlistIndex].contains(songIndex)) {
                Toast.makeText(this, "Song already in playlist", Toast.LENGTH_SHORT).show()
            } else {
                MainActivity.playlists[playlistIndex].add(songIndex)
                Toast.makeText(this, "Song added to playlist", Toast.LENGTH_SHORT).show()
                songInput.text.clear()
                playlistInput.text.clear()
            }
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}