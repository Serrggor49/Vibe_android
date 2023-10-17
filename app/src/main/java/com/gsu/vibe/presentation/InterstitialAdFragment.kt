package com.gsu.vibe.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.anjlab.android.iab.v3.BillingProcessor
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.gsu.vibe.R
import com.gsu.vibe.databinding.FragmentInterstitialAdBinding

class InterstitialAdFragment : Fragment() {

    //val adId = "ca-app-pub-3940256099942544/1033173712"  // test
    val adId = "ca-app-pub-6974166282289246/2531821303"
    private var mInterstitialAd: InterstitialAd? = null
    private val mainViewModel: MainViewModel by activityViewModels()

    lateinit private var _binding: FragmentInterstitialAdBinding
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentInterstitialAdBinding.inflate(inflater, container, false)
        return _binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAd()
        mainViewModel.visibilityBottomBarLivaData.postValue(false)
        //mainViewModel.mInterstitialAd?.show(requireActivity())


//        if (mInterstitialAd != null) {
//            mInterstitialAd?.show(requireActivity())
//        } else {
//            Log.d("TAG", "The interstitial ad wasn't ready yet.")
//        }
    }

    fun initAd(){

        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(requireActivity(), adId, adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
//                Log.d(TAG, adError?.toString())
                adError.toString().let { Log.d("MyLogsAD", it) }
                view?.findNavController()?.navigate(InterstitialAdFragmentDirections.actionInterstitialAdFragmentToMediaPlayerServiceFragment())

                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d("MyLogsAD", "Ad was loaded.")
                mInterstitialAd = interstitialAd
                mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
                    override fun onAdClicked() {
                        // Called when a click is recorded for an ad.
                        Log.d("MyLogsAD", "AdClicked")
                    }

                    override fun onAdDismissedFullScreenContent() {
                        // Called when ad is dismissed.
                        Log.d("MyLogsAD", "Ad dismissed fullscreen content.")
                        view?.findNavController()?.navigate(InterstitialAdFragmentDirections.actionInterstitialAdFragmentToMediaPlayerServiceFragment())
                        mInterstitialAd = null
                    }

                    override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                        Log.e("MyLogsAD", "Ad failed to show fullscreen content.")
                        view?.findNavController()?.navigate(InterstitialAdFragmentDirections.actionInterstitialAdFragmentToMediaPlayerServiceFragment())
                        mInterstitialAd = null
                    }

                    override fun onAdImpression() {
                        // Called when an impression is recorded for an ad.
                        Log.d("MyLogsAD", "Ad recorded an impression.")
                    }

                    override fun onAdShowedFullScreenContent() {
                        Log.d("MyLogsAD", "Ad showed fullscreen content.")
                    }
                }

                mInterstitialAd?.show(requireActivity())

            }
        })
    }

}