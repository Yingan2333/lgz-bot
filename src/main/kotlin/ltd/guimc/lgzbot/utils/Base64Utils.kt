/*
 * THIS FILE IS PART OF lgz-bot PROJECT
 *
 * You must disclose the source code of your modified work and the source code you took from this project. This means you are not allowed to use code from this project (even partially) in a closed-source (or even obfuscated) application.
 * Your modified application must also be licensed under the AGPLv3.
 *
 * Copyright (c) 2022 - now Guimc Team.
 */

package ltd.guimc.lgzbot.utils

import java.util.*

object Base64Utils {
    fun encode(data: ByteArray): String {
        return Base64.getEncoder().encodeToString(data)
    }

    fun decode(data: String): ByteArray {
        return Base64.getDecoder().decode(data)
    }
}