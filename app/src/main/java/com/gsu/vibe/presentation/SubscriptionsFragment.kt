package com.gsu.vibe.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.traceEventEnd
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anjlab.android.iab.v3.BillingProcessor
import com.anjlab.android.iab.v3.BillingProcessor.ISkuDetailsResponseListener
import com.anjlab.android.iab.v3.PurchaseInfo
import com.anjlab.android.iab.v3.SkuDetails
import com.gsu.vibe.R
import com.gsu.vibe.buttonPress
import com.gsu.vibe.databinding.FragmentSubscriptionsBinding
import com.gsu.vibe.presentation.adapters.AdapterForRecycler
import java.text.DecimalFormat
import java.util.Currency
import java.util.Locale


class SubscriptionsFragment : Fragment() {

    private var annualPrice = ""
    private var monthlyPrice = ""
    private var weekPrice = ""

    private lateinit var bp: BillingProcessor
    private val weeklySubId = "vibe_weekly_sub"
    private val monthlySubId = "vibe_monthly_sub"
    private val annualSubId = "vibe_annual_sub"

    private var currentSub = annualSubId

    val viewModel: MainViewModel by activityViewModels()

    lateinit private var _binding: FragmentSubscriptionsBinding
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSubscriptionsBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        viewModel.visibilityBottomBarLivaData.postValue(false)
        initButtons()
        initSubscribes()
        startRecyclerBackground()
    }

    fun initSubscribes() {
        Log.d("MyLogsAD", "initSubscribes")

        bp = BillingProcessor.newBillingProcessor(
            requireContext(),
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAy6D/BZZzGsik8wJNUXmdCnaFJCYKaKuRaDnqxysVKApKrcG0lqkUt/Hjt0uU6l3LRv4efzjSt6+dY3Nl6+INYZF4GzpbC7b5mKvlUUyuuyx6Aqbv1v/vEhOKf1tGbDFcwYd4oKOCujEDZmaoQGrwInXJMbV5bmOI7tWtoKh7hD64ey7Z+NYB4I5WQeY3iqyJhyr7wmp1DbaI+wxSXOWKruNtHgu+m2OdboGjUOKFSXLOsWl0drAMphLOIVVcVF3Ike3i3YwT/VYjKOke+PgOt0ysboSBS1nEvgl1xILO1pmO+2vtyWhGK21UJXRqfV6mwMAcclT1B1USVD190mzhzQIDAQAB",
            object : BillingProcessor.IBillingHandler {

                override fun onBillingError(errorCode: Int, error: Throwable?) {
                    Log.d("MyLogsAD", "onBillingError")
                    Log.d("MyLogsAD", "error = $errorCode")
                }

                override fun onBillingInitialized() {
                    Log.d("MyLogsAD", "onBillingInitialized")
                    loadAvailableSubscriptions()
                }

                override fun onProductPurchased(productId: String, details: PurchaseInfo?) {
                    Log.d("MyLogsAD", "onProductPurchased")
                    viewModel.setSubStatus(true)
                    view?.findNavController()?.popBackStack()
                }

                override fun onPurchaseHistoryRestored() {
                    Log.d("MyLogsAD", "onPurchaseHistoryRestored")
                }
            })

        bp.initialize()



    }

    private fun loadAvailableSubscriptions() {

        val arrayProductsId: ArrayList<String> = arrayListOf(weeklySubId, monthlySubId, annualSubId)
        bp.getSubscriptionsListingDetailsAsync(arrayProductsId, object : ISkuDetailsResponseListener{
            override fun onSkuDetailsResponse(products: MutableList<SkuDetails>?) {
                initPrices(products!!)
            }

            override fun onSkuDetailsError(error: String?) {
                Log.d("MyLogsAD", "error = ${error.toString()}")
            }

        })

        // теперь у вас есть список объектов SkuDetails, содержащий информацию о каждой подписке
    }

    @SuppressLint("SetTextI18n")
    fun initPrices(subscribes : MutableList<SkuDetails>){


        for (subscribe in subscribes){
            Log.d("MyLogsAD", "product = ${subscribe.productId}")
            when(subscribe.productId){
                //"vibe_weekly_sub" -> {binding.weekText.text = getString(R.string.weekly_sub, subscribe.priceText)}
                "vibe_weekly_sub" -> {
                    weekPrice = "${getCurrencySymbol(subscribe.currency)}${formatPrice(subscribe.priceValue)}"
                    binding.weekText.text = getString(R.string.weekly_sub_head)
                    binding.weekPrice.text = "${getCurrencySymbol(subscribe.currency)}${formatPrice(subscribe.priceValue*4)}/${getString(R.string.monthly_sub)}"
                }
                "vibe_monthly_sub" -> {
                    monthlyPrice = "${getCurrencySymbol(subscribe.currency)}${formatPrice(subscribe.priceValue)}"
                    binding.monthlyText.text = getString(R.string.monthly_sub_head)
                    binding.monthPrice.text = "${getCurrencySymbol(subscribe.currency)}${formatPrice(subscribe.priceValue)}/${getString(R.string.monthly_sub)}"
                }
                "vibe_annual_sub" -> {
                    annualPrice = "${getCurrencySymbol(subscribe.currency)}${formatPrice(subscribe.priceValue)}"
                    binding.descriptionSubscribe.text = getString(R.string.sub_description, "$annualPrice/${getString(R.string.annual_sub)}")
                    binding.annualText.text = getString(R.string.yearly_sub_head)
                    binding.yearPrice.text = "${getCurrencySymbol(subscribe.currency)}${formatPrice(subscribe.priceValue/12)}/${getString(R.string.monthly_sub)}"
                }
            }

            binding.subButtons.visibility = View.VISIBLE
            binding.nextButton.visibility = View.VISIBLE
            binding.readyGoToProText.visibility = View.VISIBLE
            binding.textView3.visibility = View.VISIBLE
            binding.descriptionSubscribe.visibility = View.VISIBLE

            binding.progressBar2.visibility = View.GONE
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initButtons() {

//        binding.nextButton.setOnClickListener {
//            bp.subscribe(activity as Activity, currentSub)
//        }

        binding.nextButton.setOnTouchListener { v, event ->
            buttonPress(v, event) {
                bp.subscribe(activity as Activity, currentSub)
            }
        }


        binding.weekButton.setOnTouchListener { v, event ->
            buttonPress(v, event) {
                clearStates()
                binding.weekButton.setBackgroundResource(R.drawable.stroke_for_subscribe_buttons_on)
                binding.weeklyOkIcon.setImageResource(R.drawable.circule_for_subscribe_buttons_on)
                currentSub = weeklySubId
                binding.descriptionSubscribe.text = getString(R.string.sub_description, "$weekPrice/${getString(R.string.weekly_sub)}")
            }
        }

        binding.monthlyButton.setOnTouchListener { v, event ->
            buttonPress(v, event) {
                clearStates()
                binding.monthlyButton.setBackgroundResource(R.drawable.stroke_for_subscribe_buttons_on)
                binding.monthlyOkIcon.setImageResource(R.drawable.circule_for_subscribe_buttons_on)
                currentSub = monthlySubId
                binding.descriptionSubscribe.text = getString(R.string.sub_description, "$monthlyPrice/${getString(R.string.monthly_sub)}")
            }
        }

        binding.annualButton.setOnTouchListener { v, event ->
            buttonPress(v, event) {
                clearStates()
                binding.annualButton.setBackgroundResource(R.drawable.stroke_for_subscribe_buttons_on)
                binding.annualOkIcon.setImageResource(R.drawable.circule_for_subscribe_buttons_on)
                currentSub = annualSubId
                binding.descriptionSubscribe.text = getString(R.string.sub_description, "$annualPrice/${getString(R.string.annual_sub)}")
            }
        }

    }

    private fun clearStates() {
        binding.weekButton.setBackgroundResource(R.drawable.stroke_for_subscribe_buttons_off)
        binding.weeklyOkIcon.setImageResource(R.drawable.circule_for_subscribe_buttons_off)
        binding.monthlyButton.setBackgroundResource(R.drawable.stroke_for_subscribe_buttons_off)
        binding.monthlyOkIcon.setImageResource(R.drawable.circule_for_subscribe_buttons_off)
        binding.annualButton.setBackgroundResource(R.drawable.stroke_for_subscribe_buttons_off)
        binding.annualOkIcon.setImageResource(R.drawable.circule_for_subscribe_buttons_off)
    }


    @SuppressLint("ClickableViewAccessibility")
    fun startRecyclerBackground() {

        //val recycler1 = view?.findViewById<RecyclerView>(R.id.recyclerview1)
        val recycler1 = binding.recyclerview1
        recycler1.layoutManager = LinearLayoutManager(activity?.applicationContext)
        recycler1.adapter = AdapterForRecycler(
            listOf(
                //Pair(R.drawable.sleep_04_1f_milky_way_prev, R.string.alone_in_infinity ),
                Pair(R.drawable.meditation_08_1f_prev, R.string.clear_mind),
                Pair(R.drawable.sleep_04_2b_northern_lights_prev, R.string.northern_lights),
                Pair(R.drawable.nature_08_1f_prev, R.string.lake_shore),
                Pair(R.drawable.focus_03_2f_prev, R.string.way_to_the_goal),
                Pair(R.drawable.focus_03_1b_prev, R.string.dreams_of_the_future),
            )
        )
        recycler1.scrollToPosition(Int.MAX_VALUE / 2)


       // val recycler2 = view?.findViewById<RecyclerView>(R.id.recyclerview2)
        val recycler2 = binding.recyclerview2
        recycler2.layoutManager = LinearLayoutManager(activity?.applicationContext)
        recycler2.adapter = AdapterForRecycler(
            listOf(
                Pair(R.drawable.meditation_02_1f_prev, R.string.path_of_zen),
                Pair(R.drawable.meditation_03_1f_prev, R.string.peace_and_tranquility),
                Pair(R.drawable.nature_01_1f_prev, R.string.internal_balance),
                Pair(R.drawable.sleep_02_1f_summer_evening_prev, R.string.contemplating_eternity),
                Pair(R.drawable.sleep_07_1d_rainy_night_prev, R.string.sounds_of_infinity),
                )
        )
        recycler2.scrollToPosition(Int.MAX_VALUE / 2)


       // val recycler3 = view?.findViewById<RecyclerView>(R.id.recyclerview3)
        val recycler3 = binding.recyclerview3
        recycler3.layoutManager = LinearLayoutManager(activity?.applicationContext)
        recycler3.adapter = AdapterForRecycler(
            listOf(
              //  Pair(R.drawable.focus_07_1f_prev, R.string.flow_of_thought),
                Pair(R.drawable.focus_06_2f_prev, R.string.focus_on_the_task),
                Pair(R.drawable.meditation_03_2f_prev, R.string.sea_and_seagulls),
                Pair(R.drawable.nature_02_2f_prev, R.string.whale_song),
               // Pair(R.drawable.nature_08_2f_prev, R.string.waterfall_sounds),
            )
        )
        recycler3.scrollToPosition(Int.MAX_VALUE / 2)


        //val recycler4 = view?.findViewById<RecyclerView>(R.id.recyclerview4)
        val recycler4 = binding.recyclerview4
        recycler4.layoutManager = LinearLayoutManager(activity?.applicationContext)
        recycler4.adapter = AdapterForRecycler(
            listOf(
                Pair(R.drawable.meditation_02_1f_prev, R.string.sea_and_seagulls),
                //Pair(R.drawable.meditation_03_1f_prev, R.string.sea_and_seagulls),
                Pair(R.drawable.nature_01_1f_prev, R.string.sea_and_seagulls),
                Pair(R.drawable.sleep_02_1f_summer_evening_prev, R.string.sea_and_seagulls),
            )
        )
        recycler4.scrollToPosition(Int.MAX_VALUE / 2)

        val timer = object : CountDownTimer(9999999, 6) {
            override fun onTick(millisUntilFinished: Long) {
                recycler1.scrollBy(0, 1)
                recycler2.scrollBy(0, -1)
                recycler3.scrollBy(0, 2)
                recycler4.scrollBy(0, -1)
            }

            override fun onFinish() {

            }
        }
        timer.start()


        recycler1.setOnTouchListener { _, _ -> true }
        recycler2.setOnTouchListener { _, _ -> true }
        recycler3.setOnTouchListener { _, _ -> true }
        recycler4.setOnTouchListener { _, _ -> true }

        // recycler4?.setOnTouchListener()


    }

    fun getCurrencySymbol(currencyCode: String): String {
        // Словарь с основными кодами валют и их символами
        val currencySymbols = mapOf(
            "USD" to "$",
            "RUB" to "₽",
            "EUR" to "€",
            "GBP" to "£",
            "JPY" to "¥",
            // добавьте другие валюты по необходимости
        )

        // Возвращаем символ из словаря или используем стандартную функцию
        return currencySymbols[currencyCode] ?: try {
            Currency.getInstance(currencyCode).symbol
        } catch (e: IllegalArgumentException) {
            currencyCode
        }
    }


    fun formatPrice(price: Double): String {
        // Пороговое значение, выше которого дробную часть не показываем
        val threshold = 10.0

        return when {
            price >= threshold -> DecimalFormat("#,###").format(price)
            else -> DecimalFormat("#,##0.00").format(price)
        }
    }


}