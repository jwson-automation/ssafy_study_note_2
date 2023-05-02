package com.ssafy.gallery

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.ssafy.gallery.databinding.FragmentPhotoBinding

private const val TAG = "PhotoFragment_싸피"

class PhotoFragment(val position: Int) : Fragment() {
    lateinit var binding: FragmentPhotoBinding
    private val viewModel: PhotoViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotoBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var _photo = viewModel.photos[position]
        Log.d(TAG, "onViewCreated: $_photo")
        binding.photo = _photo
    }


}