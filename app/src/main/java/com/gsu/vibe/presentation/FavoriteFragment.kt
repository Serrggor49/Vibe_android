package com.gsu.vibe.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gsu.vibe.R
import com.gsu.vibe.data.Repository
import com.gsu.vibe.databinding.FragmentFavoriteBinding
import com.gsu.vibe.databinding.FragmentPlayerBinding
import com.gsu.vibe.presentation.adapters.FavoritesSoundsAdapter

class FavoriteFragment : Fragment() {

    val mainViewModel : MainViewModel by activityViewModels()

    private lateinit var _binding: FragmentFavoriteBinding
    private val binding
        get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return _binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Log.d("MyLogs311", Repository.getFavoritesSounds(requireContext()).toString())

        init()
    }


    fun init(){

        binding.recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recycler.adapter = FavoritesSoundsAdapter(Repository.getFavoritesSounds(requireContext())) {
                name -> itemClick(name)
        }

    }

    fun itemClick(name: String){
        mainViewModel.setCurrentSound(name)
        val action = FavoriteFragmentDirections.actionFavoriteFragmentToPlayerFragment()
        view?.findNavController()?.navigate(action)
    }

}