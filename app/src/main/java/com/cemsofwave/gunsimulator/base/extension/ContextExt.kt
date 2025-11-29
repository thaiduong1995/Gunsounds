package com.trinhbx.base.extension

import android.app.Activity
import android.app.ActivityManager
import android.app.WallpaperManager
import android.content.ActivityNotFoundException
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.cemsofwave.gunsimulator.BuildConfig
import com.cemsofwave.gunsimulator.R
import com.cemsofwave.gunsimulator.base.model.Language
import com.trinhbx.base.utils.VersionUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.Locale


/**
 * Created by Trinh BX on 01/02/2023.
 * Mail: trinhbx196@gmail.com.
 * Phone: +084 988 324 622.
 */
fun Context.getAssetJsonData(fileName:String): String? {
    val json: String?
    try {
        val inputStream = assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.use { it.read(buffer) }
        json = String(buffer)
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    // print the data
//    Log.i("data", json)
    return json
}

fun Context.toastMessageShortTime(message:String?){
    Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
}
fun Context.toastMessageLongTime(message:String?){
    Toast.makeText(this,message, Toast.LENGTH_LONG).show()
}

fun Context.sendLocalBroadcast(intent: Intent) {
    val localBroadcastManager = LocalBroadcastManager.getInstance(this)
    localBroadcastManager.sendBroadcast(intent)
}

fun Context.unregisterLocalBroadcast(broadcastReceiver: BroadcastReceiver){
    LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver)
}

fun Context.isServiceRunning(serviceClass: Class<*>): Boolean {
    val manager = getSystemService(AppCompatActivity.ACTIVITY_SERVICE) as ActivityManager
    for (service in manager.getRunningServices(Int.MAX_VALUE)) {
        if (serviceClass.name == service.service.className) {
            return true
        }
    }
    return false
}

fun Context.configLanguage(language: Language) {
    val config = resources.configuration
    val lang =language.code
    if (lang.isNotEmpty()) {
        val locale = Locale(lang)

        Locale.setDefault(locale)
        config.setLayoutDirection(locale)
        if (VersionUtils.hasNougat()) {
            config.setLocale(locale)
        } else {
            config.locale = locale
        }
        createConfigurationContext(config)
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}
fun Context.shareMyApp(appId:String,appName:String) {
    val appPackageName = BuildConfig.APPLICATION_ID
    val appName = this.getString(R.string.app_name)
    val appDesc = this.getString(R.string.app_name)
    val shareBodyText = "$appDesc \n https://play.google.com/store/apps/details?id=$appPackageName"
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TITLE, appName)
        putExtra(Intent.EXTRA_TEXT, shareBodyText)
    }
    this.startActivity(Intent.createChooser(sendIntent, null))
}

fun Context.shareText(content:String?){
    if(content==null) return
    try {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
//        shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
        val shareMessage =
            """$content""".trimIndent()
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
        startActivity(Intent.createChooser(shareIntent, "Choose one"))
    } catch (e: Exception) {
        //e.toString();
        toastMessageShortTime("This text cannot be shared. Try later!")
        e.printStackTrace()
    }
}


fun Context.rateMyApp(appId:String) {
    val uri: Uri = Uri.parse("market://details?id=$appId")
    val launchIntent = Intent(Intent.ACTION_VIEW, uri)
    launchIntent.addFlags(
        Intent.FLAG_ACTIVITY_NO_HISTORY or
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK
    )
    try {
        startActivity(launchIntent)
    } catch (ex: ActivityNotFoundException) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://play.google.com/store/apps/details?id=$appId")
            )
        )
    }
}
fun Activity.updateStatusBarColor(@ColorRes color: Int) {
    // Color must be in hexadecimal fromat
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = ContextCompat.getColor(this, color)
}

fun Context.openInstagram() {
    val link = ""
    if(link.isEmpty()) return
    val intentApp = Uri.parse(link)
    val instagram = Intent(Intent.ACTION_VIEW, intentApp)
    instagram.setPackage("com.instagram.android")
    try {
        this.startActivity(instagram)
    } catch (ex: ActivityNotFoundException) {
        this.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
    }
}
fun Context.openYoutube() {
    val link = "https://www.youtube.com/channel/UCkZzP2S-qEyzTjFTQ7cXLrQ"
    val intentApp = Uri.parse(link)
    val instagram = Intent(Intent.ACTION_VIEW, intentApp)
    instagram.setPackage("com.google.android.youtube")
    try {
        this.startActivity(instagram)
    } catch (ex: ActivityNotFoundException) {
        this.startActivity(Intent(Intent.ACTION_VIEW, intentApp))
    }
}
fun Context.openFacebook() {
    val link = "https://www.facebook.com/cemsoftware"
    val intentApp = Uri.parse(link)
    val instagram = Intent(Intent.ACTION_VIEW, intentApp)
    instagram.setPackage("com.facebook.katana")
    try {
        this.startActivity(instagram)
    } catch (ex: ActivityNotFoundException) {
        this.startActivity(Intent(Intent.ACTION_VIEW, intentApp))
    }
}
fun Context.openTiktok() {
    val link = "https://www.tiktok.com/@cemsoftware"
    val intentApp = Uri.parse(link)
    val instagram = Intent(Intent.ACTION_VIEW, intentApp)
    instagram.setPackage("com.instagram.android")
    try {
        this.startActivity(instagram)
    } catch (ex: ActivityNotFoundException) {
        this.startActivity(Intent(Intent.ACTION_VIEW, intentApp))
    }
}

