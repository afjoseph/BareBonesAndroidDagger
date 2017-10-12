# Bare-Bones Android Dagger
This is a simple app to demonstrate the use of the following technologies together:

- Kotlin 1.1.4
- Dagger2 2.12 with the [`android-dagger` extension](https://github.com/google/dagger#android-gradle)
- Android Studio 3 beta 7
- Gradle 3.0.0 beta 7
- Inherited activities provide custom dependencies
- Base activities provide common dependencies

The idea is to make a simple app that allows for custom activity hierarchy.

The hierarchy goes as follows:
- **Basic Level**: Things that are shared dependencies for all activities, fragments and for the custom Application class
- **Custom Level**: Things that are custom dependencies for a specific activity or fragment or a set of those two types.

## Class Breakdown: `ApplicationComponent`

```  
@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        ActivityBuilder::class,
        ApplicationModule::class
))
interface ApplicationComponent : AndroidInjector<Application> {
    fun inject(app: MyApplication)
}
  
```

Here, you will find the basic necessary modules for an Application component. `AndroidInjectionModule` is part of the new `android-dagger` extension.

## Class Breakdown: `ApplicationModule`

```  
@Module
class ApplicationModule {
    @Provides
    @Singleton
    fun providesCommonStuff() = Db()

    @Provides
    @Singleton
    fun providesApi() = Api()
}
```

This is the **Basic Level** of the dependency hierarchy identified above, namely the dependencies that would be shared by all activities, fragments, and application class.

## Class Breakdown: `MainActivityModule`  
  
```  
@Module
class MainActivityModule {
    @Provides
    fun provideAudioPlayer(activity: MainActivity): AudioPlayer = AudioPlayer(activity)
}  
```

This is the **Custom Level** of the dependency hierarchy identified above, namely the dependencies that would be used by a specific activity or a set of activities (or fragments). In this case, `MainActivityModule` is specific to `MainActivity`.  

## Class Breakdown: `ActivityBuilder`

```
@Module
abstract class ActivityBuilder {
    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun providesMainActivityInjector(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun providesOtherActivityInjector(): OtherActivity
}
```

`ActivityBuilder` is responsible for designating all activities (or fragments) that would need to be injected. Notice that you can add a custom module, which we did in the case of `providesMainActivityInjector()`. This is not necessary, of course. `OtherActivity`, for instance, doesn't need any dependencies other than the basic ones it gets by default from `ApplicationModule`, so there is no need for a special Module for it.

## Class Breakdown: `MainActivity`

```  
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
```

`MainActivity` extends `BaseActivity` (see below). The Dagger injection happens in `BaseActivity`, so `MainActivity` doesn't need to worry about that part. It just adds an `@Inject lateinit var` to whatever component needs to be injected and the structure we've built will take care of the rest. 

Notice here that the two dependencies, `api: Api` and `db: Db` are common dependencies following the **Basic Level** of the dependency hierarchy. They are provided using `ApplicationModule`. 

In contrast, there is a third dependency that is special only for `MainActivity`, which is `audioPlayer: AudioPlayer`. This dependency is provided by `MainActivityModule`.

# Class Breakdown: `BaseActivity`

```
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
    }
}
```

As mentioned above, `BaseActivity` is responsible for handling the Dagger injection. No need for any special overrides to the `onCreate()` in the inheritance chain.

# Class Breakdown: `MyApplication`

```
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
```

So `MyApplication` has a few extra things from your regular vanilla Dagger2 implementation. Notice the interface implementation of `HasActivityInjector`. This setup is basic boilerplate for having `android-dagger` initialized. If you get errors regarding a missing `DaggerApplicationComponent`, just rebuild the project and make sure your `build.gradle` includes the necessary `kapt` dependencies (see project's `build.gradle`).
