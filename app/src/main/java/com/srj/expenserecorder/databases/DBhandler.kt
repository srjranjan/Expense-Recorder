package com.srj.expenserecorder.databases

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import com.srj.expenserecorder.modals.Expense
import android.database.sqlite.SQLiteOpenHelper as SQLiteOpenHelper1

class DBhandler(context: Context) :
    SQLiteOpenHelper1(context,
        DATABASE_NAME,
        factory,
        version
    ) {
    companion object {
        private const val DATABASE_NAME = "Database.db"
        val factory = null

        const val version = 1

        const val Table_name = "Expense"
        const val Col_date = "Date"
        const val Col_Item_name = "Item Name"
        const val Col_Amount = "Amount"

    }

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("create TABLE $Table_name($Col_date STRING, $Col_Item_name, $Col_Amount INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $Table_name")
        onCreate(db)
    }

    fun getdata(context: Context): ArrayList<Expense> {
        val query = "Select * From $Table_name"
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        val expense2 = ArrayList<Expense>()
        if (cursor.count == 0)
            Toast.makeText(context, "No record Found", Toast.LENGTH_SHORT).show()
        else {
            while (cursor.moveToNext()) {
                val expense = Expense()
                expense.date = cursor.getString(cursor.getColumnIndex(Col_date))
                expense.item_name = cursor.getString(cursor.getColumnIndex(Col_Item_name))
                expense.cost = cursor.getString(cursor.getColumnIndex(Col_Amount))
                expense2.add(expense)
            }
            Toast.makeText(context, "${cursor.count} Record Found", Toast.LENGTH_SHORT).show()
        }
        cursor.close()
        db.close()
        return expense2
    }

    fun addExpenses(context: Context, expense: Expense) {
        val values = ContentValues()
        values.put(Col_date, expense.date)
        values.put(Col_Item_name, expense.item_name)
        values.put(Col_Amount, expense.cost)
        val db = this.writableDatabase
        try {
            db.insert(Table_name, null, values)
            Toast.makeText(context, "Recorded", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(context, "Not Recorded", Toast.LENGTH_SHORT).show()
        }

        db.close()

    }
}