package ci.orange.nasaimageapp.data.remoute.dto

import ci.orange.nasaimageapp.domain.model.ImageOfToday
/**
 * {
"date": "2023-03-26",
"explanation": "How far out will humanity explore? If this video's fusion of real space imagery and fictional space visualizations is on the right track, then at least the Solar System. Some of the video's wondrous sequences depict future humans drifting through the rings of Saturn, exploring Jupiter from a nearby spacecraft, and jumping off a high cliff in the low gravity of a moon of Uranus. Although no one can know the future, wandering and exploring beyond boundaries -- both physical and intellectual -- is part of the human spirit and has frequently served humanity well in the past.",
"media_type": "video",
"service_version": "v1",
"title": "Wanderers",
"url": "//player.vimeo.com/video/108650530?title=0&byline=0&portrait=0&badge=0&color=ffffff"
}
 *
 * */
data class ImageOfToDayDto(
    val date: String,
    val explanation: String,
    val media_type:String,
    val service_version: String,
    val title : String,
    val url : String,
    val hurl: String?,

)

fun ImageOfToDayDto.toImageOfToDay():ImageOfToday{
    return ImageOfToday(
        date = this.date,
        explanation = this.explanation,
        mediaType = this.media_type,
        title = this.title,
        url = this.url,
        hurl = this.hurl ?: ""
    )
}
