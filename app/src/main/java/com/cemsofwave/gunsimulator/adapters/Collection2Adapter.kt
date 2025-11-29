package com.cemsofwave.gunsimulator.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cem.admodule.ext.ConstAd.NATIVE_ADS
import com.cem.admodule.manager.CemAdManager
import com.cemsofwave.gunsimulator.R
import com.cemsofwave.gunsimulator.data.model.ExplosionModel
import com.cemsofwave.gunsimulator.data.model.GunModel
import com.cemsofwave.gunsimulator.data.model.LightSaberModel
import com.cemsofwave.gunsimulator.data.model.ShockTaserModel
import com.cemsofwave.gunsimulator.data.model.SimulatorModel
import com.cemsofwave.gunsimulator.databinding.ItemBombBinding
import com.cemsofwave.gunsimulator.databinding.ItemCollectionBinding
import com.cemsofwave.gunsimulator.databinding.ItemCollectionSmallBinding
import com.cemsofwave.gunsimulator.databinding.ItemNativeBinding
import com.trinhbx.base.extension.setOnSingleClickListener

class Collection2Adapter (
    private val listener: Listener,
                         private var type: Int = 0) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface Listener {
        fun onItemClicked(simulatorModel: SimulatorModel, position: Int)
        fun onDataIsEmpty(isEmpty: Boolean)
    }

    private val listData by lazy { mutableListOf<SimulatorModel?>() }
    private var lastTimeShow = 0L
    private var timeReload = 0L

    fun setData(list: List<SimulatorModel?>) {
        this.listData.clear()
        this.listData.addAll(list)
        for (item in list) {
            if (item != null) { // Handle null values if necessary
                when (item) {
                    is GunModel -> type = 0
                    is ShockTaserModel -> type = 0
                    is LightSaberModel -> type = 0
                    is ExplosionModel -> type = 3
                    else -> println("obj is not an instance of any of the known subclasses")
                }
            }
        }
        notifyDataSetChanged()
    }

    fun updateItem(index: Int, item: SimulatorModel) {
        listData[index] = item
        notifyItemChanged(index)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        (recyclerView.layoutManager as GridLayoutManager).spanSizeLookup = getSpanSizeLookup()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        listData.getOrNull(position)?.let {
            when (holder) {
                is ItemViewHolder -> holder.bind(position)
                is ItemSmallViewHolder -> holder.bind(position)
                is ItemBombViewHolder -> holder.bind(position)
                else -> {}
            }
        }
        if (holder is ViewNative) {
            holder.onBind(lastTimeShow, timeReload)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM -> ItemViewHolder(ItemCollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            VIEW_TYPE_ITEM_SMALL -> ItemSmallViewHolder(ItemCollectionSmallBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            VIEW_TYPE_ITEM_BOMB -> ItemBombViewHolder(ItemBombBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> ViewNative(
                ItemNativeBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        println("Đã vào đây rồi: $type")
        return when(type) {
            0 -> {
                if(position != 3){
                    VIEW_TYPE_ITEM
                }else{
                    VIEW_TYPE_NATIVE
                }
            }
            3 -> {
                VIEW_TYPE_ITEM_BOMB
            }
            else -> VIEW_TYPE_ITEM_SMALL
        }
    }

    inner class ItemViewHolder(var binding: ItemCollectionBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            listData.getOrNull(position)?.let { itemData ->
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
                                listener.onItemClicked(itemData, position)
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
                                listener.onItemClicked(itemData, position)
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
                                listener.onItemClicked(itemData, position)
                            }
                        }
                    }

                    else -> {}
                }
            }
        }
    }

    inner class ItemSmallViewHolder(var binding: ItemCollectionSmallBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            listData.getOrNull(position)?.let { itemData ->
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
                                listener.onItemClicked(itemData, position)
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
                                listener.onItemClicked(itemData, position)
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
                                listener.onItemClicked(itemData, position)
                            }
                        }
                    }

                    else -> {}
                }
            }
        }
    }

    inner class ItemBombViewHolder(var binding: ItemBombBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            listData.getOrNull(position)?.let { itemData ->
                when (itemData) {
                    is ExplosionModel -> {
                        binding.apply {
                            lineBottom.isGone = true
                            tvBulletCount.isGone = true
                            tvName.isGone = false
                            tvName.text = itemData.name
                            imgThumb.apply {
                                isVisible = true
                                Glide.with(context).asBitmap()
                                    .load(Uri.parse("file:///android_asset/${itemData.preview}"))
                                    .into(imgThumb)
                            }
                            root.setOnSingleClickListener {
                                listener.onItemClicked(itemData, position)
                            }
                        }
                    }
                    else -> {}
                }
            }
        }
    }

    inner class ViewNative(private val binding: ItemNativeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(lastTimeShow: Long, timeReload: Long) {
            binding.nativeView.apply {
                CemAdManager.getInstance(context).loadAndShowNativeByPlacement(
                    context,
                    this,
                    NATIVE_ADS,
                    "CollectionAdapter"
                    )
            }
        }
    }

    private fun getSpanSizeLookup(): GridLayoutManager.SpanSizeLookup {
        return object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (type) {
                    0 -> 2
                    3 -> 1
                    else -> 2
                }
            }
        }
    }

    companion object {
        private const val VIEW_TYPE_NATIVE = 0
        private const val VIEW_TYPE_ITEM = 1
        private const val VIEW_TYPE_ITEM_SMALL = 2
        private const val VIEW_TYPE_ITEM_BOMB = 3
    }
}
