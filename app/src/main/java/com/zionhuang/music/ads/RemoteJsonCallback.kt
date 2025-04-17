package com.zionhuang.music.ads

interface RemoteJsonCallback {
    fun onSuccess()
    fun onError(e: Exception)
}