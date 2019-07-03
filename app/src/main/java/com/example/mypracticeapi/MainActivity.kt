package com.example.mypracticeapi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.mypracticeapi.models.getData.GetLogin
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Users =  arrayListOf<GetLogin>()
        loginBtn.setOnClickListener {
            Api().service.login("abc", "123").enqueue(object : Callback<Api.LoginResponse> {
                override fun onResponse(call: Call<Api.LoginResponse>, response: Response<Api.LoginResponse>) {
                    val data = response.body()
                    if (data != null) {
                        data.let { res ->
                            if (res.error) {
                                println("Eroor")
                            } else {
                                println(res.data.name)
                            }
                        }
                    } else {

                    }
                }

                override fun onFailure(call: Call<Api.LoginResponse>, t: Throwable) {
                    print("Invalid Url")
                }


            })

        }
        registerBtn.setOnClickListener {
            Api().service.register("Usama", "mine@gmail.com", "abc", "12333", 2)
                .enqueue(object : Callback<Api.LoginResponse> {
                    override fun onFailure(call: Call<Api.LoginResponse>, t: Throwable) {
                        println("Some Worng")
                    }

                    override fun onResponse(call: Call<Api.LoginResponse>, response: Response<Api.LoginResponse>) {
                        response.body()?.let { res ->
                            val status = res.error
                            val nData = res.data
                            if (!status) {

                                println(nData.name)
                                println(nData.email)
                                println(nData.age)
                                println(nData.username)
                                println(nData.password)
                            } else {
                                println("kUch Musla hia")
                            }
                        }

                        println()
                    }

                })
        }

        getUserBtn.setOnClickListener {
            Api().service.getUser("abc").enqueue(object : Callback<Api.UserRec> {


                override fun onResponse(call: Call<Api.UserRec>, response: Response<Api.UserRec>) {
                    response.body()?.let { res ->
                        val status = res.error
                        val nData = res.data
                        if(!status){
                            Users.addAll(nData)
                            println(Users.toString())
                        }else{
                            println("invalid username")
                        }
                    }

                }
                override fun onFailure(call: Call<Api.UserRec>, t: Throwable) {
                    println("fail")
                }

            })
        }


    }
}
