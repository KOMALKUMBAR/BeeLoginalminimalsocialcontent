package com.chat.beelogincompanyassigment.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chat.beelogincompanyassigment.databinding.ItemExploreGridBinding

class ExploreAdapter(
    private var items: List<Int>,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<ExploreAdapter.VH>() {

    inner class VH(val binding: ItemExploreGridBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemExploreGridBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val imgRes = items[position]

        holder.binding.gridImage.setImageResource(imgRes)
        holder.binding.root.setOnClickListener {
            onClick(imgRes)
        }
    }

    override fun getItemCount() = items.size

    fun updateData(newList: List<Int>) {
        items = newList
        notifyDataSetChanged()
    }
}