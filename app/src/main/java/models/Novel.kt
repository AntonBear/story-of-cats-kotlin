package models

import kotlinx.serialization.Serializable

@Serializable
data class Novel (
    val pages: ArrayList<Page>
)