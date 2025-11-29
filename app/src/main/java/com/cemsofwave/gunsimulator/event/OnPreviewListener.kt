package com.cemsofwave.gunsimulator.event

interface OnPreviewListener {
    fun onClick(preview: String)

    companion object {
        const val PREVIEW_ONE = "PREVIEW_ONE"
        const val PREVIEW_TWO = "PREVIEW_TWO"
    }
}