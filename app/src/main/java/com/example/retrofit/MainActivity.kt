package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.adapter.MyAdapter
import com.example.retrofit.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val adapter by lazy {
        MyAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpRecyclerView()
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

        viewModel.getCustomPost(2,"id","desc")
        viewModel.myCustomPost.observe(this, Observer { response ->
                if (response.isSuccessful){
                    response.body()?.let { adapter.setData(it) }
                }else{
                    Toast.makeText(this,response.code().toString(),Toast.LENGTH_LONG).show()
                }
        })
    }

    private fun setUpRecyclerView(){
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}