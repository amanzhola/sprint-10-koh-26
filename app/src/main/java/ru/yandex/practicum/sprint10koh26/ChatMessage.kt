package ru.yandex.practicum.sprint10koh26

import java.util.Date
import java.util.UUID

data class ChatMessage(
    val id: String = UUID.randomUUID().toString(),
    val isMine: Boolean,
    val messageText: String,
    val status: Status,
    val date: Date,
    val isChanged: Boolean,
) {
    enum class Status {
        SENT,
        DELIVERED,
        READ
    }
}