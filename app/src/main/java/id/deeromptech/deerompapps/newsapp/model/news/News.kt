package id.deeromptech.deerompapps.newsapp.model.news

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val id: String? = null,
    val title: String? = null,
    val link: String? = null,
    val content: String? = null,
    var imageArticle: String? = null,
    var date: String? = null,
): Parcelable
