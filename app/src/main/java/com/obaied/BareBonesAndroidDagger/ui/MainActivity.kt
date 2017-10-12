package com.obaied.BareBonesAndroidDagger.ui

import android.content.Intent
import android.os.Bundle
import com.obaied.BareBonesAndroidDagger.R
import com.obaied.BareBonesAndroidDagger.audio.AudioPlayer
import com.obaied.BareBonesAndroidDagger.common.Api
import com.obaied.BareBonesAndroidDagger.common.Db
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject lateinit var api: Api
    @Inject lateinit var db: Db
    @Inject lateinit var audioPlayer: AudioPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_run_api.let {
            it.setOnClickListener {
                api.validateUser()
            }
        }

        btn_run_db.let {
            it.setOnClickListener {
                db.fetchData()
            }
        }

        btn_run_audio_player.let {
            it.setOnClickListener {
                audioPlayer.doToast()
            }
        }

        btn_goto_next_activity.let {
            it.setOnClickListener {
                this.startActivity(Intent(this, OtherActivity::class.java))
            }
        }

    }
}
