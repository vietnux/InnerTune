package com.zionhuang.music.ads

import android.os.Build

object EmulatorUtils {

    fun isGoogleEmulator(): Boolean {
//        return true;
        val fingerprint = Build.FINGERPRINT ?: ""
        val model = Build.MODEL ?: ""
        val manufacturer = Build.MANUFACTURER ?: ""
        val brand = Build.BRAND ?: ""
        val device = Build.DEVICE ?: ""
        val product = Build.PRODUCT ?: ""

        return fingerprint.startsWith("generic") ||
                fingerprint.contains("vbox") ||
                fingerprint.contains("test-keys") ||
                model.contains("google_sdk", ignoreCase = true) ||
                model.contains("Emulator", ignoreCase = true) ||
                model.contains("Android SDK built for x86", ignoreCase = true) ||
                model.contains("Android SDK built for x86_64", ignoreCase = true) ||
                model.contains("Android SDK built for arm64", ignoreCase = true) ||
                manufacturer.contains("Genymotion", ignoreCase = true) ||
                manufacturer.equals("unknown", ignoreCase = true) ||
                brand.startsWith("generic", ignoreCase = true) ||
                device.startsWith("generic", ignoreCase = true) ||
                product.contains("sdk", ignoreCase = true) ||
                product.contains("emulator", ignoreCase = true) ||
                product.contains("simulator", ignoreCase = true)

        //check tiếp nếu cần loại bỏ cả google pixel
//        val isRealGoogleDevice = model.lowercase().startsWith("pixel") &&
//                brand.equals("google", ignoreCase = true) &&
//                manufacturer.equals("google", ignoreCase = true)
//
//        return suspect && !isRealGoogleDevice
    }
}