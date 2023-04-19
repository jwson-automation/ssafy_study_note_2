package com.ssafy.cleanstore


import com.bumptech.glide.Glide
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.ssafy.cleanstore.databinding.ActivityMainBinding
import com.ssafy.cleanstore.db.TmpBox
import com.ssafy.cleanstore.fragment.MainFragment
import com.ssafy.cleanstore.fragment.StoreFragment



class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val tabTitle = arrayOf("홈", "상가정보")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager2.adapter = ViewPagerAdapter(this)
        binding.viewPager2.setPageTransformer(ZoomOutPageTransformer())
        binding.viewPager2.registerOnPageChangeCallback(pageChangeCallBack())

        // 스와이프 차단
        binding.viewPager2.isUserInputEnabled = false

        //tool bar 추가
        setSupportActionBar(binding.toolbar) // Custom Toolbar를 액티비티의 ActionBar로 설정

        Glide.with(this)
            .load(TmpBox.profileImg)
            .override( 120) // 이미지 크기 조정
            .into(binding.toolbarImage)


        binding.toolbarTitle.text = TmpBox.nickname // TextView에 텍스트 추가
        binding.toolbar.setTitle(TmpBox.nickname)

        binding.bottomNavBar.setOnItemSelectedListener {
            navigationSelected(it)
        }

    }


    private inner class pageChangeCallBack : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            binding.bottomNavBar.selectedItemId = when (position) {
                0 -> R.id.mainFragment
                1 -> R.id.storeFragment
                else -> error("no such position : ${position}")
            }

            binding.bottomNavBar.menu.getItem(position).isChecked = true
        }

    }

    private fun navigationSelected(item: MenuItem): Boolean {
        val checked = item.setChecked(true)
        when (checked.itemId) {
            R.id.detailInfoMenu -> {
                binding.viewPager2.currentItem = 0
                return true
            }
            R.id.storeInfoMenu -> {
                binding.viewPager2.currentItem = 1
                return true
            }
        }
        return false
    }

    inner class ViewPagerAdapter(activity: MainActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int {
            return tabTitle.size
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> MainFragment()
                1 -> StoreFragment()
                else -> MainFragment()
            }
        }
    }

    class ZoomOutPageTransformer : ViewPager2.PageTransformer {
        private val MIN_SCALE = 0.85f
        private val MIN_ALPHA = 0.5f

        override fun transformPage(view: View, position: Float) {
            view.apply {
                val pageWidth = width
                val pageHeight = height
                when {
                    position < -1 -> { // [-Infinity,-1)
                        // This page is way off-screen to the left.
                        alpha = 0f
                    }
                    position <= 1 -> { // [-1,1]
                        // Modify the default slide transition to shrink the page as well
                        val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                        val vertMargin = pageHeight * (1 - scaleFactor) / 2
                        val horzMargin = pageWidth * (1 - scaleFactor) / 2
                        translationX = if (position < 0) {
                            horzMargin - vertMargin / 2
                        } else {
                            horzMargin + vertMargin / 2
                        }

                        // Scale the page down (between MIN_SCALE and 1)
                        scaleX = scaleFactor
                        scaleY = scaleFactor

                        // Fade the page relative to its size.
                        alpha = (MIN_ALPHA +
                                (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                    }
                    else -> { // (1,+Infinity]
                        // This page is way off-screen to the right.
                        alpha = 0f
                    }
                }
            }
        }
    }


}