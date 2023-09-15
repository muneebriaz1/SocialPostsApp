package com.appiskey.socialpostsapp.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.appiskey.socialpostsapp.R
import com.appiskey.socialpostsapp.data.model.remote.PostRemote
import com.appiskey.socialpostsapp.utils.getFormattedTagsString
import com.bumptech.glide.Glide

class PostAdapter(
    private val postList: List<PostRemote>,
    private val context: Context
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mOnClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_posts_single_item, parent, false)
        return ViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(position)
    }

    override fun getItemCount(): Int = postList.size
    override fun getItemViewType(position: Int) = position
    override fun getItemId(position: Int) = position.toLong()

    inner class ViewHolder(mView: View): RecyclerView.ViewHolder(mView){
        private val usernameTextView = mView.findViewById<TextView>(R.id.tv_username)
        private val tagsTextView = mView.findViewById<TextView>(R.id.tv_tags)
        private val imageView = mView.findViewById<ImageView>(R.id.imageView)
        private val likesTextView = mView.findViewById<TextView>(R.id.tv_likes)

        fun bind(result: Int){
            val data = postList[result]

            usernameTextView.text = data.user
            tagsTextView.text = getFormattedTagsString(data.tags)
            likesTextView.text = data.likes.toString()


            //For Circular Progress bar for loading
            val circularProgressDrawable = CircularProgressDrawable(context)
            circularProgressDrawable.strokeWidth = 8f
            circularProgressDrawable.centerRadius = 60f
            circularProgressDrawable.setTint(context.getColor(R.color.colorPrimary))
            circularProgressDrawable.start()

            Glide
                .with(context)
                .load(data.largeImageURL)
                .centerCrop()
                .placeholder(circularProgressDrawable)
                .into(imageView)

            imageView.setOnClickListener {
                mOnClickListener?.onImageTouch(data.largeImageURL)
            }
        }
    }

    interface OnClickListener{
        fun onImageTouch(imageURL: String)
    }
}