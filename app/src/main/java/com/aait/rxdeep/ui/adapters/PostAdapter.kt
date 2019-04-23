package com.aait.rxdeep.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aait.rxdeep.R
import com.aait.rxdeep.models.PostModel
import kotlinx.android.synthetic.main.item_post.view.*
import java.util.*

class PostAdapter : RecyclerView.Adapter<PostAdapter.MyViewHolder>() {

    private var data: List<PostModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_post, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(data[position])

    fun swapData(data: List<PostModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: PostModel) = with(itemView) {
            title_tv.text=item.title
            desc_tv.text=item.body
            setOnClickListener {

            }
        }
    }
}