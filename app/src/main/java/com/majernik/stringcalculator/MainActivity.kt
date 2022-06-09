package com.majernik.stringcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.majernik.stringcalculator.utils.SumCalculator
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputStringEditView = findViewById<EditText>(R.id.inputString)
        val sumButton = findViewById<Button>(R.id.sumButton)
        val sumLabel = findViewById<TextView>(R.id.sum)

        sumButton.setOnClickListener {
            try {
                val sum = SumCalculator.add(inputStringEditView.text.toString())
                sumLabel.text = "Sum is: $sum"
                sumLabel.setTextColor(R.color.black)
            } catch (e: Exception) {
                sumLabel.text = e.message
                sumLabel.setTextColor(R.color.error)
            }
        }
    }

}