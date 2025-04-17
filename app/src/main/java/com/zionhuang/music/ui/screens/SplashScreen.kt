package com.zionhuang.music.ui.screens

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.playtube.musictube.tune.BuildConfig
import com.playtube.musictube.tune.R
import com.zionhuang.music.ads.JsonParams
import com.zionhuang.music.ads.RemoteJsonCallback
import com.zionhuang.music.ads.fetchAndHandleRemoteJson
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigateNext: () -> Unit) {
    val TAG = "SplashScreen"
    val context = LocalContext.current
    val activity = context as? Activity
    LaunchedEffect(Unit) {
//        delay(2000) // chờ 2s trước khi chuyển tiếp
        if (activity == null) {
            onNavigateNext()
            return@LaunchedEffect
        }

        //ads
        if (JsonParams.DATA == null) {
            try {
                fetchAndHandleRemoteJson(activity, object : RemoteJsonCallback {
                    override fun onSuccess() {
                        onNavigateNext()
                    }

                    override fun onError(e: Exception) {
                        Log.e(TAG, "Error loading ads: ${e.message}")
                        onNavigateNext()
                    }
                })
            } catch (e: Exception) {
                if (BuildConfig.DEBUG) Log.e(TAG, "Error ads..." + e.toString())
                onNavigateNext()
            }
        } else {
            onNavigateNext()
        }

    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red, RectangleShape),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher),
            contentDescription = "Splash Logo",
            modifier = Modifier.size(256.dp)
        )
    }


}