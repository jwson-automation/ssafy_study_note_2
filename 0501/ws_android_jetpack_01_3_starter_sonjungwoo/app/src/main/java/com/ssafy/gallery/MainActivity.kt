package com.ssafy.gallery

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ssafy.gallery.databinding.ActivityMainBinding

private const val TAG = "MainActivity_싸피"

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var galleryDao = GalleryDao()
    private val viewModel:PhotoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        galleryDao.dbOpenHelper(this)
        galleryDao.open()


        Log.d(TAG, "onCreate: ${galleryDao.selectAllPhotos()}")
//        DB.photos = galleryDao.selectAllPhotos()
        viewModel.initPhotos(galleryDao.selectAllPhotos())



        //초기 실행화면 설정
//        val currentFragment = supportFragmentManager.findFragmentById(R.id.galleryFragment)

        supportFragmentManager.beginTransaction()
            .replace(R.id.galleryFragment, GalleryFragment()).commit()
    }


}
