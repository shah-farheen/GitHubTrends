package com.buggyarts.hubtrends.ui.main

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.buggyarts.hubtrends.R
import com.buggyarts.hubtrends.data.model.repositories.GitHubRepo
import com.buggyarts.hubtrends.databinding.ItemRepoBinding
import com.buggyarts.hubtrends.utils.AppConstants
import com.bumptech.glide.Glide

class RepoAdapter(
    private var repoList: ArrayList<GitHubRepo>,
    private var context: Context,
    private var inflater: LayoutInflater = LayoutInflater.from(context)
) : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    private var mExpandedPosition = AppConstants.NULL_INT
    private lateinit var recyclerView: RecyclerView

    private val languageColorDrawable: ColorDrawable by lazy {
        ColorDrawable()
    }

    private val namesComparator: GitHubRepo.NamesComparator by lazy {
        GitHubRepo.NamesComparator()
    }

    private val starsComparator: GitHubRepo.StarsComparator by lazy {
        GitHubRepo.StarsComparator()
    }

    fun addRepos(repos: ArrayList<GitHubRepo>) {
        repoList.addAll(repos)
        notifyDataSetChanged()
    }

    fun clearRepos() {
        repoList.clear()
        notifyDataSetChanged()
    }

    fun sortByName() {
        repoList.sortWith(namesComparator)
        notifyDataSetChanged()
    }

    fun sortByStars() {
        repoList.sortWith(starsComparator)
        notifyDataSetChanged()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val itemRepoBinding: ItemRepoBinding = DataBindingUtil
            .inflate(inflater, R.layout.item_repo, parent, false)
        return RepoViewHolder(itemRepoBinding)
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val currentRepo = repoList[position]
        holder.itemRepoBinding.tvRepoAuthor.text = currentRepo.author
        holder.itemRepoBinding.tvRepoName.text = currentRepo.name
        Glide.with(context).load(currentRepo.avatar).into(holder.itemRepoBinding.ivDp)

        if (position == mExpandedPosition) {
            holder.itemRepoBinding.layoutDetails.visibility = View.VISIBLE
            holder.itemRepoBinding.root.isActivated = true
            // setting the details
            holder.itemRepoBinding.tvRepoDetails.text = currentRepo.description
            holder.itemRepoBinding.tvLanguage.text = currentRepo.language
            holder.itemRepoBinding.tvStars.text = currentRepo.stars.toString()
            holder.itemRepoBinding.tvForks.text = currentRepo.forks.toString()
            // check if color is null/empty
            if (!TextUtils.isEmpty(currentRepo.languageColor)) {
                languageColorDrawable.color = Color.parseColor(currentRepo.languageColor)
                holder.itemRepoBinding.ivLanguageColor.visibility = View.VISIBLE
                holder.itemRepoBinding.ivLanguageColor.setImageDrawable(languageColorDrawable)
            } else {
                holder.itemRepoBinding.ivLanguageColor.visibility = View.GONE
            }
        } else {
            holder.itemRepoBinding.layoutDetails.visibility = View.GONE
            holder.itemRepoBinding.root.isActivated = false
        }
    }

    inner class RepoViewHolder(val itemRepoBinding: ItemRepoBinding) :
        RecyclerView.ViewHolder(itemRepoBinding.root) {

        init {
            itemRepoBinding.root.setOnClickListener {
                val currentExpandedPos = mExpandedPosition
                mExpandedPosition = if (adapterPosition == mExpandedPosition) {
                    AppConstants.NULL_INT
                } else {
                    adapterPosition
                }
                TransitionManager.beginDelayedTransition(recyclerView)
                notifyItemChanged(adapterPosition)
                if (currentExpandedPos != AppConstants.NULL_INT) {
                    notifyItemChanged(currentExpandedPos)
                }
            }
        }
    }
}