package id.B21_CAP0304.RecheckApps.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import id.B21_CAP0304.RecheckApps.databinding.ActivityMainBinding
import id.B21_CAP0304.RecheckApps.model.ItemsDetail
import id.B21_CAP0304.RecheckApps.ui.newes.NewesActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.onResultItems().observe(this) {
            recycleData(it)
        }

        viewModel.getItems(application, this)

        binding.btnNew.setOnClickListener {
            startActivity(Intent(this, NewesActivity::class.java))
        }


    }

    private fun recycleData(data: List<ItemsDetail>){
        binding.rvHistory.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MainAdapter(data, this@MainActivity)
        }
    }

}