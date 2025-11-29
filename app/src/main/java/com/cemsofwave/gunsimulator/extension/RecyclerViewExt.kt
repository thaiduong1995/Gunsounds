package com.cemsofwave.gunsimulator.extension

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addItemDecoration(marginHorizontal: Int, marginVertical: Int) {
    this.addItemDecoration(object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State,
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            val distanceHorizontal = context?.dpToPx(marginHorizontal)
            val distanceVertical = context?.dpToPx(marginVertical)
            if (parent.getChildAdapterPosition(view) >= 0) {
                distanceHorizontal?.let {
                    outRect.right = it
                    outRect.left = it
                }
                distanceVertical?.let {
                    outRect.top = it
                    outRect.bottom = it
                }
            }
        }
    })
}

fun RecyclerView.addItemDecoration(
    marginStart: Int = 0,
    marginEnd: Int = 0,
    marginTop: Int = 0,
    marginBottom: Int = 0
) {
    this.addItemDecoration(object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State,
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            val distanceStart = context?.dpToPx(marginStart)
            val distanceEnd = context?.dpToPx(marginEnd)
            val distanceTop = context?.dpToPx(marginTop)
            val distanceBottom = context?.dpToPx(marginBottom)
            if (parent.getChildAdapterPosition(view) >= 0) {
                distanceStart?.let {
                    outRect.right = it
                }
                distanceEnd?.let {
                    outRect.left = it
                }
                distanceTop?.let {
                    outRect.top = it
                }
                distanceBottom?.let {
                    outRect.bottom = it
                }
            }
        }
    })
}