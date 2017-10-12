package com.obaied.BareBonesAndroidDagger.ui

import android.os.Bundle
import com.obaied.BareBonesAndroidDagger.R
import com.obaied.BareBonesAndroidDagger.common.Api
import com.obaied.BareBonesAndroidDagger.common.Db
import kotlinx.android.synthetic.main.activity_other.*
import javax.inject.Inject

/**
 * Created by ab on 12.10.17.
 */
class OtherActivity : BaseActivity() {
    @Inject lateinit var api: Api
    @Inject lateinit var db: Db

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)

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
    }
}
