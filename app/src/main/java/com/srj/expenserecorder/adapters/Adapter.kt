package com.srj.expenserecorder.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.srj.expenserecorder.modals.Expense
import com.srj.expenserecorder.R
import kotlinx.android.synthetic.main.list_expense.view.*

class Adapter (private val context: Context, private val expense: ArrayList<Expense> ) :
    RecyclerView.Adapter<Adapter.MyViewholder>() {
    class MyViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt_date= itemView.txt_date!!
        val item_name = itemView.txt_item_name!!
        val cost = itemView.txt_cost!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_expense,parent,false)
        return MyViewholder(view)
    }

    override fun getItemCount(): Int {
        return expense.size
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        val expense1: Expense = expense[position]
        holder.txt_date.text = expense1.date
        holder.item_name.text = expense1.item_name
        holder.cost.text = expense1.cost
    }

}