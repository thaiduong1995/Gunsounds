package com.cemsofwave.gunsimulator.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cem.admodule.ext.gone
import com.cemsofwave.gunsimulator.R
import com.cemsofwave.gunsimulator.databinding.ItemColorBinding
import com.trinhbx.base.extension.setOnSingleClickListener
import com.trinhbx.base.extension.visible

class ColorAdapter : RecyclerView.Adapter<ColorAdapter.ViewHolder>() {

    private val listColor by lazy { mutableListOf<Int>() }
    private var oldPositionClick = -1
    var onItemClickListener: ((Int, Int) -> Unit)? = null

    fun setData(listColor: List<Int>) {
        this.listColor.clear()
        this.listColor.addAll(listColor)
        notifyDataSetChanged()
    }

    private fun setPositionClick(position: Int) {
        notifyItemChanged(oldPositionClick)
        oldPositionClick = position
        notifyItemChanged(position)
    }

    inner class ViewHolder(private val binding: ItemColorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(color: Int, position: Int) {
            binding.apply {
                imgBackground.setBackgroundColor(color)
                imgBolder.setBackgroundColor(color)
                if (oldPositionClick == position) {
                    imgBolder.visible()
                } else {
                    imgBolder.gone()
                }
                root.setOnSingleClickListener {
                    setPositionClick(position)
                    onItemClickListener?.invoke(color, position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemColorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listColor.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listColor.getOrNull(position)?.let { item ->
            holder.onBind(item, position)
        }
    }
}