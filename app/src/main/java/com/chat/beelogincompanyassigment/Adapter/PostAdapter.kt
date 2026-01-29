package com.chat.beelogincompanyassigment.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chat.beelogincompanyassigment.data.models.Post
import com.chat.beelogincompanyassigment.databinding.ItemPostBinding

class PostAdapter(
    private var list: List<Post>
) : RecyclerView.Adapter<PostAdapter.PostVH>() {

    inner class PostVH(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostVH {
        val binding = ItemPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PostVH(binding)
    }

    override fun onBindViewHolder(holder: PostVH, position: Int) {
        val p = list[position]

        holder.binding.apply {

            tvUserName.text = p.userName
            tvTimestamp.text = "${p.timestamp} • ${p.location}"
            imgPost.setImageResource(p.imageRes)
            tvTitle.text = p.title
            tvDescription.text = p.description
            tvLikes.text = "❤️ ${p.likes}"
            tvComments.text = "💬 ${p.comments}"

            tvLikes.setOnClickListener {
                if (p.isLiked) {
                    p.likes--
                } else {
                    p.likes++
                }
                p.isLiked = !p.isLiked
                tvLikes.text = "❤️ ${p.likes}"
            }
        }
    }

    override fun getItemCount(): Int = list.size

    fun updateData(newList: List<Post>) {
        list = newList
        notifyDataSetChanged()
    }
}