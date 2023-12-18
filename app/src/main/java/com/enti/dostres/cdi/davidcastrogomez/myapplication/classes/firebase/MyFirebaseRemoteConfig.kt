package com.enti.dostres.cdi.davidcastrogomez.myapplication.classes.firebase

import com.enti.dostres.cdi.davidcastrogomez.myapplication.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

class MyFirebaseRemoteConfig {
    private val remoteConfig = Firebase.remoteConfig

    enum class RemoteKeys(val key: String){
        CurrentTheme("current_theme")
    }


    init{

        val configSettings = remoteConfigSettings{
            minimumFetchIntervalInSeconds = 33 //Low value for test, set greater value on production
        }

        remoteConfig.setConfigSettingsAsync(configSettings)

        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)

        remoteConfig.fetchAndActivate()

    }

    enum class AppTheme(val key: String, val themeId: Int){
        Base("Base", R.style.Theme_ModuloDosFirebaseCDI),
        Christmas("Christmas", R.style.Theme_ModuloDosFirebaseCDI_Christmas);

        companion object{
            fun fromKey(key:String): AppTheme{
                return values().find {theme -> theme.key == key } ?: Base
            }
        }
    }


    fun getTheme(): AppTheme{
        val key = remoteConfig.getString(RemoteKeys.CurrentTheme.key)
        return AppTheme.fromKey(key)
    }


}