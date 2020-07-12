package com.example.githubapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapi.model.GithubResponse
import com.example.githubapi.adapter.RepoAdapter

const val GITHUB_RESPONSE = "GithubResponse"

class UserFragment : Fragment() {
    private lateinit var userRepo: Array<GithubResponse>
    private lateinit var repoList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userRepo = it.getParcelableArray(GITHUB_RESPONSE) as Array<GithubResponse>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (userRepo.isNotEmpty()) {
            activity?.title = userRepo[0].owner.login
        }

        repoList = view.findViewById(R.id.repo_view)
        repoList.layoutManager = LinearLayoutManager(activity)
        repoList.adapter = RepoAdapter(userRepo)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: MutableList<GithubResponse>) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArray(GITHUB_RESPONSE, param1.toTypedArray())
                }
            }
    }
}