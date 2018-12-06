package com.example.pankajmakwana.photosearch

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_component_list.view.*

class ImagesAdapter(val imagefeed: ImageFeed) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return imagefeed.hits.count()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        val row = layoutInflater.inflate(R.layout.image_component_list, p0, false)
        return ViewHolder(row)
    }


    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val hits = imagefeed.hits[p1]
        p0.userName.text = hits.user
        if (hits.largeImageURL != "") {
            Picasso.get().load(hits.largeImageURL).into(p0.imageSearch)
        }
        if (hits.userImageURL != "") {
            Picasso.get().load(hits.userImageURL).into(p0.imageUser)
        }
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var imageSearch = view.imageViewShow
    var imageUser = view.userImageView
    var userName = view.textViewUser

}