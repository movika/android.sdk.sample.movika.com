package com.movika.sample.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.movika.sample.R
import com.movika.sample.gameplayer.PlayerContainer
import film.interactive.player.sdk.android.base.tools.loader.AsyncMovieBundleLoader
import film.interactive.player.sdk.android.base.tools.loader.MovieBundleLoader

class PlayerActivity : AppCompatActivity() {

    private lateinit var playerContainer: PlayerContainer

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)
        playerContainer = findViewById(R.id.playerContainer)
        val loader : MovieBundleLoader = AsyncMovieBundleLoader()
        loader.load("https://asazin-cache.cdnvideo.ru/asazin/movika/prod/users/1/movie/10/manifest.json")
        {
            if (it.isComplete) {
                Handler(Looper.getMainLooper()).post {
                    playerContainer.conceivePlayer(it.data)
                    playerContainer.playerView?.onResume()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        playerContainer.abortPlayer()
    }
}