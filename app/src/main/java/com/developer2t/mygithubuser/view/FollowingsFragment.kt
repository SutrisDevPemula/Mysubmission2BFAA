package com.developer2t.mygithubuser.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.developer2t.mygithubuser.R
import com.developer2t.mygithubuser.adapter.UsersDataAdapter
import com.developer2t.mygithubuser.viewmodel.ViewModelListFollow
import kotlinx.android.synthetic.main.activity_main.*


class FollowingsFragment : Fragment() {

    companion object {
        fun newInstance(name: String?) = FollowingsFragment().apply {
            arguments = Bundle().apply {
                putString("name", name)
            }
        }
    }

    private lateinit var adapter: UsersDataAdapter
    private lateinit var viewModelListFollow: ViewModelListFollow
    private val name by lazy { arguments?.getString("name") ?: "" }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_followings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        showRecylerViewList()

        viewModelListFollow =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(ViewModelListFollow::class.java)
        viewModelListFollow.setFollowers(ViewModelListFollow.FOLLOWING,name)

        viewModelListFollow.getFollowers().observe(viewLifecycleOwner, Observer { followersList ->
            if (followersList != null) {
                adapter.setData(followersList)
            }
        })
    }

    private fun showRecylerViewList() {
        adapter = UsersDataAdapter()
        adapter.notifyDataSetChanged()

        rv_item.layoutManager = LinearLayoutManager(context)
        rv_item.adapter = adapter
    }

}