package com.github.leleact.drawerlayoutexample

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private var mPlanetTitles: ArrayList<String> = ArrayList()
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var left: LinearLayout
    private lateinit var mDrawerList: ListView
    private lateinit var tvMenu: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_example)
        initView()
        initData()
        initEvent()
    }

    private fun initView() {
        mDrawerList = findViewById(R.id.left_drawer)
        mDrawerLayout = findViewById(R.id.drawer_layout)
        left = findViewById(R.id.left)
        tvMenu = findViewById(R.id.tv_menu)
    }

    private fun initData() {

        for (i in 0 until 10) {
            mPlanetTitles.add("left---Line$i")
        }
        //创建并设置适配器
        val adapter = ArrayAdapter(
            applicationContext,
            android.R.layout.activity_list_item,
            android.R.id.text1,
            mPlanetTitles
        )
        mDrawerList.adapter = adapter
    }


    private fun initEvent() {
        //设置ListView的点击条目事件 ,选中后让菜单栏消失
        mDrawerList.setOnItemClickListener { _: AdapterView<*>, _: View, i: Int, _: Long ->
            Toast.makeText(this@MainActivity, "点击了$i", Toast.LENGTH_LONG).show()
            mDrawerLayout.closeDrawers()   //关闭侧边栏的菜单
        }

        //点击菜单按钮，显示菜单
        tvMenu.setOnClickListener {
            mDrawerLayout.openDrawer(left)
        }

        mDrawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerStateChanged(newState: Int) {
                Log.d(TAG, "" + newState)
            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

            }

            override fun onDrawerClosed(drawerView: View) {
                Toast.makeText(this@MainActivity, "关闭了侧边栏", Toast.LENGTH_LONG).show()
            }

            override fun onDrawerOpened(drawerView: View) {
                Toast.makeText(this@MainActivity, "打开了侧边栏", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
