package com.buggyarts.hubtrends.ui.main

import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.buggyarts.hubtrends.R
import com.buggyarts.hubtrends.databinding.ActivityMainBinding
import com.buggyarts.hubtrends.ui.base.BaseActivity
import com.buggyarts.hubtrends.ui.devs.DevelopersFragment
import com.buggyarts.hubtrends.ui.repositories.RepositoriesFragment

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var popupMenu: PopupMenu

    /**
     * Set binding and viewModels here as this is the first thing executed in onCreate()
     */
    override fun setupDependencies() {
        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this)[MainViewModel::class.java]
        viewModel.initRepository(repositoryComponent)
    }

    override fun initViews() {
        // init PopupMenu
        popupMenu = PopupMenu(this, binding.ivThreeDots)
        popupMenu.inflate(R.menu.menu_main)

        binding.viewPager.adapter = MainViewPagerAdapter(this)
        binding.viewPager.setPageTransformer(null)
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

//        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
//        supportFragmentManager.beginTransaction()
//            .add(binding.container.id, RepositoriesFragment.newInstance())
//            .commit()
    }

    override fun initListeners() {
//        popupMenu.setOnMenuItemClickListener {
//            when (it.itemId) {
//                R.id.menu_item_stars -> repoAdapter.sortByStars()
//                R.id.menu_item_name -> repoAdapter.sortByName()
//            }
//            return@setOnMenuItemClickListener true
//        }

        binding.ivThreeDots.setOnClickListener {
            popupMenu.show()
        }
    }

    override fun initObservers() {
    }

    class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> RepositoriesFragment.newInstance()
                else -> DevelopersFragment()
            }
        }

        override fun getCount(): Int = 2
    }

    class MainViewPagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> RepositoriesFragment.newInstance()
                else -> DevelopersFragment()
            }
        }
    }
}
