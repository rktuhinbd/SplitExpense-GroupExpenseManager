package com.rktuhinbd.splitxpens.home

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.rktuhinbd.splitxpens.R
import com.rktuhinbd.splitxpens.databinding.ActivityHomeBinding
import com.rktuhinbd.splitxpens.home.adapter.ViewPagerAdapter
import com.rktuhinbd.splitxpens.home.expenses.ExpensesFragment
import com.rktuhinbd.splitxpens.home.view.HomeFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarHome.toolbar)

        binding.appBarHome.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        tabLayout = binding.appBarHome.home.tabLayout
        viewPager = binding.appBarHome.home.viewPager

        viewPager.offscreenPageLimit = 1

        adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()

        val fragment1 = HomeFragment()
        val fragment2 = ExpensesFragment()
        adapter.addFragment(fragment1, "Overview")
        adapter.addFragment(fragment2, "Expenses")
        adapter.notifyDataSetChanged()

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

        viewPager.isUserInputEnabled = false

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }
}