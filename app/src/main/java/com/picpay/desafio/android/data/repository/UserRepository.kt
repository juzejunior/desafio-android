package com.picpay.desafio.android.data.repository
import com.picpay.desafio.android.data.api.ApiService
import com.picpay.desafio.android.data.api.callback
import com.picpay.desafio.android.model.User

class UserRepository () {
      fun getUsers(onSuccess: (users: List<User>) -> Unit, onError: () -> Unit) {
          val call = ApiService.getService().getUsers()
          call.enqueue(callback({response ->
              response?.body()?.let {
                  val users: List<User> = it
                  onSuccess(users)
              }
          }, {
              onError()
          }))

          /*api.getService().getUsers().enqueue(object : Callback<List<User>> {
           override fun onFailure(call: Call<List<User>>, t: Throwable) {
               onError()
               /*val message = getString(R.string.error)
               progressBar.visibility = View.GONE
               recyclerView.visibility = View.GONE
               Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
                   .show()*/
           }

           override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
             /*  progressBar.visibility = View.GONE
               adapter.users = response.body()!!*/
               //return response.body() as List<User>

           }*/
   }
}