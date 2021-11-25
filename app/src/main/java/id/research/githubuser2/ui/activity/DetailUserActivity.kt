package id.research.githubuser2.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayoutMediator
import id.research.githubuser2.R
import id.research.githubuser2.databinding.ActivityDetailUserBinding
import id.research.githubuser2.models.ItemsItem
import id.research.githubuser2.ui.Constant
import id.research.githubuser2.ui.adapter.DetailPagerAdapter
import id.research.githubuser2.viewmodels.MainViewModel

class DetailUserActivity : AppCompatActivity() {

    private lateinit var mDetailBinding: ActivityDetailUserBinding
    private lateinit var mainViewModel : MainViewModel

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.tab_text_1, R.string.tab_text_2)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDetailBinding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(mDetailBinding.root)

        val mDetailPagerAdapter = DetailPagerAdapter(this)
        mDetailBinding.viewPager.adapter = mDetailPagerAdapter

        mDetailBinding.btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val users = intent.getParcelableExtra<ItemsItem>(Constant.USERS) as ItemsItem

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        users.login?.let { mainViewModel.detailUser(users.login) }


        mainViewModel.mDetailUser.observe(this, {
            mDetailBinding.apply {
                Glide.with(this@DetailUserActivity)
                    .load(it.avatarUrl)
                    .apply(RequestOptions().override(100, 100))
                    .into(imgUser)

                tvName.text = it.name
                tvValueFollowers.text = it.followers.toString()
                tvValueFollowing.text = it.following.toString()
                tvFirstUsername.text = it.login
                tvSecondUsername.text = it.login
                tvLocation.text = it.location
                tvEmail.text = it.email
            }
        })

        TabLayoutMediator(mDetailBinding.tabLayout, mDetailBinding.viewPager) { tab, position ->
            tab.text = resources.getString(
                TAB_TITLES[position]
            )
        }.attach()

        supportActionBar?.elevation = 0f
    }
}