package com.cemsofwave.gunsimulator.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cemsofwave.gunsimulator.R
import com.cemsofwave.gunsimulator.data.model.GunModel
import com.cemsofwave.gunsimulator.data.model.LightSaberModel
import com.cemsofwave.gunsimulator.data.model.ShockTaserModel
import com.cemsofwave.gunsimulator.data.model.SimulatorModel
import com.cemsofwave.gunsimulator.databinding.ItemCollectionBinding
import com.trinhbx.base.extension.setOnSingleClickListener
import com.trinhbx.base.ui.BaseAdapter

class CollectionAdapter(private val listener: Listener) :
    BaseAdapter<ItemCollectionBinding, SimulatorModel>() {
    interface Listener {
        fun onItemClicked(simulatorModel: SimulatorModel)
        fun onDataIsEmpty(isEmpty:Boolean)
    }
    override fun binding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        attachToParent: Boolean,
        viewType: Int
    ): ItemCollectionBinding {
        return ItemCollectionBinding.inflate(inflater, parent, attachToParent)
    }

    override fun onBindData(binding: ItemCollectionBinding, position: Int) {
        data.getOrNull(position)?.let { itemData ->
            when (itemData) {
                is GunModel -> {
                    binding.apply {
                        tvName.text = itemData.name
                        tvBulletCount.apply {
                            text = itemData.bulletCount.toString()
                            setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.bullet_preview,
                                0,
                                0,
                                0
                            )
                        }
                        preview.setAnimation(itemData.listSkin.getOrNull(0))
                        root.setOnSingleClickListener {
                            listener.onItemClicked(itemData)
                        }
                    }
                }

                is ShockTaserModel -> {
                    binding.apply {
                        preview.isGone = true
                        tvName.isGone = true
                        lineBottom.isGone = true
                        tvBulletCount.isGone = true
                        imgThumb.apply {
                            isVisible = true
                            val width = context.resources.displayMetrics.widthPixels/3
                            Glide.with(context).asBitmap()
                                .load(Uri.parse("file:///android_asset/${itemData.location}"))
                                .override(width)
                                .into(imgThumb)
                        }
                        root.setOnSingleClickListener {
                            listener.onItemClicked(itemData)
                        }
                    }
                }

                is LightSaberModel -> {
                    binding.apply {
                        preview.isGone = true
                        tvName.isGone = true
                        lineBottom.isGone = true
                        tvBulletCount.isGone = true
                        imgThumb.apply {
                            isVisible = true
                            val width = context.resources.displayMetrics.widthPixels/3
                            Glide.with(context).asBitmap()
                                .load(Uri.parse("file:///android_asset/${itemData.location}"))
                                .override(width)
                                .into(imgThumb)
                        }
                        root.setOnSingleClickListener {
                            listener.onItemClicked(itemData)
                        }
                    }
                }

                else -> {}
            }
        }
    }

    fun updateAll(newList: List<SimulatorModel>?) {
        if (newList == null) return
        data.clear()
        data.addAll(newList)
        notifyDataSetChanged()
        recyclerView?.setItemViewCacheSize(itemCount)
        listener.onDataIsEmpty(itemCount==0)
    }
    private var recyclerView: RecyclerView? = null
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }
}
