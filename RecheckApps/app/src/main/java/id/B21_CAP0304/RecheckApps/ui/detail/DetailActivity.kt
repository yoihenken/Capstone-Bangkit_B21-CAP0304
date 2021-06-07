package id.B21_CAP0304.RecheckApps.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import id.B21_CAP0304.RecheckApps.databinding.ActivityDetailBinding
import id.B21_CAP0304.RecheckApps.model.ItemsDetail
import id.B21_CAP0304.RecheckApps.model.ItemsResult
import id.B21_CAP0304.RecheckApps.ui.main.MainAdapter

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var dataItemsDetail : ItemsDetail
    private val model : DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get data intent
        dataItemsDetail = intent.getParcelableExtra<ItemsDetail>(EXTRA_DATA) ?: ItemsDetail()

        binding.apply {
            appBar.apply {
                title = dataItemsDetail.title
                setNavigationOnClickListener { finish() }
            }

            dataItemsDetail.id?.let { model.getSavedResult(application, this@DetailActivity, it) }
            model.savedResult.observe(this@DetailActivity){
                recycleData(it)
            }
        }
    }

    private fun recycleData(data: List<ItemsResult>){
        binding.rvDetailItem.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity)
            // adapter = DetailAdapter(data, this@DetailActivity)
        }
    }
    
    companion object{
        var EXTRA_DATA = "extra_data"
    }
}