package com.example.pankajmakwana.photosearch

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editSearchText= editTextSearch.text

        fun buttonClickOperation(){
            val searchText =editSearchText.toString()
            Log.i("Search Text", searchText)
            val intent = Intent(this@MainActivity,ShowImagesActivity::class.java)
            intent.putExtra("edit_search_content",searchText)
            startActivity(intent)

        }

        val clickListener = View.OnClickListener { view ->
            when (view.id) {
                R.id.buttonSearchImages -> buttonClickOperation()

            }

        }
        buttonSearchImages.setOnClickListener(clickListener)

    }
}

