package id.B21_CAP0304.RecheckApps.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import id.B21_CAP0304.RecheckApps.databinding.ActivityMainBinding
import id.B21_CAP0304.RecheckApps.model.ItemsDetail
import id.B21_CAP0304.RecheckApps.ui.newes.NewesActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNew.setOnClickListener {
            startActivity(Intent(this, NewesActivity::class.java))
        }

        //Dummy
        loadDummy()
    }

    private fun recycleData(data: List<ItemsDetail>){
        binding.rvHistory.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MainAdapter(data, this@MainActivity)
        }
    }

    private fun loadDummy(){
        var data = listOf<ItemsDetail>(
            ItemsDetail("Estimation 0", 2, "02/02/2021"),
            ItemsDetail("Estimation 1", 4, "02/03/2021"),
            ItemsDetail("Estimation 2", 6, "02/04/2021"),
        )

        recycleData(data)

    }
}