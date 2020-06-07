package com.picpay.desafio.android.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.vm.UserViewModelFactory
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.data.vm.UserViewModel
import com.picpay.desafio.android.utils.invisible
import com.picpay.desafio.android.utils.visible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: UserListAdapter
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initElements()
        getData()
    }

    private fun initElements() {
      userViewModel = ViewModelProvider(this,
          UserViewModelFactory(application)
      )
          .get(UserViewModel::class.java)
      adapter = UserListAdapter()
    }

    private fun getData() {
       user_list_progress_bar.visible()

       userViewModel.users.observe(this, Observer { users ->
              recyclerView.also {
                  it.layoutManager = LinearLayoutManager(this)
                  it.setHasFixedSize(true)
                  it.adapter = adapter
                  adapter.users = users
              }
           user_list_progress_bar.invisible()
       })

        userViewModel.isNetworkError.observe(this, Observer { isNetWorkError ->
            if (isNetWorkError) onNetworkError()
        })
    }

    private fun onNetworkError() {
        val message = getString(R.string.error)
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
            .show()
    }
}
