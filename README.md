MUSIC PLAYLIST MANAGER
Github Link: 
https://github.com/SKhoza-commits/PracticumMusicPlaylistManager
Description
The Music Playlist Manager App is built for users to add their favorite songs to a maximum of 4, with them detailing the songs details and allowing them to create and manage playlists. Users are also enabled to rate the songs inserted, add comments and input information about the artist I the comment section.
How it works
 
The first page on the screen is where the users can add their songs, rate and comment. There are 4 buttons:
•	MainActivity.kt buttons – Declared the buttons and utilized a loop to alert user for error handling using the following Code below:
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

•	navigation to the next screen: The MainActivity code will return the setOnclickListener function when the user does in fact insert the right details, this was done along with the below code:

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


•	ViewSongsActivity.kt: When the user clicks on the view songs button the below code is used to navigate the user to the ViewSongsActivity.kt, provided with the screenshot below of how that screen will look like:
viewSongsButton.setOnClickListener {
    startActivity(Intent(this, ViewSongsActivity::class.java))
}

 

•	PlaylistActivity.kt will appear if the user clicks the manage playlist button of which the following code was used along with the screenshot:
managePlaylistsButton.setOnClickListener {
    startActivity(Intent(this, PlaylistActivity::class.java))
}

 

•	AddToPlaylist.kt: The below code was utilized for the user to navigate the AddToPlaylist.kt page as well as error handling implemented along with the screenshot of how the screen looks like:
•	createPlaylistBtn.setOnClickListener {
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
 

•	View All playlist: If the user clicks on View all playlist, the playlist along with the songs will be displayed and this was done using the below code to also hand errors and alert the user:

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





Error Handling 

If The user does not enter the song title and artist or adds more than 4 songs the below will appear. I’ve also shown the code utilized:


if (title.isEmpty() || artist.isEmpty()) {
    Toast.makeText(this, "Title and Artist are required fields.", Toast.LENGTH_SHORT).show()
    return@setOnClickListener
}

if (songTitles.size >= 4) {
    Toast.makeText(this, "Maximum of 4 songs reached.", Toast.LENGTH_SHORT).show()
    return@setOnClickListener
}
 
 
References:
•	DeepSeek. (2024). DeepSeek Chat (Jun 19 version) [Large language model]. https://chat.deepseek.com/a/chat/s/e1da824c-1b37-46c1-a46c-4a48d2e128ef
•	DeepSeek. (2024). DeepSeek Chat (Jun 19 version) [Large language model]. https://chat.deepseek.com/a/chat/s/917a7467-c6d1-40fc-98f7-c78f6582d024
•	DeepSeek. (2024). DeepSeek Chat (Jun 19 version) [Large language model]. https://chat.deepseek.com/a/chat/s/3e2df38f-042d-47dc-8f7a-610dbb6e31d7
•	DeepSeek. (2024). DeepSeek Chat (Jun 19 version) [Large language model]. https://chat.deepseek.com/a/chat/s/c070d6ee-64b0-47f2-b042-d190438826c7
•	DeepSeek. (2024). DeepSeek Chat (Jun 19 version) [Large language model]. https://chat.deepseek.com/a/chat/s/f19182be-6916-4305-a968-c7f51b2ddfba

