package nimbl3.surveyapp.widgets

import android.content.Context
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.request.RequestOptions
import nimbl3.surveyapp.R


@GlideModule
class AppGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context?, builder: GlideBuilder?) {
        super.applyOptions(context, builder)
        val requestOptions = RequestOptions().centerCrop()
                                             .placeholder(R.drawable.ic_launcher)

        builder?.setDefaultRequestOptions(requestOptions)

    }
}
