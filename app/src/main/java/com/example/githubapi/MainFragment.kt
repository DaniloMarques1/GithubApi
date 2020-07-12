package com.example.githubapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.githubapi.model.GithubResponse
import com.example.githubapi.service.Apicall
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainFragment : Fragment() {
    private val retrofit =
        Retrofit.Builder().baseUrl(Apicall.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    private lateinit var editText: EditText
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Search User"
        editText = view.findViewById(R.id.github_user)
        button   = view.findViewById(R.id.btn_search)
        button.setOnClickListener {
            getRepos()
        }
    }

    private fun getRepos() {
        val service  = retrofit.create(Apicall::class.java)
        val userName = editText.text.toString()
        editText.setText("")
        val call     = service.getUserRepo(userName)
        call.enqueue(object: Callback<MutableList<GithubResponse>> {
            override fun onFailure(call: Call<MutableList<GithubResponse>>?, t: Throwable?) {
                Toast.makeText(context, "Erro na requisicao ${t?.message}", Toast.LENGTH_SHORT ).show()
            }

            override fun onResponse(
                call: Call<MutableList<GithubResponse>>?,
                response: Response<MutableList<GithubResponse>>?
            ) {
                if (response?.code() == 200) {
                    val body = response?.body()!!
                    val bundle = Bundle()
                    bundle.putParcelableArray("GithubResponse", body.toTypedArray())
                    findNavController().navigate(R.id.action_mainFragment_to_userFragment, bundle)
                }
            }

        })
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MainFragment().apply {}
    }
}