package com.example.gamesapi.model

import com.google.gson.annotations.SerializedName


class GamesModel {

    @SerializedName("count"    ) var count    : Int?               = null
    @SerializedName("next"     ) var next     : String?            = null
    @SerializedName("previous" ) var previous : String?            = null
    @SerializedName("results"  ) var results  : ArrayList<Results> = arrayListOf()
}

data class Results (

    @SerializedName("id"                 ) var id               : Int?                 = null,
    @SerializedName("slug"               ) var slug             : String?              = null,
    @SerializedName("name"               ) var name             : String?              = null,
    @SerializedName("released"           ) var released         : String?              = null,
    @SerializedName("tba"                ) var tba              : Boolean?             = null,
    @SerializedName("background_image"   ) var backgroundImage  : String?              = null,
    @SerializedName("rating"             ) var rating           : Double?              = null,
    @SerializedName("rating_top"         ) var ratingTop        : Int?                 = null,
    @SerializedName("ratings_count"      ) var ratingsCount     : Int?                 = null,
    @SerializedName("reviews_text_count" ) var reviewsTextCount : String?              = null,
    @SerializedName("added"              ) var added            : Int?                 = null,
    @SerializedName("metacritic"         ) var metacritic       : Int?                 = null,
    @SerializedName("playtime"           ) var playtime         : Int?                 = null,
    @SerializedName("suggestions_count"  ) var suggestionsCount : Int?                 = null,
    @SerializedName("updated"            ) var updated          : String?              = null,

)