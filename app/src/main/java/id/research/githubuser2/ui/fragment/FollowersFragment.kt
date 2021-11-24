package id.research.githubuser2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.research.githubuser2.databinding.FragmentFollowersBinding
import id.research.githubuser2.models.ItemsItem
import id.research.githubuser2.models.ListFollowersResponseItem
import id.research.githubuser2.ui.Constant
import id.research.githubuser2.ui.activity.DetailUserActivity
import id.research.githubuser2.ui.adapter.FollowersAdapter
import id.research.githubuser2.viewmodels.MainViewModel


class FollowersFragment : Fragment() {

    private lateinit var mFollowersBinding: FragmentFollowersBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var mFollowersAdapter: FollowersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mFollowersBinding = FragmentFollowersBinding.inflate(inflater, container, false)
        return mFollowersBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mFollowersAdapter = FollowersAdapter()
        val users =
            (requireActivity() as DetailUserActivity).intent.getParcelableExtra<ItemsItem>(Constant.USERS) as ItemsItem

        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        users.login?.let { mainViewModel.followersUser(it) }

        mainViewModel.mFollowers.observe(this.requireActivity(), { mFollowers ->
            mFollowersAdapter.setListFollowers(mFollowers as ArrayList<ListFollowersResponseItem>)
            with(mFollowersBinding.rvList) {
                layoutManager = LinearLayoutManager(activity)
                setHasFixedSize(true)
                adapter = mFollowersAdapter
            }
        })

        mainViewModel.isLoading.observe(this.requireActivity(), {
            showLoading(it)
        })

    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            mFollowersBinding.progressBar.visibility = View.VISIBLE
        } else {
            mFollowersBinding.progressBar.visibility = View.GONE
        }
    }

}