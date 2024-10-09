package models

import kotlinx.serialization.Serializable

@Serializable
data class Button (
    val place: Int?,
    val text: String?,
    val nameOfNextScene: String?,
    val idOfNextScene: Int?

)