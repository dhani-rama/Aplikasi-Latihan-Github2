package id.research.githubuser2.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.research.githubuser2.databinding.ItemUserBinding
import id.research.githubuser2.models.ListFollowersResponseItem

class FollowersAdapter : RecyclerView.Adapter<FollowersAdapter.listFollowersHolderView>() {

    private var listFollowers = ArrayList<ListFollowersResponseItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setListFollowers(listFollowers: ArrayList<ListFollowersResponseItem>?) {
        if (listFollowers == null) return
        this.listFollowers.clear()
        this.listFollowers.addAll(listFollowers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FollowersAdapter.listFollowersHolderView {
        val userFollowersBinding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return listFollowersHolderView(userFollowersBinding)
    }

    override fun onBindViewHolder(holder: FollowersAdapter.listFollowersHolderView, position: Int) {
        val listUsers = listFollowers[position]
        holder.bind(listUsers)
    }

    override fun getItemCount(): Int {
        return listFollowers.size
    }

    class listFollowersHolderView(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userFollowers: ListFollowersResponseItem) {
            with(binding) {
                tvName.text = userFollowers.login
                tvUsername.text = userFollowers.id.toString()

                Glide.with(itemView.context)
                    .load(userFollowers.avatarUrl)
                    .apply(RequestOptions().override(48, 48))
                    .into(imgUser)
            }
        }
    }
}