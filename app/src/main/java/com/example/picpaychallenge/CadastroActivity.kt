package com.example.picpaychallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        registerNavBar.setNavigationOnClickListener(View.OnClickListener {
            finish()
        })

        registerButton.setOnClickListener {
            var intent = Intent(baseContext, FormCadastroActivity::class.java)
            startActivity(intent)
        }
    }
}
