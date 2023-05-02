package com.ssafy.gallery

import android.os.Bundle
import android.provider.ContactsContract.Contacts.Photo
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ssafy.gallery.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

private const val TAG = "MainActivity_싸피"

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: PhotoViewModel by viewModels()
    private lateinit var repo: GalleryRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repo = GalleryRepository.get()

        lifecycleScope.launch {
            viewModel.initPhotos(repo.selectAllPhotos())
            Log.d(TAG, "onCreate: ${repo.selectAllPhotos()}")

            supportFragmentManager.beginTransaction()
                .replace(R.id.galleryFragment, GalleryFragment()).commit()
        }


    }
    fun changeFragment(position: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.galleryFragment, PhotoFragment(position)).addToBackStack("back").commit()
    }


}
