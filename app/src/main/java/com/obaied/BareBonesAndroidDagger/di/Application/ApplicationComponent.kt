package com.obaied.BareBonesAndroidDagger.di.Application

import android.app.Application
import com.obaied.BareBonesAndroidDagger.MyApplication
import com.obaied.BareBonesAndroidDagger.di.Activity.ActivityBuilder
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Created by ab on 12.10.17.
 */
@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        ActivityBuilder::class,
        ApplicationModule::class
))
interface ApplicationComponent : AndroidInjector<Application> {
    fun inject(app: MyApplication)
}
