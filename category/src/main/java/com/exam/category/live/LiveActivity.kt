package com.exam.category.live

import android.graphics.drawable.LevelListDrawable
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.exam.category.R
import com.exam.category.widget.media.AndroidMediaController
import kotlinx.android.synthetic.main.activity_live.*

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
                controlDrawable?.level = 1
                pause()
            }
        }



        mediaController = AndroidMediaController(this, false)
        ijkVideoView.setMediaController(mediaController)
        ijkVideoView.setVideoURI(Uri.parse("http://zv.3gv.ifeng.com/live/zhongwen800k.m3u8"))
        ijkVideoView.setOnPreparedListener {
            it.start()
        }
    }

    private fun startOrContinue() {

    }

    private fun pause() {

    }
}
