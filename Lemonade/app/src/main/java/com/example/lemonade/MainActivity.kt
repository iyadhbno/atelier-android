package com.example.lemonade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {


    private val LEMONADE_STATE = "LEMONADE_STATE"
    private val LEMON_SIZE = "LEMON_SIZE"
    private val SQUEEZE_COUNT = "SQUEEZE_COUNT"
    private val SELECT = "select"
    private val SQUEEZE = "squeeze"
    private val DRINK = "drink"
    private val RESTART = "restart"
    private var lemonadeState = "select"
    private var lemonSize = -1
    private var squeezeCount = -1

    private var lemonTree = LemonTree()
    private var lemonImage: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            lemonadeState = savedInstanceState.getString(LEMONADE_STATE, "select")
            lemonSize = savedInstanceState.getInt(LEMON_SIZE, -1)
            squeezeCount = savedInstanceState.getInt(SQUEEZE_COUNT, -1)
        }


        lemonImage = findViewById(R.id.image_lemon_state)
        setViewElements()

        lemonImage!!.setOnClickListener {
            clickLemonImage()
        }

        lemonImage!!.setOnLongClickListener {

            showSnackbar()
        }
    }




    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(LEMONADE_STATE, lemonadeState)
        outState.putInt(LEMON_SIZE, lemonSize)
        outState.putInt(SQUEEZE_COUNT, squeezeCount)
        super.onSaveInstanceState(outState)
    }

    private fun clickLemonImage() {








        var conditionSortie = false

        if (lemonadeState == SELECT && !conditionSortie) {
            lemonadeState = "squeeze"
            val  lemonTree = LemonTree()
            lemonSize = lemonTree.pick()
            squeezeCount = 0
            conditionSortie = true;
        }
        if (lemonadeState == SQUEEZE && !conditionSortie) {
            if(lemonSize == 0){
                lemonadeState = "drink"
                lemonSize = -1
                conditionSortie = true;
            }else{
                lemonSize--
                squeezeCount++
            }
        }
        if (lemonadeState == DRINK && !conditionSortie) {
            lemonadeState = "restart"
            conditionSortie = true;
        }
        if (lemonadeState == RESTART && !conditionSortie) {
            lemonadeState = "select"
            conditionSortie = true;
        }
        println(lemonadeState)

        setViewElements()

    }

    private fun setViewElements() {
        val textAction: TextView = findViewById(R.id.text_action)
        if (lemonadeState == SELECT) {
            lemonImage!!.setImageResource(R.drawable.lemon_tree)
            textAction.setText(R.string.lemon_select)
        }
        if (lemonadeState == SQUEEZE) {
            lemonImage!!.setImageResource(R.drawable.lemon_squeeze)
            textAction.setText(R.string.lemon_squeeze)
        }

        if (lemonadeState == DRINK) {
            lemonImage!!.setImageResource(R.drawable.lemon_drink)
            textAction.setText(R.string.lemon_drink)
        }

        if (lemonadeState == RESTART) {
            lemonImage!!.setImageResource(R.drawable.lemon_restart)
            textAction.setText(R.string.lemon_empty_glass)
        }


    }



    private fun showSnackbar(): Boolean {
        if (lemonadeState != SQUEEZE) {
            return false
        }
        val squeezeText = getString(R.string.squeeze_count, squeezeCount)
        Snackbar.make(
            findViewById(R.id.constraint_Layout),
            squeezeText,
            Snackbar.LENGTH_SHORT
        ).show()
        return true
    }
}


class LemonTree {
    fun pick(): Int {
        return (2..4).random()
    }
}
