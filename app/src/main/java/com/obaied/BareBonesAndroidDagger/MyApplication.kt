package com.obaied.BareBonesAndroidDagger

import android.app.Activity
import android.app.Application
import com.obaied.BareBonesAndroidDagger.common.Db
import com.obaied.BareBonesAndroidDagger.di.Application.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by ab on 12.10.17.
 */

class MyApplication : Application(),
        HasActivityInjector {
    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var db: Db

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent
                .create()
                .inject(this)

        initDb()
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    private fun initDb() {
        db.initDb()
    }
}