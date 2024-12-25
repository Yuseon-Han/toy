package com.yuseon.cute.model

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import org.json.JSONArray
import org.json.JSONObject

class FireBaseRepository {
    fun init() {
        val remoteConfig = FirebaseRemoteConfig.getInstance()

        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(60 * 60) // 데이터 새로고침 간격(초) , 1시간
            .build()

        remoteConfig.setConfigSettingsAsync(configSettings)
    }

    fun fetch(callback: (() -> Unit)?) {
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        remoteConfig.fetchAndActivate().addOnCompleteListener {
            callback?.invoke()
        }
    }

    fun getUrlList(page: Int): List<String> {
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        val jsonString = remoteConfig.getString("cuteUrls$page")
        val jsonArray = JSONObject(jsonString).getJSONArray("data")
        val urlList = jsonArrayToListUsingMap(jsonArray)
        return urlList
    }

    fun getPageCount(): Int {
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        return remoteConfig.getLong("urlCount").toInt()
    }
}

fun jsonArrayToListUsingMap(jsonArray: JSONArray): List<String> {
    return (0 until jsonArray.length()).map { index -> jsonArray.getString(index) }
}