package com.picpay.desafio.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.data.base.UserViewModelFactory
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.data.vm.UserViewModel
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
      userViewModel = ViewModelProvider(this, UserViewModelFactory(UserRepository()))
          .get(UserViewModel::class.java)
      adapter = UserListAdapter()
    }

    private fun getData() {
       userViewModel.getUsers()
       userViewModel.users.observe(this, Observer { users ->
          recyclerView.also {
              it.layoutManager = LinearLayoutManager(this)
              it.setHasFixedSize(true)
              it.adapter = adapter
              adapter.users = users
          }
       })
    }

    /*override fun onResume() {
        super.onResume()

       /* recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        progressBar.visibility = View.VISIBLE

        ApiService.invoke().getUsers()
            .enqueue(object : Callback<List<User>> {
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    val message = getString(R.string.error)

                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.GONE

                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    progressBar.visibility = View.GONE

                    adapter.users = response.body()!!
                }
            })*/
    }*/
}
