package com.example.aplikasidinu.data.mapper

import com.example.aplikasidinu.data.remote.response.LoginResponse
import com.example.aplikasidinu.domain.model.User

    fun LoginResponse.toUserDomain(): LoginResponse.User {
        return LoginResponse.User(
            id = this.user?.id ?: 0,
            name = this.user?.name.orEmpty(),
            username = this.user?.username.orEmpty(),
            image = this.user?.image.orEmpty(),
            token = this.token.orEmpty(),
        )
    }