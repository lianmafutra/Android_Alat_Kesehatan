package com.app.kesehatan.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.app.kesehatan.AlatKesehatanDetailActivity
import com.app.kesehatan.AlatKesehatanListActivity
import com.app.kesehatan.databinding.ItemAlatKesehatanListBinding
import com.app.kesehatan.model.ModelAlatKesehatanItem
import com.bumptech.glide.Glide

class AdapterListAlatKesehatan(
    private var modelAlatKesehatanItem: List<ModelAlatKesehatanItem>,
) :
    RecyclerView.Adapter<AdapterListAlatKesehatan.ItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemBinding = ItemAlatKesehatanListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item: ModelAlatKesehatanItem = modelAlatKesehatanItem[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = modelAlatKesehatanItem.size

    // method for filtering our recyclerview items.
    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filterlist: ArrayList<ModelAlatKesehatanItem>) {
        // below line is to add our filtered
        // list in our course array list.
        modelAlatKesehatanItem = filterlist
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged()
    }

    class ItemHolder(private val binding: ItemAlatKesehatanListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: ModelAlatKesehatanItem) {
            binding.tvNamaAlat.text = item.nama

            Glide.with(itemView)
                .load(Uri.parse("file:///android_asset/gambar/"+item.gambar))
                .into(binding.imgAlat)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, AlatKesehatanDetailActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                val bundle = Bundle()
                bundle.putString("img_detail", item.gambar)
                bundle.putString("nama_alat", item.nama)
                bundle.putString("deskripsi",item.deskripsi)
                intent.putExtras(bundle)
                itemView.context.startActivity(intent)

            }
        }
    }
}