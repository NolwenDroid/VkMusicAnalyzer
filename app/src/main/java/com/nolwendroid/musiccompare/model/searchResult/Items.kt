package com.nolwendroid.musiccompare.model.searchResult

import com.google.gson.annotations.SerializedName

data class Items (

	@SerializedName("external_urls") val external_urls : External_urls,
	@SerializedName("followers") val followers : Followers,
	@SerializedName("genres") val genres : List<String>,
	@SerializedName("href") val href : String,
	@SerializedName("id") val id : String,
	@SerializedName("images") val images : List<Images>,
	@SerializedName("name") val name : String,
	@SerializedName("popularity") val popularity : Int,
	@SerializedName("type") val type : String,
	@SerializedName("uri") val uri : String
)