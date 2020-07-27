package com.dynamatix.appupdateexamplebyupdater

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.javiersantos.appupdater.AppUpdater
import com.github.javiersantos.appupdater.AppUpdaterUtils
import com.github.javiersantos.appupdater.AppUpdaterUtils.UpdateListener
import com.github.javiersantos.appupdater.enums.AppUpdaterError
import com.github.javiersantos.appupdater.enums.Display
import com.github.javiersantos.appupdater.enums.UpdateFrom
import com.github.javiersantos.appupdater.objects.Update
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {
    var versionName: String = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       /* val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)*/
       setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    //    initializeAppUpdater()
         versionName = "App version : " + getVersionName(this)
       // Log.e("MainActivity.kt", "VersionName ==> " + getVersionName(this))
        version_name.setOnClickListener {
            AppUpdater(this)
              ///  .setUpdateFrom(UpdateFrom.GITHUB)
              //  .setGitHubUserAndRepo("bharathi3991", "AppUpdateExampleByUpdater")
                .setUpdateFrom(UpdateFrom.JSON)
                .setUpdateJSON("https://github.com/bharathi3991/AppUpdateExampleByUpdater/master/app/update-changelog.json")
                .setDisplay(Display.DIALOG)
                .showAppUpdated(true)
                .start()
        }
        //versionName.setOnClickListener

        val appUpdaterUtils =
            AppUpdaterUtils(this) //.setUpdateFrom(UpdateFrom.AMAZON)
                //.setUpdateFrom(UpdateFrom.GITHUB)
                //.setGitHubUserAndRepo("javiersantos", "AppUpdater")
                //...
                .withListener(object : UpdateListener {
                    override fun onSuccess(
                        update: Update,
                        isUpdateAvailable: Boolean
                    ) {
                        versionName = "App version : " + update.latestVersion
                        version_name.setText(versionName)
                        Log.d("Latest Version", update.latestVersion)
                       // Log.d("Latest Version Code", update.latestVersionCode.toString())
                        Log.d("Release notes", update.releaseNotes)
                        //Log.d("URL", update.urlToDownload.toString())
                        Log.d(
                            "Is update available?",
                            java.lang.Boolean.toString(isUpdateAvailable)
                        )
                    }

                    override fun onFailed(error: AppUpdaterError) {
                        Log.d("AppUpdater Error", "Something went wrong")
                    }
                })
        appUpdaterUtils.start()

    }

    private fun initializeAppUpdater() {
        AppUpdater(this)
            .setUpdateFrom(UpdateFrom.GITHUB)
            .setGitHubUserAndRepo("bharathi3991", "AppUpdateExampleByUpdater")
            .setDisplay(Display.DIALOG)
            .showAppUpdated(true)
            .start()

       /* startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://github.com/bharathi3991/AppUpdateExampleByUpdater")
            )
        )*/
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