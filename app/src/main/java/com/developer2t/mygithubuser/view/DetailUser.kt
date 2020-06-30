package com.developer2t.mygithubuser.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.developer2t.mygithubuser.R
import com.developer2t.mygithubuser.adapter.SectionPagerAdapter
import com.developer2t.mygithubuser.model.UserModel
import com.developer2t.mygithubuser.viewmodel.ViewModelDetail
import kotlinx.android.synthetic.main.activity_detail_user.*
import kotlinx.android.synthetic.main.detail_user.*

class DetailUser : AppCompatActivity() {


    companion object {
        const val EXTRA_USER = "extra_USER"
    }


    private lateinit var viewModelDetail: ViewModelDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        val usernameQ = intent.getStringExtra(EXTRA_USER)

        supportActionBar?.title = "Detail User"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        viewModelDetail = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(ViewModelDetail::class.java)
        showLoading(true)

        viewModelDetail.setDetailUser(usernameQ.toString())
        viewModelDetail.getDetailUser().observe(this, Observer { detailItem ->
            if (detailItem != null) {
                setDetailUser(detailItem)
                showLoading(false)
            }
        })
    }

    private fun setDetailUser(users: UserModel) {
        Glide.with(this)
            .load(users.images)
            .apply(RequestOptions()).override(120, 120)
            .into(user_images)
        tv_username.text = users.username
        tv_name.text = users.name
        tv_location.text = users.location
        tv_company.text = users.company
        tv_repositori.text = users.repo.toString()
        tv_followers.text = users.follower.toString()
        tv_followings.text = users.followings.toString()

        val sectionPagerAdapter = SectionPagerAdapter(
            this, supportFragmentManager, users.username
        )
        view_pager.adapter = sectionPagerAdapter
        tabs_layout.setupWithViewPager(view_pager)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            loading.visibility = View.VISIBLE
        } else {
            loading.visibility = View.GONE
        }
    }
}