package org.sopt.and.presentation.utils

object AuthKey {
    const val EMAIL = "email"
    const val PASSWORD = "password"
    const val PASSWORD_MIN_LENGTH = 8
    const val PASSWORD_MAX_LENGTH = 20
    const val PASSWORD_PATTERN =
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,20}$"
    const val DEFAULT_NAME = "프로필"
}