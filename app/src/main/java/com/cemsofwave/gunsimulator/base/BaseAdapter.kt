package com.trinhbx.base.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Created by Trinh BX on 02/02/2023.
 * Mail: trinhbx196@gmail.com.
 * Phone: +084 988 324 622.
 */
abstract class BaseAdapter<VB : ViewBinding, T>(var data: ArrayList<T> = arrayListOf()) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    abstract fun binding(inflater: LayoutInflater, parent: ViewGroup, attachToParent: Boolean, viewType: Int): VB

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = binding(LayoutInflater.from(parent.context), parent, false, viewType)

        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BaseAdapter<*, *>.BaseViewHolder).onBind()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    open inner class BaseViewHolder(var binding: VB) : RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            onBindData(binding, layoutPosition)
        }
    }

    abstract fun onBindData(binding: VB, position: Int)

    fun isEndPosition(position: Int): Boolean {
        return position >= itemCount - 1
    }
}