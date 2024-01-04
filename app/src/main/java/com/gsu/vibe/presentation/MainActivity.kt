package com.gsu.vibe.presentation

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.gsu.vibe.R
import com.gsu.vibe.databinding.ActivityMainBinding
import com.gsu.vibe.services.MediaPlayerService
import io.branch.referral.Branch
import io.branch.referral.BranchError
import io.branch.referral.util.BRANCH_STANDARD_EVENT
import io.branch.referral.util.BranchEvent
import io.branch.referral.util.CurrencyType
import kotlinx.coroutines.launch
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val mainViewModel: MainViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        initBranch()
        initFavorite()
        initCloseAd()
        initBar()
        initNavBarButtons()

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )
    }

    fun initBranch(){

        //Branch.sessionBuilder(this).withCallback(branchListener).withData(this.intent?.data).init()

        //val branch = Branch.getInstance()

//        branch.initSession({ referringParams, error ->
//            if (error == null) {
//                Log.d("BRANCH SDK", referringParams.toString())
//            } else {
//                Log.d("BRANCH SDK", error.message)
//            }
//        }, this.intent.data, this)

        Branch.sessionBuilder(this).withCallback { branchUniversalObject, linkProperties, error ->
            if (error != null) {
                Log.e("BranchSDK_Tester", "branch init failed. Caused by -" + error.message)
            } else {
                Log.i("BranchSDK_Tester", "branch init complete!")
                if (branchUniversalObject != null) {
                    Log.i("BranchSDK_Tester", "title " + branchUniversalObject.title)
                    Log.i("BranchSDK_Tester", "CanonicalIdentifier " + branchUniversalObject.canonicalIdentifier)
                    Log.i("BranchSDK_Tester", "metadata " + branchUniversalObject.contentMetadata.convertToJson())
                }
                if (linkProperties != null) {
                    Log.i("BranchSDK_Tester", "Channel " + linkProperties.channel)
                    Log.i("BranchSDK_Tester", "control params " + linkProperties.controlParams)
                }
            }
        }.withData(this.intent.data).init()


        // BranchEvent(BRANCH_STANDARD_EVENT.PURCHASE).setAffiliation("test").setCurrency(CurrencyType.USD).logEvent(this)  // для теста


    }

    fun initCloseAd(){
        mainViewModel.openSubscribeFragmentLivaData.observe(this){
            if (it) {
                clearNavButtons()
                mainViewModel.currentType = MainViewModel.CurrentType.FAVORITE
                findNavController(R.id.fragmentContainerView).navigate(R.id.subscriptionsFragment)
            }
        }
    }

    fun initFavorite(){

        mainViewModel.openFavoriteLivaData.observe(this){
            if (it) {
                clearNavButtons()
                mainViewModel.currentType = MainViewModel.CurrentType.FAVORITE
                findNavController(R.id.fragmentContainerView).navigate(R.id.favoriteFragment)
            }
        }
    }

    fun updateBottomButtons(){
        clearNavButtons()

        when (mainViewModel.currentType){
            MainViewModel.CurrentType.FOR_SLEEP ->  binding.buttonBar1Icon.setImageResource(R.drawable.ic_sleep_bar_color)
            MainViewModel.CurrentType.FOR_FOCUS ->  binding.buttonBar2Icon.setImageResource(R.drawable.ic_focus_bar_color)
            MainViewModel.CurrentType.FOR_MEDITATION ->  binding.buttonBar3Icon.setImageResource(R.drawable.ic_meditation_bar_color)
            MainViewModel.CurrentType.NATURE ->  binding.buttonBar4Icon.setImageResource(R.drawable.ic_nature_bar_color)
            MainViewModel.CurrentType.MIXER ->  binding.buttonBar5Icon.setImageResource(R.drawable.ic_mixer_bar_color)
            else -> {}
        }


//        if (mainViewModel.currentType != MainViewModel.CurrentType.FOR_FOCUS) {
//            mainViewModel.currentType = MainViewModel.CurrentType.FOR_FOCUS
//            clearNavButtons()
//            binding.buttonBar2Icon.setImageResource(R.drawable.ic_focus_bar_color)
//            findNavController(R.id.fragmentContainerView).navigate(R.id.focusFragment)
//        }
    }

    fun initBar() {

        val navOptions = NavOptions.Builder()
            .setEnterAnim(R.anim.fade_in)
            .setExitAnim(R.anim.fade_out)
            .setPopEnterAnim(R.anim.fade_in)
            .setPopExitAnim(R.anim.fade_out)
            .build()

        mainViewModel.visibilityBottomBarLivaData.observe(this) {
            if (it) {
                binding.bottomBar.visibility = View.VISIBLE
                binding.imageGradientView.visibility = View.VISIBLE
                window.navigationBarColor = Color.parseColor("#17004A")
                WindowCompat.setDecorFitsSystemWindows(
                    window,
                    true
                )  // показывает верхний и нижний бары
            } else {
                binding.bottomBar.visibility = View.INVISIBLE
                binding.imageGradientView.visibility = View.INVISIBLE
                window.navigationBarColor = Color.parseColor("#0017004A")
                WindowCompat.setDecorFitsSystemWindows(
                    window,
                    false
                )  // убирает верхний и нижний бары
            }
        }

        binding.buttonBar1.setOnClickListener {
            if (mainViewModel.currentType != MainViewModel.CurrentType.FOR_SLEEP) {
                mainViewModel.currentType = MainViewModel.CurrentType.FOR_SLEEP
                clearNavButtons()
                binding.buttonBar1Icon.setImageResource(R.drawable.ic_sleep_bar_color)

                findNavController(R.id.fragmentContainerView).navigate(R.id.sleepFragment, null, navOptions)
            }
        }

        binding.buttonBar2.setOnClickListener {
            if (mainViewModel.currentType != MainViewModel.CurrentType.FOR_FOCUS) {
                mainViewModel.currentType = MainViewModel.CurrentType.FOR_FOCUS
                clearNavButtons()
                binding.buttonBar2Icon.setImageResource(R.drawable.ic_focus_bar_color)



                findNavController(R.id.fragmentContainerView).navigate(R.id.focusFragment, null, navOptions)
            }
        }

        binding.buttonBar3.setOnClickListener {
            if (mainViewModel.currentType != MainViewModel.CurrentType.FOR_MEDITATION) {
                mainViewModel.currentType = MainViewModel.CurrentType.FOR_MEDITATION
                clearNavButtons()
                binding.buttonBar3Icon.setImageResource(R.drawable.ic_meditation_bar_color)
                findNavController(R.id.fragmentContainerView).navigate(R.id.meditationFragment, null, navOptions)
            }
        }

        binding.buttonBar4.setOnClickListener {
            if (mainViewModel.currentType != MainViewModel.CurrentType.NATURE) {
                mainViewModel.currentType = MainViewModel.CurrentType.NATURE
                clearNavButtons()
                binding.buttonBar4Icon.setImageResource(R.drawable.ic_nature_bar_color)
                findNavController(R.id.fragmentContainerView).navigate(R.id.natureFragment, null, navOptions)
            }
        }


//        binding.buttonBar4.setOnClickListener {
//            clearNavButtons()
//            binding.buttonBar4Icon.setImageResource(R.drawable.ic_nature_bar_color)
//            findNavController(R.id.fragmentContainerView).navigate(R.id.natureFragment)
//        }

        binding.buttonBar5.setOnClickListener {
            if (mainViewModel.currentType != MainViewModel.CurrentType.MIXER) {
                mainViewModel.currentType = MainViewModel.CurrentType.MIXER
                clearNavButtons()
                binding.buttonBar5Icon.setImageResource(R.drawable.ic_mixer_bar_color)
                findNavController(R.id.fragmentContainerView).navigate(R.id.mixerFragment, null, navOptions)
            }
        }

        binding.favoriteButton.setOnClickListener {
            if (mainViewModel.currentType != MainViewModel.CurrentType.FAVORITE) {
                mainViewModel.currentType = MainViewModel.CurrentType.FAVORITE
                clearNavButtons()
                findNavController(R.id.fragmentContainerView).navigate(R.id.favoriteFragment, null, navOptions)
            }
        }

    }

    fun clearNavButtons() {
        binding.buttonBar1Icon.setImageResource(R.drawable.ic_sleep_bar)
        binding.buttonBar2Icon.setImageResource(R.drawable.ic_focus_bar)
        binding.buttonBar3Icon.setImageResource(R.drawable.ic_meditation_bar)
        binding.buttonBar4Icon.setImageResource(R.drawable.ic_nature_bar)
        binding.buttonBar5Icon.setImageResource(R.drawable.ic_mixer_bar)
    }

//    object branchListener : Branch.BranchReferralInitListener {
//        override fun onInitFinished(referringParams: JSONObject?, error: BranchError?) {
//            if (error == null) {
//                Log.i("BRANCH SDK", referringParams.toString())
//                // Retrieve deeplink keys from 'referringParams' and evaluate the values to determine where to route the user
//                // Check '+clicked_branch_link' before deciding whether to use your Branch routing logic
//            } else {
//                Log.e("BRANCH SDK", error.message)
//            }
//        }
//
//    }

    fun initNavBarButtons(){

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        setupWithNavController(binding.bottomNavigationView, navController)

        mainViewModel.visibilityBottomBarLivaData.observe(this) {
            if (it) {
                binding.bottomNavigationView.visibility = View.VISIBLE
            } else {
                binding.bottomNavigationView.visibility = View.GONE
            }
        }

    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        this.setIntent(intent);
        Branch.sessionBuilder(this).withCallback { referringParams, error ->
            if (error != null) {
                Log.e("BranchSDK_Tester", error.message)
            } else if (referringParams != null) {
                Log.i("BranchSDK_Tester", referringParams.toString())
            }
        }.reInit()
    }

}