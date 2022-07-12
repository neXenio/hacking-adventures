package com.nexenio.user.data

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include

@JsonInclude(Include.NON_ABSENT)
data class UserResponse(
    val username: String,
    val salt: String = "",
    val pwHash: String = "",
    val phone: String = "",
    val recoveryPhrase: String = ""
)
