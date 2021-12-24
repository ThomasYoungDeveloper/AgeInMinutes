package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)
        }
    }

    val myCalendar = Calendar.getInstance()
    val year = myCalendar.get(Calendar.YEAR)
    val month = myCalendar.get(Calendar.MONTH)
    val day = myCalendar.get(Calendar.DAY_OF_MONTH)
    fun clickDatePicker(view: View) {

       val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                    view, selectedYear, selectedMonth, selectedDayOfMonth ->
                //Toast.makeText(this, "The chosen year is $selectedYear, the month is ${selectedMonth + 1 } and the day is $selectedDayOfMonth", Toast.LENGTH_LONG).show()

                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate.text = "$selectedMonth/${selectedMonth + 1}/$selectedYear"



                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                val selectedDateInMinutes = theDate!!.time / 60000

                val selectedDateInHours =   theDate.time / 3600000

                val selectedDateInDays =   theDate.time / 86400000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateToMinutes = currentDate!!.time / 60000

                val currentDateToHours = currentDate.time / 3600000

                val currentDateToDays = currentDate.time / 86400000

                val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes

                val differenceInHours = currentDateToHours - selectedDateInHours

                val differenceInDays = currentDateToDays - selectedDateInDays


                tvSelectedDateInMinutes.text = differenceInMinutes.toString()
                tvSelectedDateInHours.text = differenceInHours.toString()
                tvSelectedDateInDays.text = differenceInDays.toString()


//                Toast.makeText(this, "$selectedDateInMinutes",
//                    Toast.LENGTH_LONG).show();
            }
            ,year
            ,month
            ,day)

        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }
}