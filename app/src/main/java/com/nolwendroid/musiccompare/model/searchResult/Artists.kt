package com.nolwendroid.musiccompare.model.searchResult

import com.google.gson.annotations.SerializedName

data class Artists (

	@SerializedName("href") val href : String,
	@SerializedName("items") val items : List<Items>,
	@SerializedName("limit") val limit : Int,
	@SerializedName("next") val next : String,
	@SerializedName("offset") val offset : Int,
	@SerializedName("previous") val previous : String,
	@SerializedName("total") val total : Int
)