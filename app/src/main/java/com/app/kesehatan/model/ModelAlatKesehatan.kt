package com.app.kesehatan.model

import com.google.gson.annotations.SerializedName

data class ModelAlatKesehatan(

	@field:SerializedName("ModelAlatKesehatan")
	val modelAlatKesehatan: List<ModelAlatKesehatanItem?>? = null
)

data class ModelAlatKesehatanItem(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("deskripsi")
	val deskripsi: String? = null,

	@field:SerializedName("gambar")
	val gambar: String? = null
)
