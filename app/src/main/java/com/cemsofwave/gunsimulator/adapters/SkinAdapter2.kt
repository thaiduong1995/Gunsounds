package com.cemsofwave.gunsimulator.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cemsofwave.gunsimulator.R
import com.cemsofwave.gunsimulator.data.model.SkinManager
import com.cemsofwave.gunsimulator.databinding.ItemCollectionSkinBinding
import com.trinhbx.base.extension.gone
import com.trinhbx.base.extension.visible
import com.trinhbx.base.ui.BaseAdapter

class SkinAdapter2(private val onItemClicked: (Int, SkinManager) -> Unit) :
    BaseAdapter<ItemCollectionSkinBinding, SkinManager>() {
    private var itemSelected: SkinManager? = null
    override fun binding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        attachToParent: Boolean,
        viewType: Int
    ): ItemCollectionSkinBinding {
        return ItemCollectionSkinBinding.inflate(inflater, parent, attachToParent)
    }

    override fun onBindData(binding: ItemCollectionSkinBinding, position: Int) {
        data.getOrNull(position)?.let { itemData ->
            binding.apply {
                preview.cancelAnimation()
                preview.setAnimation(itemData.nameSkin)
                if (itemData == itemSelected) {
                    imvSelected.visible()
                    if (itemData.lock) {
                        imvSelected.setImageDrawable(
                            ContextCompat.getDrawable(
                                binding.root.context,
                                R.drawable.ic_video_ads
                            )
                        )
                    } else {
                        imvSelected.setImageDrawable(
                            ContextCompat.getDrawable(
                                binding.root.context,
                                R.drawable.ic_tick
                            )
                        )
                    }
                } else {
                    if (itemData.lock) {
                        imvSelected.setImageDrawable(
                            ContextCompat.getDrawable(
                                binding.root.context,
                                R.drawable.ic_video_ads
                            )
                        )
                    } else {
                        imvSelected.gone()
                    }
                }
                root.setOnClickListener {
                    onItemClicked.invoke(position, itemData)
                    if (itemData != itemSelected) {
                        val posLast = data.indexOfFirst { it == itemSelected }
                        notifyItemChanged(posLast)
                        itemSelected = itemData
                        notifyItemChanged(position)
                    }
                }
            }
        }
    }

    private var recyclerView: RecyclerView? = null
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.itemAnimator = null
        this.recyclerView = recyclerView
    }

    fun updateAll(newList: List<SkinManager>?, position: Int) {
        if (newList == null) return
        itemSelected = newList.getOrNull(position)
        data.clear()
        data.addAll(newList)
        notifyDataSetChanged()
        recyclerView?.setItemViewCacheSize(itemCount)
    }
}