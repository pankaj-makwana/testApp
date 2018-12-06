package com.example.pankajmakwana.photosearch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_show_images.*


class ShowImagesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_images)
        var searchString = intent.extras.getString("edit_search_content")
        Log.i("Intent data", searchString)
        textViewShowImages.text = searchString
        imagesList.layoutManager = LinearLayoutManager(this)
        fetchJSONData(searchString)
    }

    fun fetchJSONData(search :String) {
        var searchKey=search.replace("\\s+".toRegex(),"+")
        val fetchingUrl = "https://pixabay.com/api/?key=10229438-7afdc8c120b228294b7d19689&q="+searchKey
        val request = JsonObjectRequest(Request.Method.GET, fetchingUrl, null,
                Response.Listener { response ->
                    val responseHit = response.toString()
                    if (response["totalHits"]==0){
                        Toast.makeText(this,"No results can be fetched ",Toast.LENGTH_SHORT).show()
                    }else{
                        Log.i("Response of Url", responseHit)
                        val gson = GsonBuilder().create()
                        val imagefeed = gson.fromJson(responseHit, ImageFeed::class.java)
                        println(imagefeed.hits.get(1))
                        val testVal = imagefeed.hits[0].user

                        Log.i("GSON", imagefeed.toString())
                        runOnUiThread {
                            imagesList.adapter=ImagesAdapter(imagefeed)
                        }

                    }



                },
                Response.ErrorListener { error ->
                    Log.i("Error", "HEre the Error Comes")

                })
        //VolleyService.requestQueue.add(request)
        // VolleyService.requestQueue.start()
        //que.add(request)
        MySingleton.getInstance(this).addToRequestQueue(request)
    }
}


class ImageFeed(val hits: List<Hit>)
class Hit(val largeImageURL: String, val webformatHeight: String, val webformatWidth: String, val likes: Int, val imageWidth: Int,
          val id: Int, val user_id: Int, val views: Int, val comments: Int, val pageUrl: String, val imageHeight: Int,
          val webformatURL: String, val type: String, val previewHeight: Int, val tags: String, val downloads: Int, val user: String,
          val favorites: Int, val imageSize: Long, val previewWidth: Int, val userImageURL: String, val previewURL: String)