package com.gsu.vibe.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gsu.vibe.R
import com.gsu.vibe.databinding.FragmentPaywallBinding

import com.gsu.vibe.presentation.adapters.AdapterForRecycler


class PaywallFragment : Fragment() {

    private lateinit var _binding: FragmentPaywallBinding
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPaywallBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonsConfig()
        startRecyclerBackground()


        initTexts()
        //   view?.findViewById<TextView>(R.id.descriptionSubscribe)?.text = viewModelFragments.descriptionSubscribeYearly

    }

    fun initTexts(){

       // binding.textView21.text = getString(R.string.sea_and_seagulls)
    }


    fun buttonsConfig() {

        //val nextButton = view?.findViewById<ImageButton>(R.id.nextButton)
//        nextButton?.setOnClickListener {
//            viewModelFragments.bp?.subscribe(activity, viewModelFragments.selectedSubscribe)
//            activity?.let { it1 ->
//                viewModelFragments.liveDataStatus.observe(it1) {
//                    if (it) {
//
//                        val bandle = Bundle()
//                        bandle.putString("period", "monthly")
//                        viewModelFragments.sendAnalitic("sub_success", bandle)
//
//                        val intent = Intent(activity, KEditorDemoActivity1::class.java)
//                        requireActivity().finish()
//                        startActivity(intent)
//
//                    }
//                }
//            }
//        }





//        val monthDescriptionSubscribe =
//            view?.findViewById<AppCompatTextView>(R.id.monthDescriptionSubscribe)
//        activity?.let { it ->
//            viewModelFragments.descriptionSubscribeMounth.observe(it) {
//                monthDescriptionSubscribe?.text = it
//            }
//        }

//
//        val weekDescriptionSubscribe =
//            view?.findViewById<AppCompatTextView>(R.id.weekDescriptionSubscribe)
//        activity?.let { it ->
//            viewModelFragments.descriptionSubscribeWeek.observe(it) {
//                weekDescriptionSubscribe?.text = it
//            }
//        }


//        val descriptionSubscribeNew =
//            view?.findViewById<AppCompatTextView>(R.id.descriptionSubscribeNew)
//        activity?.let { it ->
//            viewModelFragments.descriptionSubscribeNew.observe(it) {
//                descriptionSubscribeNew?.text = it
//            }
//        }

//        monthButton?.setOnClickListener {
//            viewModelFragments.selectedSubscribe = viewModelFragments.mounthlySubscribeId
//            // view?.findViewById<TextView>(R.id.descriptionSubscribe)?.text = viewModelFragments.descriptionSubscribeYearly
//            monthButton.setBackgroundResource(R.drawable.sub_select)
//            weekButton?.setBackgroundResource(R.drawable.sub_unselect)
//        }


//        weekButton?.setOnClickListener {
//
//            viewModelFragments.selectedSubscribe = viewModelFragments.weekSubscribeId
//            // view?.findViewById<TextView>(R.id.descriptionSubscribe)?.text = viewModelFragments.descriptionSubscribeMounth
//            monthButton?.setBackgroundResource(R.drawable.sub_unselect)
//            weekButton.setBackgroundResource(R.drawable.sub_select)
//        }


    }


    @SuppressLint("ClickableViewAccessibility")
    fun startRecyclerBackground() {

        val recycler1 = view?.findViewById<RecyclerView>(R.id.recyclerview1)
        recycler1?.layoutManager = LinearLayoutManager(activity?.applicationContext)
        recycler1?.adapter = AdapterForRecycler(
            listOf(
                Pair(R.drawable.sleep_04_1f_milky_way_prev, R.string.sea_and_seagulls),
                Pair(R.drawable.meditation_08_1f_prev, R.string.clear_mind),
                Pair(R.drawable.sleep_04_2b_northern_lights_prev, R.string.northern_lights),
                Pair(R.drawable.sleep_07_1d_rainy_night_prev, R.string.sounds_of_infinity),
                Pair(R.drawable.focus_03_2f_prev, R.string.way_to_the_goal),
                Pair(R.drawable.focus_03_1b_prev, R.string.dreams_of_the_future),
            )
        )
        recycler1?.scrollToPosition(Int.MAX_VALUE / 2)


        val recycler2 = view?.findViewById<RecyclerView>(R.id.recyclerview2)
        recycler2?.layoutManager = LinearLayoutManager(activity?.applicationContext)
        recycler2?.adapter = AdapterForRecycler(
            listOf(
                Pair(R.drawable.meditation_02_1f_prev, R.string.path_of_zen),
                Pair(R.drawable.meditation_03_1f_prev, R.string.peace_and_tranquility),
                Pair(R.drawable.nature_01_1f_prev, R.string.internal_balance),
                Pair(R.drawable.sleep_02_1f_summer_evening_prev, R.string.contemplating_eternity),
            )
        )
        recycler2?.scrollToPosition(Int.MAX_VALUE / 2)


        val recycler3 = view?.findViewById<RecyclerView>(R.id.recyclerview3)

        recycler3?.layoutManager = LinearLayoutManager(activity?.applicationContext)
        recycler3?.adapter = AdapterForRecycler(
            listOf(
                Pair(R.drawable.focus_07_1f_prev, R.string.flow_of_thought),
                Pair(R.drawable.focus_06_2f_prev, R.string.focus_on_the_task),
                Pair(R.drawable.meditation_03_2f_prev, R.string.sea_and_seagulls),
                Pair(R.drawable.nature_02_2f_prev, R.string.whale_song),
                Pair(R.drawable.nature_08_1f_prev, R.string.lake_shore),
                Pair(R.drawable.nature_08_2f_prev, R.string.waterfall_sounds),
            )
        )
        recycler3?.scrollToPosition(Int.MAX_VALUE / 2)


        val recycler4 = view?.findViewById<RecyclerView>(R.id.recyclerview4)
        recycler4?.layoutManager = LinearLayoutManager(activity?.applicationContext)
        recycler4?.adapter = AdapterForRecycler(
            listOf(
                Pair(R.drawable.meditation_02_1f_prev, R.string.sea_and_seagulls),
                //Pair(R.drawable.meditation_03_1f_prev, R.string.sea_and_seagulls),
                Pair(R.drawable.nature_01_1f_prev, R.string.sea_and_seagulls),
                Pair(R.drawable.sleep_02_1f_summer_evening_prev, R.string.sea_and_seagulls),
            )
        )
        recycler4?.scrollToPosition(Int.MAX_VALUE / 2)

        val timer = object : CountDownTimer(9999999, 5) {
            override fun onTick(millisUntilFinished: Long) {
                recycler1?.scrollBy(0, 1)
                recycler2?.scrollBy(0, -1)
                recycler3?.scrollBy(0, 2)
                recycler4?.scrollBy(0, -1)
            }

            override fun onFinish() {

            }
        }
        timer.start()


        recycler1?.setOnTouchListener { _, _ -> true }
        recycler2?.setOnTouchListener { _, _ -> true }
        recycler3?.setOnTouchListener { _, _ -> true }
        recycler4?.setOnTouchListener { _, _ -> true }

       // recycler4?.setOnTouchListener()


    }



    //
