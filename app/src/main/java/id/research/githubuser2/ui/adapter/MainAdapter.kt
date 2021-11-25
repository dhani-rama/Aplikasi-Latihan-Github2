package id.research.githubuser2.ui.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.research.githubuser2.databinding.ItemUserBinding
import id.research.githubuser2.models.ItemsItem
import id.research.githubuser2.ui.Constant
import id.research.githubuser2.ui.activity.DetailUserActivity

class MainAdapter : RecyclerView.Adapter<MainAdapter.listUserHolderView>() {

    private var listUsers = ArrayList<ItemsItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setListUser(listUser: ArrayList<ItemsItem>?){
        if(listUser == null) return
        this.listUsers.clear()
        this.listUsers.addAll(listUser)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): listUserHolderView {
        val itemUserBinding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return listUserHolderView(itemUserBinding)
    }

    override fun onBindViewHolder(holder: listUserHolderView, position: Int) {
        val listUsers = listUsers[position]
        holder.bind(listUsers)
    }

    override fun getItemCount(): Int {
        return listUsers.size
    }

    class listUserHolderView(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(usersItem: ItemsItem){
            with(binding){
                tvUsername.text = usersItem.login
                tvId.text = usersItem.id

                Glide.with(itemView.context)
                    .load(usersItem.avatarUrl)
                    .apply(RequestOptions().override(48, 48))
                    .into(imgUser)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailUserActivity::class.java)
                        .apply {
                            putExtra(Constant.USERS, usersItem)
                        }
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

}