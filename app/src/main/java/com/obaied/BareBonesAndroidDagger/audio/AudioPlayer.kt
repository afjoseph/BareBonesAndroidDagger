package com.obaied.BareBonesAndroidDagger.audio

import android.app.Activity
import android.widget.Toast

/**
 * Created by ab on 12.10.17.
 */
class AudioPlayer(private val context: Activity) {
    fun doToast() {
        Toast.makeText(context, "Running Audio player...", Toast.LENGTH_SHORT).show()
    }
}
