package com.app.kesehatan.adapter

import android.view.View
import com.app.kesehatan.model.ModelAlatKesehatanItem

interface RecyclerViewClickListener {
    fun onItemClicked(view: View, dzikir: ModelAlatKesehatanItem, position: Int)
}