package org.robomoto.ageinminutes

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

        btnDatePicker.setOnClickListener{view ->
            clickDatePicker(view)
        }
    }

    fun clickDatePicker(view: View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener {
                        view, selectedYear, selectedMonth, selectedDayOfMonth ->
                    Toast.makeText(this, "Chosen year: $selectedYear, Month: $selectedMonth, Day: $selectedDayOfMonth" , Toast.LENGTH_LONG).show()
                    val selectedDate = "${selectedMonth+1}/$selectedDayOfMonth/$selectedYear"

                    tvSelectedDate.setText(selectedDate)

                    val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.US)
                    val theDate = sdf.parse(selectedDate)
                    val selectedDateInMinutes = theDate!!.time / 60000
                    val now = sdf.parse(sdf.format(System.currentTimeMillis()))
                    val nowToMinutes = now!!.time / 60000

                    val differenceInMinutes = nowToMinutes - selectedDateInMinutes

                    tvSelectedDateInMinutes.setText(differenceInMinutes.toString())
                },
                year,
                month,
                day)

        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }


}