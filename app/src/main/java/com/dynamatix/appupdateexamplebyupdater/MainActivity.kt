package com.dynamatix.appupdateexamplebyupdater

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.javiersantos.appupdater.AppUpdater
import com.github.javiersantos.appupdater.enums.UpdateFrom
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        initializeAppUpdater()
        val versionName: String = "App version : " + getVersionName(this)
        Log.e("MainActivity.kt", "VersionName ==> " + getVersionName(this))
        version_name.setText(versionName)


    }

    private fun initializeAppUpdater() {
        val appUpdater = AppUpdater(this)
        appUpdater.start()
        appUpdater.showEvery(5)
        appUpdater.setUpdateFrom(UpdateFrom.GITHUB)
        appUpdater.setGitHubUserAndRepo("bharathi3991", "AppUpdateExampleByUpdater")
    }

    fun getVersionName(context: Context): String {
        val pm = context.packageManager
        try {
            val pi = pm.getPackageInfo(context.packageName, 0)
            return (pi.versionName)
        } catch (ex: PackageManager.NameNotFoundException) {
        }
        return ""
    }
}