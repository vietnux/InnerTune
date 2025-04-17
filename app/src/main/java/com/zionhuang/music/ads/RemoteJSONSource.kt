package com.zionhuang.music.ads

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.playtube.musictube.tune.BuildConfig
import com.zionhuang.music.MainActivity
import com.zionhuang.music.ads.JsonParams
import com.zionhuang.music.ads.LoadingDialog
import com.zionhuang.music.ads.AdmobLib
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.StandardCharsets

suspend fun fetchAndHandleRemoteJson(context: Activity, callback: RemoteJsonCallback) {
    val TAG = "RemoteJSON"

    try {
        val jsonString = withContext(Dispatchers.IO) {
            val url = URL("http://thegioilaptrinh.net/policy/configads_tunemusic.json")
            val reader = BufferedReader(InputStreamReader(url.openConnection().getInputStream(), StandardCharsets.ISO_8859_1))
            val content = reader.readText()
            reader.close()
            content
        }

        val jsonObject = JSONObject(jsonString)
        val data = jsonObject.getJSONObject("data")
        JsonParams.setData(data)

        withContext(Dispatchers.Main) {
            if (JsonParams.getParamInt("begin") == 1) {
                AdmobLib.getInstance(context).interstitial(true, LoadingDialog.loadingDialog, callback)
            } else if (JsonParams.getParamInt("begin") == 2) {
                AdmobLib.getInstance(context).fetchAd(LoadingDialog.loadingDialog, callback)
            } else {
                LoadingDialog.dismissLoadingDialog()
                callback.onSuccess()
            }
        }

    } catch (e: Exception) {
        if (BuildConfig.DEBUG) Log.e(TAG, "Error fetching JSON: ${e.message}")
        withContext(Dispatchers.Main) {
            callback.onError(e)
        }
    }
}
