package models

import kotlinx.serialization.Serializable

@Serializable
data class Page (
    val id: Int,
    val backGroundScene: String,
    val text: String,
    val buttons: ArrayList<Button>
)