package com.joseph.BareBonesAndroidDagger.di.Activity

import com.joseph.BareBonesAndroidDagger.ui.MainActivity
import com.joseph.BareBonesAndroidDagger.ui.OtherActivity
import com.joseph.BareBonesAndroidDagger.di.Scopes.ActivityScope
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
