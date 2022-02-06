package com.example.cv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.curriculumvitae.R
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private var textPlainName: EditText? = null
    private var textPlainAge: EditText? = null
    private var textPlainEmail: EditText? = null
    private var buttonNext: Button? = null
    private var buttonReset: Button? = null
    private var radioGroup: RadioGroup? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textPlainName = findViewById(R.id.textPlainName);
        textPlainAge = findViewById(R.id.editTextAge);
        textPlainEmail = findViewById(R.id.editTextEmail);
        radioGroup = findViewById(R.id.radioGroup)
        buttonNext = findViewById(R.id.button2)
        buttonReset = findViewById(R.id.buttonReset)

        buttonNext!!.setOnClickListener{
            if(textPlainName!!.text.toString() == "" || textPlainAge!!.text.toString() == ""
                || textPlainEmail!!.text.toString() == ""){
                Snackbar.make(
                    findViewById(R.id.button2),
                    "Completer tout les champs",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            else
            {
                Snackbar.make(
                    findViewById(R.id.button2),
                    "Vous etes excellent en Android!",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
        buttonReset!!.setOnClickListener{
            textPlainName!!.setText("")
            textPlainAge!!.setText("")
            textPlainEmail!!.setText("")
            radioGroup!!.check(R.id.radioButtonHomme)
        }
    }
}