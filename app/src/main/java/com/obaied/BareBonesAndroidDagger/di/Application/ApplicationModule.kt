package com.obaied.BareBonesAndroidDagger.di.Application

import com.obaied.BareBonesAndroidDagger.common.Api
import com.obaied.BareBonesAndroidDagger.common.Db
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by ab on 12.10.17.
 */

@Module
class ApplicationModule {
    @Provides
    @Singleton
    fun providesCommonStuff() = Db()

    @Provides
    @Singleton
    fun providesApi() = Api()
}