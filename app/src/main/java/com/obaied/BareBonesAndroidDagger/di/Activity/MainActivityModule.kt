package com.joseph.BareBonesAndroidDagger.di.Activity

import com.joseph.BareBonesAndroidDagger.ui.MainActivity
import com.joseph.BareBonesAndroidDagger.audio.AudioPlayer
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
