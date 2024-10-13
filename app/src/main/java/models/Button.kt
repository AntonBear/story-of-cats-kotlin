package models

import kotlinx.serialization.Serializable

@Serializable
data class Button (
    val text: String?,
    val idOfNextScene: Int?

)