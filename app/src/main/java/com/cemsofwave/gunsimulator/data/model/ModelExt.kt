package com.cemsofwave.gunsimulator.data.model

/**
 * @author
 * Created by Trinh BX.
 * Mail: trinhbx196@gmail.com.
 * Phone: +084 988 324 622.
 * @since 18/10/2023
 */

fun GunData.toModel():GunModel{
    oneShotSound = "gun/$folderName/$oneShotSound"
    if(autoShotSound!=null){
        autoShotSound = "gun/$folderName/$autoShotSound"
    }
    val newListSkin = arrayListOf<String>()
    listSkin.forEach {
        val newSkinString = "gun/$folderName/$it"
        newListSkin.add(newSkinString)
    }
    fireFile = "gun/fire/$fireFile"
    bulletShell = "gun/bullet_shell/$bulletShell"
    bulletType = "file:///android_asset/gun/bullet_type/$bulletType"
    val reloadSound = "gun/reload/reload sound.mp3"
    val emptySound = "gun/empty/Empty gun shot.mp3"
    val reloadAnimFile = "gun/reload/Reload Bullet.json"
    val isPistol = (folderName.contains("USP") || folderName.contains("MAGNUM") || folderName.contains("JERICHO"))

    return GunModel(
        name = name,
        placeOfOrigin = placeOfOrigin,
        inService = inService,
        length = length,
        designed =  designed,
        timeOneShot = timeOneShot,
        bulletCount = bulletCount,
        headX = headX,
        headY = headY,
        oneShotSound = oneShotSound,
        autoShotSound = autoShotSound,
        reloadSound = reloadSound,
        emptySound = emptySound,
        fireFile = fireFile,
        bulletShell = bulletShell,
        bulletType = bulletType,
        isPistol = isPistol,
        listSkin = newListSkin,
        timestamp = System.currentTimeMillis(),
        reloadAnimFile = reloadAnimFile,
        speedAutoShot = speedAutoShot,
        speedBrushShot = speedBrushShot
    )
}

fun ShockTaserData.toModel(): ShockTaserModel {
    val sound = "shock_taser/$sound"
    val effect = "shock_taser/$effect"
    return ShockTaserModel(
        name = name,
        folderName = folderName,
        location = location,
        sound = sound,
        effect = effect,
        timestamp = System.currentTimeMillis()
    )
}

fun LightSaberData.toModel(): LightSaberModel {
    val soundStart = "light_saber/$soundStart"
    val sound = "light_saber/$sound"
    val soundEnd = "light_saber/$soundEnd"
    val list = mutableListOf<String>()
    listEffect.forEach {
        val effect = "light_saber/$it"
        list.add(effect)
    }
    return LightSaberModel(
        name = name,
        folderName = folderName,
        location = location,
        soundStart = soundStart,
        sound = sound,
        soundEnd = soundEnd,
        listEffect = list,
        timestamp = System.currentTimeMillis()
    )
}

fun ExplosionData.toModel(): ExplosionModel {
    val sound = "bomb/$sound"
    return ExplosionModel(
        id = id,
        name = name,
        image = image,
        preview = preview,
        bottom = bottom,
        count = count,
        end = end,
        heightImage = height_image,
        sound = sound,
        start = start,
        timer = timer,
        top = top,
        widthImage = width_image,
        typeBomb = typeBomb,
        timestamp = timestamp
    )
}