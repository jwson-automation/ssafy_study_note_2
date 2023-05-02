package com.ssafy.gallery

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.ssafy.gallery.databinding.FragmentGalleryBinding

private const val TAG = "GalleryFragment_싸피"
class GalleryFragment() : Fragment() {
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    private val viewModel:PhotoViewModel by activityViewModels()
    lateinit var adapter : GalleryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated: ${viewModel.photos}")
        adapter = GalleryAdapter(requireActivity(),viewModel.photos)

        binding.galleryRCV.adapter = adapter
        binding.galleryRCV.layoutManager = GridLayoutManager(requireContext(), 3)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}