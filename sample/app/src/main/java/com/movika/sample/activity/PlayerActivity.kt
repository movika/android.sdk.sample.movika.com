package com.movika.sample.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.movika.sample.R
import film.interactive.player.sdk.android.base.Config
import film.interactive.player.sdk.android.base.tools.loader.AsyncMovieBundleLoader
import film.interactive.player.sdk.android.base.tools.loader.MovieBundleLoader
import film.interactive.player.sdk.android.base.ui.InteractivePlayerView

class PlayerActivity : AppCompatActivity() {

    private lateinit var interactivePlayerView: InteractivePlayerView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)
        interactivePlayerView = findViewById(R.id.interactivePlayer)
        val eventContainer: ViewGroup = findViewById(R.id.eventContainer)
        interactivePlayerView.customEventContainer = eventContainer
        val config = Config(
            isLoop = true,
            isDebugMode = false,
            isShowTimeBar = true,
            isUseDefaultPlayPauseController = true,
            isCacheStartPartsOfNextChapters = true,
            isSeekEnabled = false
        )

        lifecycle.addObserver(interactivePlayerView)

        val loader: MovieBundleLoader = AsyncMovieBundleLoader()
        loader.load("https://asazin-cache.cdnvideo.ru/asazin/movika/prod/users/1/movie/10/manifest.json") {
            if (it.isComplete) {
                Handler(Looper.getMainLooper()).post {
                    interactivePlayerView.run(it.data!!, config, savedInstanceState)
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        interactivePlayerView.onSaveInstanceState(outState)
    }
}
