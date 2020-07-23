package com.dynamatix.appupdateexamplebyupdater

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        val versionName : String = "App version : "+getVersionName(this)
        Log.e("MainActivity.kt","VersionName ==> "+getVersionName(this))
        version_name.setText(versionName)
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