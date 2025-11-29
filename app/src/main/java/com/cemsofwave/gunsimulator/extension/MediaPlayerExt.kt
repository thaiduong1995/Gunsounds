package com.cemsofwave.gunsimulator.extension

import android.media.MediaPlayer

fun MediaPlayer.destroy() {
    if (this.isPlaying) {
        pause()
        stop()
        release()
    }
}