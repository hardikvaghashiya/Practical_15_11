package com.practicaltest.model

import android.os.Parcelable

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DocumentResponseModel(
        @SerializedName("content")
        val content: List<DocContent> = listOf(),
        @SerializedName("lang")
        val lang: String = "",
        @SerializedName("status")
        val status: Boolean = false
) : Parcelable

@Parcelize
data class DocContent(
        @SerializedName("mediaDate")
        val mediaDate: MediaDate = MediaDate(),
        @SerializedName("mediaId")
        val mediaId: Int = 0,
        @SerializedName("mediaTitleCustom")
        val mediaTitleCustom: String = "",
        @SerializedName("mediaType")
        val mediaType: String = "",
        @SerializedName("mediaUrl")
        val mediaUrl: String = "",
        @SerializedName("mediaUrlBig")
        val mediaUrlBig: String = ""
) : Parcelable

@Parcelize
data class MediaDate(
        @SerializedName("dateString")
        val dateString: String = "",
        @SerializedName("year")
        val year: String = ""
) : Parcelable