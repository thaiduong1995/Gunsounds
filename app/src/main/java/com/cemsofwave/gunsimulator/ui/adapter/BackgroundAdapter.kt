package com.cemsofwave.gunsimulator.ui.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cemsofwave.gunsimulator.R
import com.cemsofwave.gunsimulator.databinding.ItemBackgroundBinding
import com.cemsofwave.gunsimulator.utils.RotateTransformation
import com.trinhbx.base.extension.setOnSingleClickListener

/**
 * Created by duong_tt on 10/20/2023.
 * Email: tranthaiduong.kailoren@gmail.com
 * Github: https://github.com/thaiduong1995
 */
class BackgroundAdapter : RecyclerView.Adapter<BackgroundAdapter.ViewHolder>() {

    private val listBackground by lazy { mutableListOf<Bitmap>() }
    private var oldPositionClick = -1
    var onItemClickListener: ((Bitmap, Int) -> Unit)? = null

    fun setData(listBackground: List<Bitmap>) {
        this.listBackground.clear()
        this.listBackground.addAll(listBackground)
        notifyDataSetChanged()
    }

    private fun setPositionClick(position: Int) {
        notifyItemChanged(oldPositionClick)
        oldPositionClick = position
        notifyItemChanged(position)
    }

    inner class ViewHolder(private val binding: ItemBackgroundBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Bitmap, position: Int) {
            binding.apply {
                try{
                    imgBackground.apply {
                        Glide.with(binding.root).clearOnStop().asBitmap().load(item)
                            .transform(RotateTransformation(binding.root.context, -90f))
                            .into(this)
                    }
                }catch (e: Exception){
                    e.printStackTrace()
                }
                root.background =
                    ContextCompat.getDrawable(root.context, R.drawable.bg_corner_4_yellow)

                root.setOnSingleClickListener {
//                    setPositionClick(position)
                    onItemClickListener?.invoke(item, position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBackgroundBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listBackground.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listBackground.getOrNull(position)?.let { item ->
            holder.onBind(item, position)
        }
    }
}