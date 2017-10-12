package com.obaied.BareBonesAndroidDagger.di.Activity

import com.obaied.BareBonesAndroidDagger.ui.MainActivity
import com.obaied.BareBonesAndroidDagger.audio.AudioPlayer
import dagger.Module
import dagger.Provides

/**
 * Created by ab on 12.10.17.
 */
@Module
class MainActivityModule {
    @Provides
    fun provideAudioPlayer(activity: MainActivity): AudioPlayer = AudioPlayer(activity)
}
