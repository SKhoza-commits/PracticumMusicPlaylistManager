package com.example.musicplaylistmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import vcmsa.ci.musicplaylistmanager.R

class MainActivity : AppCompatActivity() {
    companion object {
        val songTitles = mutableListOf<String>()
        val artists = mutableListOf<String>()
        val albums = mutableListOf<String>()
        val ratings = mutableListOf<Float>()
        val comments = mutableListOf<String>()
        val playlists = mutableListOf<MutableList<Int>>() // Stores indices of songs in each playlist
        val playlistNames = mutableListOf<String>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val titleInput = findViewById<EditText>(R.id.etSongTitle)
        val artistInput = findViewById<EditText>(R.id.etArtist)
        val albumInput = findViewById<EditText>(R.id.etAlbum)
        val ratingInput = findViewById<RatingBar>(R.id.ratingBar)
        val commentInput = findViewById<EditText>(R.id.etComments)

        val addButton = findViewById<Button>(R.id.btnAdd)
        val managePlaylistsButton = findViewById<Button>(R.id.btnManagePlaylists)
        val viewSongsButton = findViewById<Button>(R.id.btnViewSongs)
        val exitButton = findViewById<Button>(R.id.btnExit)

        addButton.setOnClickListener {
            val title = titleInput.text.toString().trim()
            val artist = artistInput.text.toString().trim()
            val album = albumInput.text.toString().trim()
            val rating = ratingInput.rating
            val comment = commentInput.text.toString().trim()

            if (title.isEmpty() || artist.isEmpty()) {
                Toast.makeText(this, "Title and Artist are required fields.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (songTitles.size >= 4) {
                Toast.makeText(this, "Maximum of 4 songs reached.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            songTitles.add(title)
            artists.add(artist)
            albums.add(album)
            ratings.add(rating)
            comments.add(comment)

            Toast.makeText(this, "Song added!", Toast.LENGTH_SHORT).show()

            // Clear inputs
            titleInput.text.clear()
            artistInput.text.clear()
            albumInput.text.clear()
            ratingInput.rating = 0f
            commentInput.text.clear()
        }

        managePlaylistsButton.setOnClickListener {
            startActivity(Intent(this, PlaylistActivity::class.java))
        }

        viewSongsButton.setOnClickListener {
            startActivity(Intent(this, ViewSongsActivity::class.java))
        }

        exitButton.setOnClickListener {
            finish()
        }
    }
}