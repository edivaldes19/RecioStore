package com.edival.reciostore.domain.model

import com.edival.reciostore.core.Config

enum class RoleID(val id: String) { CLIENT(Config.ROLE_CLIENT), ADMIN(Config.ROLE_ADMIN) }