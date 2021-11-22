package com.egpayawal.itunesexam.ui.ext

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.egpayawal.itunesexam.R
import timber.log.Timber

/**
 * Created by Eraño Payawal on 11/22/21.
 * hunterxer31@gmail.com
 */
fun ImageView.loadImage(url: String?, size: String? = null) {
    val translateUrl = translateUrl(url, size)
    Timber.tag("DEBUG").e("URL::$translateUrl")
    if (!translateUrl.isNullOrBlank()) {
        Glide.with(this.context)
            .asBitmap()
            .load(translateUrl)
            .thumbnail(1.0f)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_image_holder)
            .error(R.drawable.ic_image_holder)
            .dontAnimate()
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {
                    this
                }

                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    this@loadImage.setImageBitmap(resource)
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    super.onLoadFailed(errorDrawable)
                    this@loadImage.setImageDrawable(errorDrawable)
                }
            })
    }
}

const val FIXED_800 = "800x800"
const val FIXED_400 = "400x400"
const val IMAGE_SIZE_HOLDER = "100x100"

private fun translateUrl(url: String?, size: String?): String? {
    if (!url.isNullOrEmpty() && !size.isNullOrEmpty() && url.contains(IMAGE_SIZE_HOLDER)) {
        return url.replace(IMAGE_SIZE_HOLDER, size)
    }
    return url
}

