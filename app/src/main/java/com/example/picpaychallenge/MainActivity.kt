package com.example.picpaychallenge

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.picpaychallenge.API.UsersService
import com.example.picpaychallenge.Model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item_layout.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var mytextoView:TextView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeAPI()
    }

    class UserListAdapter(private val context: Context, private val dataSource: MutableList<User>):
        RecyclerView.Adapter<UserListAdapter.ViewHolder>()  {

        class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

            var userImg: ImageView
            var userUsername: TextView
            var userName: TextView

            init {
                userImg = itemView.userImg
                userUsername = itemView.userUsername
                userName = itemView.userName
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            var itemView = LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false)
            return ViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return dataSource.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.userUsername.text = dataSource[position].username
            holder.userName.text = dataSource[position].name
            Picasso.with(context).load(dataSource[position].img).into(holder.userImg)

            var mContext = context

            holder.itemView.setOnClickListener {
                val intent = Intent(mContext,CadastroActivity::class.java)
                mContext.startActivity(intent)
            }
        }

    }


    private fun initializeAPI() {

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://careers.picpay.com/tests/mobdev/")
            .build()

        var usersInterface: UsersService = retrofit.create(UsersService::class.java)

        var call: Call<MutableList<User>> = usersInterface.getUsers()

        call.enqueue(object : Callback<MutableList<User>> {

            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<MutableList<User>>,
                response: Response<MutableList<User>>
            ) {

                adapter = UserListAdapter(baseContext, response.body() as MutableList<User>)
                myRecycleView.adapter = adapter
                layoutManager = LinearLayoutManager(baseContext)
                myRecycleView.layoutManager = layoutManager
            }

            override fun onFailure(call: Call<MutableList<User>>, t: Throwable) {
                mytextoView.text = t.message.toString()
            }
        })
    }

}
