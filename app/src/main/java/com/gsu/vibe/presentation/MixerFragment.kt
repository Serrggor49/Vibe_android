package com.gsu.vibe.presentation

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gsu.vibe.R
import com.gsu.vibe.data.Repository
import com.gsu.vibe.data.models.MixerSoundModel
import com.gsu.vibe.databinding.FragmentMixerBinding
import com.gsu.vibe.presentation.adapters.MixerContentAdapter
import kotlin.math.roundToInt

class MixerFragment : Fragment() {

    val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var _binding: FragmentMixerBinding
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMixerBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }


    lateinit var animalList : ArrayList<MixerSoundModel>
    lateinit var natureList : ArrayList<MixerSoundModel>
    lateinit var instrumentsList : ArrayList<MixerSoundModel>


    fun init(){

        animalList = Repository.getSoundsForMixer(Repository.SoundsForMixerType.ANIMALS)
        natureList = Repository.getSoundsForMixer(Repository.SoundsForMixerType.NATURE)
        instrumentsList = Repository.getSoundsForMixer(Repository.SoundsForMixerType.INSTRUMENTS)

        val display: Display = requireActivity().windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)

        val density = resources.displayMetrics.density
        val dpWidth = outMetrics.widthPixels / density
        val columns = (dpWidth / 100).roundToInt()

        binding.recyclerAnimals.layoutManager = GridLayoutManager(requireActivity(), columns)
        binding.recyclerAnimals.adapter = MixerContentAdapter(animalList) { pair ->
            itemClick(pair.first, pair.second, Repository.SoundsForMixerType.ANIMALS, animalList, binding.recyclerAnimals)
        }

        binding.recyclerNature.layoutManager = GridLayoutManager(requireActivity(), columns)
        binding.recyclerNature.adapter = MixerContentAdapter(natureList) { pair ->
            itemClick(pair.first, pair.second, Repository.SoundsForMixerType.NATURE, natureList,  binding.recyclerNature)
        }

        binding.recyclerinstruments.layoutManager = GridLayoutManager(requireActivity(), columns)
        binding.recyclerinstruments.adapter = MixerContentAdapter(instrumentsList) { pair ->
            itemClick(pair.first, pair.second,Repository.SoundsForMixerType.INSTRUMENTS, instrumentsList,  binding.recyclerinstruments)
        }

        binuaInit()

        binding.infoBinuaButton.setOnClickListener {
            val dialogBinding = layoutInflater.inflate(R.layout.alert_dialog_binua_info, null)
            val myDialog = Dialog(requireContext())
            myDialog.setContentView(dialogBinding)

            myDialog.setCancelable(true)
            myDialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
            myDialog.show()

            val okButton = dialogBinding.findViewById<View>(R.id.buttonOKalert)
            okButton.setOnClickListener {
                myDialog.dismiss()
            }
        }

    }


    fun binuaInit(){

        binding.deltaButton.setOnClickListener {
            setShadows()
            binding.deltaShadow.visibility = View.INVISIBLE
            mainViewModel.binuaSound?.sound = R.raw.bird_test
        }

        binding.tettaButton.setOnClickListener {
            setShadows()
            binding.tettaShadow.visibility = View.INVISIBLE
            mainViewModel.binuaSound?.sound = R.raw.bird_test
        }

        binding.alphaButton.setOnClickListener {
            setShadows()
            binding.alphaShadow.visibility = View.INVISIBLE
            mainViewModel.binuaSound?.sound = R.raw.bird_test
        }

        binding.bettaButton.setOnClickListener {
            setShadows()
            binding.bettaShadow.visibility = View.INVISIBLE
            mainViewModel.binuaSound?.sound = R.raw.bird_test
        }

        binding.gammaButton.setOnClickListener {
            setShadows()
            binding.gammaShadow.visibility = View.INVISIBLE
            mainViewModel.binuaSound?.sound = R.raw.bird_test
        }

    }

    fun setShadows(){
        val shadowList = arrayListOf(binding.deltaShadow, binding.tettaShadow, binding.alphaShadow, binding.bettaShadow, binding.gammaShadow)
        shadowList.forEach {it.visibility = View.VISIBLE }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun itemClick(pos: Int, choiceSound: MixerSoundModel, type: Repository.SoundsForMixerType, list: ArrayList<MixerSoundModel>, recyclerView: RecyclerView){

        when (type){
            Repository.SoundsForMixerType.ANIMALS -> mainViewModel.animalSound = choiceSound
            Repository.SoundsForMixerType.NATURE -> mainViewModel.natureSound = choiceSound
            Repository.SoundsForMixerType.INSTRUMENTS -> mainViewModel.instrumentsSound = choiceSound
        }

        list.forEach { it.isSelected = false }
        list[pos].isSelected = true
        recyclerView.adapter?.notifyDataSetChanged()

    }

}