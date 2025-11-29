package com.cemsofwave.gunsimulator.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cemsofwave.gunsimulator.base.model.Language
import com.cemsofwave.gunsimulator.databinding.ItemLanguageBinding
import com.cemsofwave.gunsimulator.utils.RotateTransformation

class LanguageAdapter() : RecyclerView.Adapter<LanguageAdapter.ViewHolder>() {

    private var selectedPosition = -1
    val list: MutableList<Language> = mutableListOf()
    var onClickItemListener: ((Language, Int) -> Unit)? = null

    fun submitData(newData: List<Language>) {
        list.clear()
        list.addAll(newData)
        notifyDataSetChanged()
    }

    fun setSelectedPosition(position: Int) {
        selectedPosition = position
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageAdapter.ViewHolder {
        return ViewHolder(
            ItemLanguageBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: LanguageAdapter.ViewHolder, position: Int) {
        list.getOrNull(position)?.let {
            holder.onBind(it, position)
        }
    }

    inner class ViewHolder(itemView: ItemLanguageBinding) :
        RecyclerView.ViewHolder(itemView.root) {

        private val binding = itemView

        fun onBind(item: Language, position: Int) {
            binding.apply {
                tvTitleLanguage.text = item.name
                Glide.with(binding.root).clearOnStop().asBitmap().load(item.image)
                    .transform(RotateTransformation(binding.root.context, 0f))
                    .into(binding.imgLanguage)
                checkboxLanguage.isChecked = (position == selectedPosition)
                root.setOnClickListener {
                    selectedPosition = position
                    onClickItemListener?.invoke(item, position)
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}