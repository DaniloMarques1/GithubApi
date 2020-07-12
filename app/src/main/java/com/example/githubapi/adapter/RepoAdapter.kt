package com.example.githubapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapi.R
import com.example.githubapi.model.GithubResponse
import com.example.githubapi.model.ListRepo

class RepoAdapter(private val repoList: Array<GithubResponse>): RecyclerView.Adapter<ListRepo>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListRepo {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_repo_view_holder, parent, false)

        return ListRepo(view)
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    override fun onBindViewHolder(holder: ListRepo, position: Int) {
        holder.repoName.text = repoList[position].name
        val dt = formatDate(repoList[position].created_at.split('T')[0])
        holder.repoDate.text = dt
    }

    //TODO: learn how to make this better
    private fun formatDate(date: String): String {
        val year = date.substring(0, 4)
        val month = date.substring(5, 7)
        val day = date.substring(8, 10)

        return "${day}/${month}/${year}"
    }
}
