package com.example.pruebapractica

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebapractica.ui.theme.PruebaPracticaTheme

class MainActivity : ComponentActivity(), MyAdapter.OnListInteractionListener {

    private var recyclerView: RecyclerView? = null
    private var mAdapter: MyAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var baseUri = "https://www.cheapshark.com/redirect?dealID="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_list_)

        recyclerView = findViewById(R.id.list)
        recyclerView!!.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager
        mAdapter = MyAdapter(emptyList(), this)

        AppExecutors.instance?.networkIO()?.execute {
            ReposNetworkLoaderRunnable(object : OnReposLoadedListener {
                override fun onReposLoaded(deals: List<OfferModel>) {
                    runOnUiThread {
                        mAdapter!!.swap(deals)
                    }
                }
            }).run()
        }

        recyclerView!!.adapter = mAdapter
    }


    override fun onListInteraction(url: String?) {
        val webpage = Uri.parse(baseUri + url)
        val webIntent = Intent(Intent.ACTION_VIEW, webpage)
        startActivity(webIntent)
    }
}