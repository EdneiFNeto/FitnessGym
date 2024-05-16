package com.minhasafra360.android.utils

import android.content.Context
import android.telephony.SmsManager

fun String.sendSMS(context: Context, codeSMS: String) {
    context.getSystemService(SmsManager::class.java)
        .sendTextMessage(
            this,
            null,
            "Código de validação $codeSMS Minhasafra",
            null,
            null
        )
}