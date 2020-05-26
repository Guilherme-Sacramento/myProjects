package com.example.picpaychallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_form_cadastro.*

class FormCadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_cadastro)

        window.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
                    and WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        FormRegisterNavBar.setNavigationOnClickListener {
            finish()
        }

        cardNumberInput.addTextChangedListener(validateForm)
        holderNameInput.addTextChangedListener(validateForm)
        dueDateInput.addTextChangedListener(validateForm)
        CVVNumberInput.addTextChangedListener(validateForm)

        registerButton.setOnClickListener {
            val intent = Intent(baseContext, PayingActivity::class.java)
            startActivity(intent)
        }
    }

    val validateForm = object: TextWatcher {

        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            try {
                if((cardNumberInput.text!!.length) == 19
                    && holderNameInput.text.toString().isNotEmpty()
                    && dueDateInput.text.toString().length == 5
                    && CVVNumberInput.text!!.length == 3){
                    registerButton.visibility = View.VISIBLE
                }
                else{
                    registerButton.visibility = View.INVISIBLE
                }
            }
            catch (e: Exception){

            }
        }
    }
}
