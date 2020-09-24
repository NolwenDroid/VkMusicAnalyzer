package com.nolwendroid.musiccompare.model.searchResult

import com.google.gson.annotations.SerializedName

data class Followers (

	@SerializedName("href") val href : String,
	@SerializedName("total") val total : Int
)