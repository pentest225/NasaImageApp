package ci.orange.nasaimageapp.domain.model

import okhttp3.MediaType

data class ImageOfToday(
    val date: String,
    val explanation: String,
    val hurl: String,
    val mediaType: String,
    val title : String,
    val url : String
)
