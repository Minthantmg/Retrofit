package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)


        button.setOnClickListener {
            val myNum = number_editText.text.toString()
            viewModel.getCustomPost(Integer.parseInt(myNum),"id","desc")
            viewModel.myCustomPost.observe(this, Observer { response ->
                if (response.isSuccessful){
                    textView.text = response.body()?.toString()
                    response.body()?.forEach {
                        Log.d("Responese",it.userId.toString())
                        Log.d("Responese",it.id.toString())
                        Log.d("Responese",it.title)
                        Log.d("Responese",it.body)
                        Log.d("Responese","-------------")
                    }
                }else{
                    textView.text = response.code().toString()
                }
            })
        }
    }
}