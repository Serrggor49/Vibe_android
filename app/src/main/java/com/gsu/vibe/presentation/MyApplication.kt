package com.gsu.vibe.presentation

import android.app.Application
import io.branch.referral.Branch
import io.branch.referral.BranchError
import org.json.JSONObject

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()


        Branch.getAutoInstance(this);

    }


}