package com.cemsofwave.gunsimulator.rcv

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cemsofwave.gunsimulator.extension.dpToPx

/**
 * Created by duong_tt on 9/18/2023.
 * Email: tranthaiduong.kailoren@gmail.com
 * Github: https://github.com/thaiduong1995
 */
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
