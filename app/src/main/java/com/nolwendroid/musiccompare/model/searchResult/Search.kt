package com.nolwendroid.musiccompare.model.searchResult

import com.google.gson.annotations.SerializedName

data class Search (

	@SerializedName("artists") val artists : Artists
)