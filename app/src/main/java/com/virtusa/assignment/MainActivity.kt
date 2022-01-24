package com.virtusa.assignment

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.bumptech.glide.Glide
import kotlinx.coroutines.*

/**
    2.
    Write a program to call the below mentioned rest APIs.
    Display the email ids returned by the GET method
 */

class MainActivity : AppCompatActivity() {

    private lateinit var edtParam: EditText
    private lateinit var btnSubmit: Button
    private lateinit var imgAvatar: ImageView
    private lateinit var txtResponse: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var client: ApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtParam = findViewById(R.id.param)
        btnSubmit = findViewById(R.id.submit)
        imgAvatar = findViewById(R.id.avatar)
        txtResponse = findViewById(R.id.response)
        progressBar = findViewById(R.id.progressBar)

        client = ApiBuilder.getInstance().create(ApiInterface::class.java)

        btnSubmit.setOnClickListener {
            btnSubmit.hideKeyboard()
            val input = edtParam.text.toString()
            if (input != "") {
                progressBar.visibility = View.VISIBLE
                callRestApi(input)
            } else {
                Toast.makeText(
                    this,
                    "Please give proper input.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }

    private fun callRestApi(input: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = client.getUserDetails(input)
            withContext(Dispatchers.Main) {
                if (result.code() == 200) {
                    txtResponse.text = result.body()?.data?.let {
                        "First Name : ${it.first_name} \n" +
                                "Last Name : ${it.last_name} \n" +
                                "Email : ${it.email}."
                    }
                    imgAvatar.visibility = View.VISIBLE
                    Glide.with(this@MainActivity)
                        .load(result.body()?.data?.avatar)
                        .error(R.drawable.ic_launcher_background)
                        .into(imgAvatar)

                    progressBar.visibility = View.GONE

                } else {
                    txtResponse.text = "Wrong input. Please provide proper input."
                    imgAvatar.visibility = View.GONE
                    progressBar.visibility = View.GONE
                }

            }
        }
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}