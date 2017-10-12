package com.obaied.BareBonesAndroidDagger.di.Activity

import com.obaied.BareBonesAndroidDagger.ui.MainActivity
import com.obaied.BareBonesAndroidDagger.ui.OtherActivity
import com.obaied.BareBonesAndroidDagger.di.Scopes.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by ab on 12.10.17.
 */
@Module
abstract class ActivityBuilder {
    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun providesMainActivityInjector(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun providesOtherActivityInjector(): OtherActivity
}
