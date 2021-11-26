package id.research.githubuser2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.research.githubuser2.databinding.FragmentFollowingBinding
import id.research.githubuser2.models.ItemsItem
import id.research.githubuser2.models.ListFollowingResponseItem
import id.research.githubuser2.ui.activity.DetailUserActivity
import id.research.githubuser2.ui.adapter.FollowingAdapter
import id.research.githubuser2.utils.Constant
import id.research.githubuser2.viewmodels.MainViewModel


class FollowingFragment : Fragment() {

    private lateinit var mFollowingBinding: FragmentFollowingBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var mFollowingAdapter: FollowingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mFollowingBinding = FragmentFollowingBinding.inflate(inflater, container, false)
        return mFollowingBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mFollowingAdapter = FollowingAdapter()
        val users = (requireActivity() as DetailUserActivity).intent.getParcelableExtra<ItemsItem>(
            Constant.USERS
        ) as ItemsItem

        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        users.login?.let { mainViewModel.followingUser(it) }

        mainViewModel.mFollowing.observe(this.requireActivity(), { mFollowing ->
            mFollowingAdapter.setListFollowing(mFollowing as ArrayList<ListFollowingResponseItem>)
            with(mFollowingBinding.rvList) {
                layoutManager = LinearLayoutManager(activity)
                setHasFixedSize(true)
                adapter = mFollowingAdapter
            }
        })

        mainViewModel.isLoading.observe(this.requireActivity(), {
            showLoading(it)
        })

    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            mFollowingBinding.progressBar.visibility = View.VISIBLE
        } else {
            mFollowingBinding.progressBar.visibility = View.GONE
        }
    }

}