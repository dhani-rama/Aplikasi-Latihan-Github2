package id.research.githubuser2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.research.githubuser2.databinding.ItemUserBinding
import id.research.githubuser2.models.ListFollowingResponseItem

class FollowingAdapter : RecyclerView.Adapter<FollowingAdapter.listFollowingHolderView>() {

    private var listFollowing = ArrayList<ListFollowingResponseItem>()

    fun setListFollowing(listFollowing: ArrayList<ListFollowingResponseItem>?) {
        if (listFollowing == null) return
        this.listFollowing.clear()
        this.listFollowing.addAll(listFollowing)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FollowingAdapter.listFollowingHolderView {
        val userFollowingBinding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return listFollowingHolderView(userFollowingBinding)
    }

    override fun onBindViewHolder(holder: FollowingAdapter.listFollowingHolderView, position: Int) {
        val listUsers = listFollowing[position]
        holder.bind(listUsers)
    }

    override fun getItemCount(): Int {
        return listFollowing.size
    }

    class listFollowingHolderView(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userFollowing: ListFollowingResponseItem) {
            with(binding) {
                tvName.text = userFollowing.login
                tvUsername.text = userFollowing.id.toString()

                Glide.with(itemView.context)
                    .load(userFollowing.avatarUrl)
                    .apply(RequestOptions().override(48, 48))
                    .into(imgUser)
            }
        }
    }
}