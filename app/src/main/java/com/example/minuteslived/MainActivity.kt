package com.example.minuteslived

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button:Button = findViewById(R.id.buttonDatePicker)
        button.setOnClickListener { view ->
             clickDatePicker(view)
        }
    }

    fun clickDatePicker(view: View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

     val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            Toast.makeText(this,"DatePicker Works", Toast.LENGTH_LONG).show()

            val selectedDate = "$year/${month+1}/$dayOfMonth"
            var textBirthDate:TextView = findViewById(R.id.tvSelectedBirthDate)
            textBirthDate.setText(selectedDate)

            val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)

            val selectedDateInMintues = theDate!!.time/60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate!!.time/60000

            val differenceInMinutes = currentDateInMinutes - selectedDateInMintues

            var textInMinutes:TextView = findViewById(R.id.tvTextMinutesLived)
            textInMinutes.setText(differenceInMinutes.toString())


        }
                ,year,month,day)
        dpd.datePicker.setMaxDate(Date().time - 86400000) // Limiting the date till present, can't select future dates
        dpd.show()
    }
}