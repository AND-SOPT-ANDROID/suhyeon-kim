package org.sopt.and.data.remote.utils

import org.sopt.and.data.remote.model.base.ApiResponse

fun <T> ApiResponse<T>.handleApiResponse(): Result<T> {
    return try {
        Result.success(this.result)
    } catch (e: Exception) {
        Result.failure(e)
    }
}