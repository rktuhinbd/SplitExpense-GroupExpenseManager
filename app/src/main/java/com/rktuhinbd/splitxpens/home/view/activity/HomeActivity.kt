package com.rktuhinbd.splitxpens.home.view.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.GsonBuilder
import com.rktuhinbd.splitxpens.R
import com.rktuhinbd.splitxpens.add_member.viewmodel.AddMemberViewModel
import com.rktuhinbd.splitxpens.databinding.ActivityHomeBinding
import com.rktuhinbd.splitxpens.home.view.adapter.ViewPagerAdapter
import com.rktuhinbd.splitxpens.home.view.fragment.ExpensesFragment
import com.rktuhinbd.splitxpens.home.view.fragment.HomeFragment
import com.rktuhinbd.splitxpens.utils.NetworkUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: ViewPagerAdapter

    private val TAG = "HomeActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()
        initListeners()
        setupViewPager()
        setupTabLayout()
    }

    private fun initComponents() {

        tabLayout = binding.appBarHome.home.tabLayout
        viewPager = binding.appBarHome.home.viewPager

        setSupportActionBar(binding.appBarHome.toolbar)
    }


    private fun initListeners() {

        binding.appBarHome.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun setupViewPager() {

        viewPager.offscreenPageLimit = 1

        adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        val fragment1 = HomeFragment()
        val fragment2 = ExpensesFragment()
        adapter.addFragment(fragment1, "Overview")
        adapter.addFragment(fragment2, "Expenses")
        adapter.notifyDataSetChanged()

        viewPager.isUserInputEnabled = false

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }

    private fun setupTabLayout() {

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.setCurrentItem(tab?.position!!, true)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Write code to handle tab reselect
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Write code to handle tab reselect
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }
}