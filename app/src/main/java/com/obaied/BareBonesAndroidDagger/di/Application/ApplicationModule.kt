package com.joseph.BareBonesAndroidDagger.di.Application

import com.joseph.BareBonesAndroidDagger.common.Api
import com.joseph.BareBonesAndroidDagger.common.Db
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