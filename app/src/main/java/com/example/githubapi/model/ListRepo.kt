package com.example.githubapi.model

import com.example.githubapi.R
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListRepo(view: View): RecyclerView.ViewHolder(view) {
    val repoName: TextView = view.findViewById(R.id.repo_name)
    val repoDate: TextView = view.findViewById(R.id.repo_date)
}