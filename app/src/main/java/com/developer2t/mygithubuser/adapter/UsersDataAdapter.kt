package com.developer2t.mygithubuser.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.developer2t.mygithubuser.R
import com.developer2t.mygithubuser.model.UserModel
import com.developer2t.mygithubuser.view.DetailUser
import kotlinx.android.synthetic.main.list_item.view.*

class UsersDataAdapter : RecyclerView.Adapter<UsersDataAdapter.ListViewHolder>() {

    private val mData = ArrayList<UserModel>()

    fun setData(items: ArrayList<UserModel>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UsersDataAdapter.ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: UsersDataAdapter.ListViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(users: UserModel) {
            with(itemView) {
                Glide.with(itemView.context).load(users.images)
                    .apply(RequestOptions().override(65, 65)).into(user_images)
                tv_username.text = users.username
                tv_id.text = users.id

                itemView.setOnClickListener {
                    val intent = Intent(context, DetailUser::class.java)
                    intent.putExtra(DetailUser.EXTRA_USER, users.username)
//                    intent.putExtra(FollowersFragment.EXTRA_USER, users.username)
                    startActivity(context, intent, null)
                }
            }
        }
    }

}