/*
 * THIS FILE IS PART OF lgz-bot PROJECT
 *
 * You must disclose the source code of your modified work and the source code you took from this project. This means you are not allowed to use code from this project (even partially) in a closed-source (or even obfuscated) application.
 * Your modified application must also be licensed under the AGPLv3.
 *
 * Copyright (c) 2022 - now Guimc Team.
 */

package ltd.guimc.lgzbot.utils

object RandomUtils {
    fun randomText(length: Int): String {
        val charset = "QWERTYUIOPASDDFGHJKKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567980"
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }

    fun randomLong(): Long {
        val charset = "1234567980"
        return (1..15)
            .map { charset.random() }
            .joinToString("")
            .toLong()
    }
}