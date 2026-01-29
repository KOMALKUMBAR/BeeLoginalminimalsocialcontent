package com.chat.beelogincompanyassigment.Adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chat.beelogincompanyassigment.databinding.ItemProfilePostBinding

class ProfilePostAdapter(private val images: List<Int>) :
    RecyclerView.Adapter<ProfilePostAdapter.PostVH>() {

    inner class PostVH(val bind: ItemProfilePostBinding) :
        RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostVH {
        return PostVH(
            ItemProfilePostBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PostVH, position: Int) {
        holder.bind.postImage.setImageResource(images[position])
    }

    override fun getItemCount() = images.size
}