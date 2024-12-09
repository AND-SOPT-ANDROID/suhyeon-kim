package org.sopt.and.utils

import android.content.Context
import org.sopt.and.R
import retrofit2.HttpException
import java.io.IOException

fun handleErrorToast(
    exception: Throwable?,
    is400Error: Int,
    context: Context,
    is403Error: Int = R.string.fail_to_network,
    is409Error: Int = R.string.fail_to_network,
) {
    return when (exception) {
        is HttpException -> when (exception.code()) {
            400 -> context.toast(context.getString(is400Error))
            403 -> context.toast(context.getString(is403Error))
            409 -> context.toast(context.getString(is409Error))
            else -> context.toast(context.getString(R.string.fail_to_network))
        }

        is IOException -> {
            context.toast(context.getString(R.string.fail_to_network))
        }

        else -> {
            context.toast(context.getString(R.string.fail_to_login))
        }
    }
}