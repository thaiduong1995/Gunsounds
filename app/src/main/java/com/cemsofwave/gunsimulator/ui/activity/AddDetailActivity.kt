package com.cemsofwave.gunsimulator.ui.activity

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.cemsofwave.gunsimulator.databinding.ActivityAddDetailBinding
import com.cemsofwave.gunsimulator.ui.dialog.SettingDetailFragment
import com.cemsofwave.gunsimulator.utils.ADD_DETAIL_SHOW
import com.cemsofwave.gunsimulator.base.BaseActivity
import com.cemsofwave.gunsimulator.data.model.SimulatorModel
import com.cemsofwave.gunsimulator.interfaces.MyFragmentCallback
import com.cemsofwave.gunsimulator.ui.dialog.ChargeBatteryDialog
import com.cemsofwave.gunsimulator.ui.dialog.ChargeBulletDialog
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class AddDetailActivity : BaseActivity<ActivityAddDetailBinding>(), MyFragmentCallback {

    companion object {
        private const val EXTRA_MODEL = "EXTRA_MODEL"
        private const val VIBRATE_MODEL = "VIBRATE_MODEL"
        private const val FLASH_MODEL = "FLASH_MODEL"
        private const val SOUND_MODEL = "SOUND_MODEL"
        private lateinit var myFragmentCallback: MyFragmentCallback
        @JvmStatic
        fun start(activity: FragmentActivity?,
                  typeAdd: String,
                  isVibrate: Boolean?,
                  isFlash: Boolean?,
                  isSound: Boolean?,
                  fromScreenName:String?){
            if(activity==null || activity.supportFragmentManager.isStateSaved) return
            val intent = Intent(activity, AddDetailActivity::class.java).apply {
                putExtra(EXTRA_MODEL, typeAdd)
                putExtra(VIBRATE_MODEL, isVibrate)
                putExtra(FLASH_MODEL, isFlash)
                putExtra(SOUND_MODEL, isSound)
            }
            activity.startActivity(intent)
        }

        @JvmStatic
        fun start(activity: FragmentActivity?, typeAdd: String, fromScreenName:String?, myFragmentCallback: MyFragmentCallback){
            if(activity==null || activity.supportFragmentManager.isStateSaved) return
            val intent = Intent(activity,AddDetailActivity::class.java).apply {
                putExtra(EXTRA_MODEL, typeAdd)
            }
            this.myFragmentCallback = myFragmentCallback
            activity.startActivity(intent)
        }
    }

    private var typeAdd:String? = "SETTING"
    private var isVibrate:Boolean? = false
    private var isFlash:Boolean? = false
    private var isSound:Boolean? = false

    override fun provideScreenName(): String {
        return ADD_DETAIL_SHOW
    }

    override fun binding(): ActivityAddDetailBinding {
        return ActivityAddDetailBinding.inflate(layoutInflater)
    }

    override fun getData() {
        super.getData()
        typeAdd = intent?.getStringExtra(EXTRA_MODEL)
        isVibrate = intent?.getBooleanExtra(VIBRATE_MODEL, false)
        isFlash = intent?.getBooleanExtra(FLASH_MODEL, false)
        isSound = intent?.getBooleanExtra(SOUND_MODEL, false)
    }

    override fun initView() {
        when (typeAdd) {
            "SETTING" -> {
                SettingDetailFragment.show(this@AddDetailActivity, isVibrate, isFlash, isSound, screenName)
            }

            "RELOAD_BULLET" -> {
                val reloadBulletDialog = ChargeBulletDialog.newInstance("gun")
                reloadBulletDialog.setOnButtonClickListener(this)
                this@AddDetailActivity.addFragment(
                    binding.fragmentHost,
                    reloadBulletDialog
                )
            }
            "RELOAD_GRENADES" -> {
                val reloadBulletDialog = ChargeBulletDialog.newInstance("grenades")
                reloadBulletDialog.setOnButtonClickListener(this)
                this@AddDetailActivity.addFragment(
                    binding.fragmentHost,
                    reloadBulletDialog
                )
            }
            "CHARGE" -> {
                val reloadBulletDialog = ChargeBatteryDialog.newInstance()
                reloadBulletDialog.setOnButtonClickListener(this)
                this@AddDetailActivity.addFragment(
                    binding.fragmentHost,
                    reloadBulletDialog
                )
            }
        }
    }

    override fun onBulletsRequestListener(isReload: Boolean) {
        try{
            myFragmentCallback.onBulletsRequestListener(isReload)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    override fun onGunRequestListener(simulatorModel: SimulatorModel) {

    }
}