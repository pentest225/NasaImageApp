package ci.orange.nasaimageapp

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import ci.orange.nasaimageapp.domain.model.ImageOfToday
import com.bumptech.glide.Glide


@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
        imageView.contentDescription = imageView.context.getString(R.string.potentially_hazardous_asteroid_image)

    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
        imageView.contentDescription = imageView.context.getString(R.string.not_hazardous_asteroid_image)
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
        imageView.contentDescription = imageView.context.getString(R.string.potentially_hazardous_asteroid_image)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
        imageView.contentDescription = imageView.context.getString(R.string.not_hazardous_asteroid_image)
    }
}

@BindingAdapter("asteroidImage")
fun bindImageUrl(imageView: ImageView, today: ImageOfToday?) {
    if(today != null && today.mediaType != "video"){
        Glide.with(imageView)
            .load(today.url)
            .placeholder(R.drawable.placeholder_picture_of_day)
            .into(imageView)
        imageView.contentDescription = imageView.context.getString(R.string.nasa_picture_of_day_content_description_format,today.title)

    }else if(today != null ){
        //todo :Implement movie reader ...
        imageView.setImageResource(R.drawable.video_player)
        imageView.contentDescription = imageView.context.getString(R.string.nasa_picture_of_day_content_description_format,today.title)

    }else{
        imageView.setImageResource(R.drawable.computer)
        imageView.contentDescription = imageView.context.getString(R.string.this_is_nasa_s_picture_of_day_showing_nothing_yet)
    }
}


@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}
