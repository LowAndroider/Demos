package com.exam.category.live

import android.graphics.drawable.LevelListDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.exam.category.R
import com.exam.category.entity.Movie
import com.exam.category.widget.media.AndroidMediaController
import kotlinx.android.synthetic.main.activity_live.*
import tv.danmaku.ijk.media.player.IjkMediaPlayer

/**
 * @author djh
 * @date 2018年09月17日 20:06:53
 */
class LiveActivity : AppCompatActivity() {

    private var controlDrawable: LevelListDrawable? = null

    private var mediaController: AndroidMediaController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live)

        ivBack.setOnClickListener { finish() }
        controlDrawable = ivVideoControl.drawable as LevelListDrawable

        ivVideoControl.setOnClickListener {
            if (controlDrawable?.level == 1) {
                controlDrawable?.level = 2
                startOrContinue()
            } else {
                0
                controlDrawable?.level = 1
                pause()
            }
        }

        val movie = intent.getParcelableExtra<Movie>("movie")

        mediaController = AndroidMediaController(this, false)

        // init player
        IjkMediaPlayer.loadLibrariesOnce(null)
        IjkMediaPlayer.native_profileBegin("libijkplayer.so")
        ijkVideoView.setMediaController(mediaController)
        ijkVideoView.setVideoPath(movie.data)
        ijkVideoView.setHudView(hudView)
        ijkVideoView.setOnPreparedListener {
            rlLoading.visibility = View.GONE
            it.start()
        }
    }

    private fun startOrContinue() {
    }

    private fun pause() {
        ijkVideoView.pause()
    }

    override fun onPause() {
        super.onPause()
        ijkVideoView.pause()
    }
}
