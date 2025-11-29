package com.cemsofwave.gunsimulator.ui.activity

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.cem.admodule.ext.ConstAd.FULL_KEY_PREVIEW
import com.cem.admodule.manager.CemAdManager
import com.cemsofwave.gunsimulator.databinding.ActivityCollectionBinding
import com.cemsofwave.gunsimulator.ui.fragment.CollectionFragment
import com.cemsofwave.gunsimulator.utils.SimulatorType
import com.cemsofwave.gunsimulator.utils.VIEW_COLLECTION_ACTIVITY
import com.cemsofwave.gunsimulator.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollectionActivity : BaseActivity<ActivityCollectionBinding>() {

    companion object {
        private const val EXTRA_MODEL = "EXTRA_MODEL"
        @JvmStatic
        fun start(activity: FragmentActivity?, simulatorType: String, fromScreenName:String?){
            if(activity==null || activity.supportFragmentManager.isStateSaved) return
            CemAdManager.getInstance(activity)
                .showInterstitialReloadCallback(activity = activity, configKey =  FULL_KEY_PREVIEW, callback = {
                    val intent = Intent(activity,CollectionActivity::class.java).apply {
                        putExtra(EXTRA_MODEL, simulatorType)
                    }
                    activity.startActivity(intent)
                })
        }
    }


    private var simulatorType:String? = "GUN"

    override fun provideScreenName(): String {
        return VIEW_COLLECTION_ACTIVITY
    }

    override fun binding(): ActivityCollectionBinding {
        return ActivityCollectionBinding.inflate(layoutInflater)
    }

    override fun getData() {
        simulatorType = intent?.getStringExtra(EXTRA_MODEL)
        initMedia()
    }

    override fun initView() {
        binding.apply {
            when(simulatorType){
                "GUN" -> {
                    this@CollectionActivity.addFragment(
                        fragmentHost,
                        CollectionFragment.newInstance(false,
                            SimulatorType.GUN,
                            screenName)
                    )

                }
                "SHOCK_TASER" -> {
                    this@CollectionActivity.addFragment(
                        fragmentHost,
                        CollectionFragment.newInstance(false,
                            SimulatorType.SHOCK_TASER,
                            screenName)
                    )
                }
                "LIGHT_SABER" -> {
                    this@CollectionActivity.addFragment(
                        fragmentHost,
                        CollectionFragment.newInstance(false,
                            SimulatorType.LIGHT_SABER,
                            screenName)
                    )
                }
                "EXPLOSION" -> {
                    this@CollectionActivity.addFragment(
                        fragmentHost,
                        CollectionFragment.newInstance(false,
                            SimulatorType.EXPLOSION,
                            screenName)
                    )
                }
            }
        }
    }

    override fun initOnClickListener() {}

    override fun onResume() {
        super.onResume()
        playMedia()
    }

    override fun onPause() {
        super.onPause()
        pauseMedia()
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyMedia()
    }
}