fun Context.support() {
    val appName = getString(R.string.app_name)
    val emailAddress = getString(R.string.email) //cemsoftware.contact@gmail.com
    val mailIntent = Intent(Intent.ACTION_VIEW)
    val data =
        Uri.parse("mailto:?SUBJECT=$appName&body=&to=$emailAddress")
    mailIntent.data = data
    try {
        startActivity(Intent.createChooser(mailIntent, "Send mail"))
    }catch (ex: ActivityNotFoundException){
        toastMessageShortTime("There is no email client installed.")
    }
}

fun Activity.showKeyBoard() {
    val imm: InputMethodManager =
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

fun Activity.hideKeyBoard(editText: EditText) {
    if(!editText.isKeyboardVisible()) return
    val activityView = this.window.decorView
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(activityView.windowToken, 0)
//    imm.toggleSoftInputFromWindow(editText.rootView.windowToken, 0, 0)
}

fun Context.hideKeyBoard(editText: EditText) {
    if(!editText.isKeyboardVisible()) return
    val imm: InputMethodManager =
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInputFromWindow(editText.rootView.windowToken, 0, 0)
}

const val FOLDER_TMP_EDIT =  "tmp edit"
const val FOLDER_IMAGE_DRAFTS =  "Image Drafts"
fun Context.saveBitmapToInternal(bitmap:Bitmap,folderName:String,fileName: String):String?{
    val rootPath = filesDir.path
    val parentFile = File(rootPath,folderName)
    if(!parentFile.exists()){
        parentFile.mkdirs()
    }
    val fileNameWithExt = "$fileName.png"
    val file = File(parentFile, fileNameWithExt)
    Timber.e("saveBitmapToInternal: ${file.path}")
    return saveBitmapToInternal(bitmap,file.path)
}

fun Context.saveBitmapToInternal(bitmap:Bitmap?,path:String):String?{
    if(bitmap==null) return null
    File(path).parentFile?.mkdirs()
    var pathResult:String? = null
    try {
        val fos = FileOutputStream(path)
        if(bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)){
            pathResult = path
        }
        fos.flush()
        fos.close()
    } catch (e: FileNotFoundException) {
        pathResult = null
        e.printStackTrace()
    } catch (e: Exception) {
        pathResult = null
        e.printStackTrace()
    }
    return pathResult
}


fun Context.saveBitmapToTmpEdit(bitmap: Bitmap):String?{
    val rootPath = filesDir.path
    val parentFile = File(rootPath, FOLDER_TMP_EDIT)
    parentFile.deleteRecursively()
    return saveBitmapToInternal(bitmap,FOLDER_TMP_EDIT,System.currentTimeMillis().toString())
}
private const val CROP_FOLDER = "Crop Image"
fun Context.saveBitmapToCropFolder(bitmap: Bitmap):String?{
    val rootPath = filesDir.path
    val parentFile = File(rootPath, CROP_FOLDER)
    parentFile.deleteRecursively()
    return saveBitmapToInternal(bitmap,CROP_FOLDER,System.currentTimeMillis().toString())
}

fun Context.saveBitmapsToTmpEdit(origin: Bitmap?,mask:Bitmap?):Pair<String?,String?>{
    var originPath:String? = null
    var maskPath:String? = null
    val rootPath = filesDir.path
    val parentFile = File(rootPath, FOLDER_TMP_EDIT)
    parentFile.deleteRecursively()
    if(origin!=null){
        originPath = saveBitmapToInternal(origin,FOLDER_TMP_EDIT,"origin_"+System.currentTimeMillis().toString())
    }
    if(mask!=null){
        maskPath = saveBitmapToInternal(mask,FOLDER_TMP_EDIT,"mask_"+System.currentTimeMillis().toString())
    }
    return Pair(originPath,maskPath)
}



