package com.srj.expenserecorder.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.srj.expenserecorder.databases.DBhandler
import com.srj.expenserecorder.R
import com.srj.expenserecorder.adapters.Adapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var dBhandler: DBhandler
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dBhandler =
            DBhandler(this)

        viewData()

        fab_save.setOnClickListener {
            Intent(this, AddExpense::class.java).apply {
                startActivity(this)
            }
        }
    }


    private fun viewData() {
        val expenseList = dBhandler.getdata(this)
        val adapter = Adapter(this, expenseList)
        val rv: RecyclerView = findViewById(R.id.expenseRecyclerview)
        val layoutManager = LinearLayoutManager(applicationContext)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rv.layoutManager = layoutManager
        rv.adapter = adapter
    }

    override fun onResume() {
        viewData()
        super.onResume()
    }
}