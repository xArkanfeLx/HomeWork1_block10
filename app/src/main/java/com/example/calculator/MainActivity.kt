package com.example.calculator

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var toolbarMain: Toolbar

    private lateinit var firstTimeET: EditText
    private lateinit var secondTimeET: EditText

    private lateinit var sumBTN: Button
    private lateinit var difBTN: Button

    private lateinit var resultTV: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = "Калькулятор"
        toolbarMain.subtitle = "времени"
        toolbarMain.setLogo(R.drawable.ic_calculate)

        firstTimeET = findViewById(R.id.firstTimeET)
        secondTimeET = findViewById(R.id.secondTimeET)
        sumBTN = findViewById(R.id.sumBTN)
        difBTN = findViewById(R.id.difBTN)
        resultTV = findViewById(R.id.resultTV)

        sumBTN.setOnClickListener(this)
        difBTN.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.resetMenuMain -> {
                firstTimeET.text.clear()
                secondTimeET.text.clear()
                resultTV.setTextColor(Color.parseColor("#000000"))
                resultTV.text = "Тут будет результат"
                val toast =
                    Toast.makeText(applicationContext, "Данные очищены", Toast.LENGTH_LONG).show()
            }

            R.id.exitMenuMain -> {
                val toast =
                    Toast.makeText(applicationContext, "Приложение закрыто", Toast.LENGTH_LONG)
                        .show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View) {
        if (firstTimeET.text.isEmpty() || secondTimeET.text.isEmpty()) return
        val time1 = firstTimeET.text.toString()
        val time2 = secondTimeET.text.toString()
        resultTV.text = when (v.id) {
            R.id.sumBTN -> TimeCalculator(time1, time2).sum()
            R.id.difBTN -> TimeCalculator(time1, time2).dif()
            else -> "Куда то не туда нажали"
        }
        resultTV.setTextColor(Color.parseColor("#8B0000"))
        val toast = Toast.makeText(applicationContext,"Результат ${resultTV.text}",Toast.LENGTH_SHORT).show()
    }


}