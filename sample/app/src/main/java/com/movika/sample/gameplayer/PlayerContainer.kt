package com.movika.sample.gameplayer

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import film.interactive.player.sdk.android.base.Config
import film.interactive.player.sdk.android.base.InteractiveFactory
import film.interactive.player.sdk.android.base.listener.OnInteractiveEndListener
import film.interactive.player.sdk.android.base.listener.OnInteractiveStartListener
import film.interactive.player.sdk.android.base.model.MovieBundle
import film.interactive.player.sdk.android.base.tools.runner.SimpleInteractivePlayerViewRunner
import film.interactive.player.sdk.android.base.ui.InteractivePlayerView

/**
 * @author Bulat Motygullin bul3515@gmail.com
 * 09.01.2020
 */
class PlayerContainer : FrameLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    var playerView: InteractivePlayerView? = null
        private set

    private val config = Config(
        isLoop = true,
        isDebugMode = false,
        isShowTimeBar = true,
        isUseDefaultPlayPauseController = true,
        cacheStartPartsOfNextChapters = true,
        isSeekEnabled = false
    )

    fun conceivePlayer(
        movieBundle: MovieBundle?,
        customInteractiveContainer: ViewGroup? = null,
        startEventListener: OnInteractiveStartListener? = null,
        endEventListener: OnInteractiveEndListener? = null,
        factory: InteractiveFactory? = null
    ) {
        abortPlayer()
        val newPlayer = InteractivePlayerView(context)
            .apply {
                interactiveFactory = factory
                startEventListener?.let(::addOnInteractiveStartListener)
                endEventListener?.let(::addOnInteractiveEndListener)
                customInteractiveContainer?.let {
                    customEventContainer = it
                }
            }

        val runner = SimpleInteractivePlayerViewRunner(context)
        this.playerView = newPlayer
        addView(newPlayer)
        movieBundle?.let {
            runner.run(newPlayer, movieBundle, config)
        }
    }

    fun abortPlayer() {
        playerView?.let {
            it.onPause()
            it.onDestroy()
            removeView(it)
        }
        this.playerView = null
    }

}