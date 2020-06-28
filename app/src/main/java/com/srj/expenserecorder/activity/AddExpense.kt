package com.srj.expenserecorder.activity

import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.srj.expenserecorder.R
import com.srj.expenserecorder.modals.Expense
import kotlinx.android.synthetic.main.activity_add_expense.*

class AddExpense : AppCompatActivity() {
    private lateinit var mdateSetListener: OnDateSetListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)


        btn_save.setOnClickListener {
            val date = txt_input_date.text.toString()
            val item = txt_input_item.text.toString()
            val cost = txt_input_cost.text.toString()
            when {
                date.isEmpty() -> Toast.makeText(this, "Date is Required", Toast.LENGTH_SHORT)
                    .show()
                item.isEmpty() -> Toast.makeText(this, "Item name is Required", Toast.LENGTH_SHORT)
                    .show()
                cost.isEmpty() -> Toast.makeText(this, "cost is Required", Toast.LENGTH_SHORT)
                    .show()
                else -> {
                    val expense = Expense()
                    expense.date = date
                    expense.item_name = item
                    expense.cost = cost
                    MainActivity.dBhandler.addExpenses(this, expense)
                }
            }

        }
    }

  /*  private fun setDate() {
        txt_input_date.setOnClickListener {
            val cal = Calendar.getInstance()
            val year = cal[Calendar.YEAR]
            val month = cal[Calendar.MONTH]
            val day = cal[Calendar.DAY_OF_MONTH]
            val dialog = DatePickerDialog(
                this,
                android.R.style.Theme_DeviceDefault_Dialog_Alert,
                mdateSetListener,
                year, month, day
            )
            dialog.window
                ?.setBackgroundDrawable(ColorDrawable(Color.DKGRAY))
            dialog.show()
        }
        mdateSetListener =
            OnDateSetListener { _, year, month, dayOfMonth ->
                var month = month
                month += 1

                val date = "$dayOfMonth/$month/$year"

                txt_input_date.text = date
            }

    }*/

}