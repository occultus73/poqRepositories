package io.github.occultus73.poqrepositories.framework.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.occultus73.poqrepositories.R
import io.github.occultus73.poqrepositories.business.domain.model.SquareReposItem
import kotlinx.android.synthetic.main.square_repos_item.view.*


class RVAdapter : ListAdapter<SquareReposItem, RVAdapter.RepoHolder>(DiffCallback) {

    private object DiffCallback : DiffUtil.ItemCallback<SquareReposItem>() {

        override fun areItemsTheSame(oldItem: SquareReposItem, newItem: SquareReposItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SquareReposItem,
            newItem: SquareReposItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    inner class RepoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int) {
            with(itemView) {
                val currentRepo: SquareReposItem = getItem(position)
                text_name.text = currentRepo.name
                text_description.text = currentRepo.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.square_repos_item, parent, false)
        return RepoHolder(itemView)
    }

    override fun onBindViewHolder(holder: RepoHolder, position: Int) = holder.bind(position)
}
