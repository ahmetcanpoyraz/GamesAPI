package com.example.gamesapi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


class GamesModel {

    @SerializedName("count"    ) var count    : Int?               = null
    @SerializedName("next"     ) var next     : String?            = null
    @SerializedName("previous" ) var previous : String?            = null
    @SerializedName("results"  ) var results  : ArrayList<Results> = arrayListOf()
}

@Entity
data class Results (
    @ColumnInfo(name = "id")
    @SerializedName("id"                 ) var id               : Int?                 = null,
    @ColumnInfo(name = "slug")
    @SerializedName("slug"               ) var slug             : String?              = null,
    @ColumnInfo(name = "name")
    @SerializedName("name"               ) var name             : String?              = null,
    @ColumnInfo(name = "released")
    @SerializedName("released"           ) var released         : String?              = null,
    @ColumnInfo(name = "tba")
    @SerializedName("tba"                ) var tba              : Boolean?             = null,
    @ColumnInfo(name = "background_image")
    @SerializedName("background_image"   ) var backgroundImage  : String?              = null,
    @ColumnInfo(name = "rating")
    @SerializedName("rating"             ) var rating           : Double?              = null,
    @ColumnInfo(name = "rating_top")
    @SerializedName("rating_top"         ) var ratingTop        : Int?                 = null,
    @ColumnInfo(name = "ratings_count")
    @SerializedName("ratings_count"      ) var ratingsCount     : Int?                 = null,
    @ColumnInfo(name = "reviews_text_count")
    @SerializedName("reviews_text_count" ) var reviewsTextCount : String?              = null,
    @ColumnInfo(name = "added")
    @SerializedName("added"              ) var added            : Int?                 = null,
    @ColumnInfo(name = "metacritic")
    @SerializedName("metacritic"         ) var metacritic       : Int?                 = null,
    @ColumnInfo(name = "playtime")
    @SerializedName("playtime"           ) var playtime         : Int?                 = null,
    @ColumnInfo(name = "suggestions_count")
    @SerializedName("suggestions_count"  ) var suggestionsCount : Int?                 = null,
    @ColumnInfo(name = "updated")
    @SerializedName("updated"            ) var updated          : String?              = null,
){
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}