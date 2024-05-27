package com.dicoding.picodiploma.loginwithanimation.data.response

import com.google.gson.annotations.SerializedName

data class GetAssetsResponse(

	@field:SerializedName("listStory")
	val listStory: List<ListStoryItem?>? = emptyList(),

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class ListStoryItem(

	@field:SerializedName("purchaseDate")
	val purchaseDate: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("ownerId")
	val ownerId: Int? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("qrCode")
	val qrCode: String? = null,

	@field:SerializedName("trackerId")
	val trackerId: Any? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("imageURL")
	val imageURL: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("isApproved")
	val isApproved: Boolean? = null,

	@field:SerializedName("depreciation")
	val depreciation: Any? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