//    fun handlePurchase(purchase : Purchase) {
//        // Creating the Revenue.Receipt instance.
//        // It is used for checking purchases in Google Play.
//       val revenueReceipt : Revenue.Receipt  = Revenue.Receipt.newBuilder()
//            .withData(purchase.getOriginalJson())
//            .withSignature(purchase.getSignature())
//            .build();
//        // Creating the Revenue instance.
//         val revenue : Revenue = Revenue.newBuilderWithMicros(99000000, Currency.getInstance("RUB"))
//        .withProductID("com.yandex.service.299")
//            .withQuantity(2)
//            .withReceipt(revenueReceipt)
//            .withPayload("{\"source\":\"Google Play\"}")
//            .build();
//        // Sending the Revenue instance using reporter.
//        context?.let { YandexMetrica.getReporter(it, "Testing API key").reportRevenue(revenue) }
//    }


//    fun sendSubscribeToAppMetrica(){
//
//        // Creating the Revenue instance.
//        val revenue: Revenue = Revenue.newBuilderWithMicros(99000000, Currency.getInstance("RUB1"))
//            .withProductID("com.yandex.service.299")
//            .withQuantity(2)
//            // Passing the OrderID parameter in the .withPayload(String payload) method to group purchases.
//            .withPayload("km")
//            .build()
//
//        YandexMetrica.reportRevenue(revenue)

// Sending the Revenue instance using reporter.
    // context?.let { YandexMetrica.getReporter(it, "Testing API key").reportRevenue(revenue) };





}





