package id.B21_CAP0304.RecheckApps.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import id.B21_CAP0304.RecheckApps.R
import id.B21_CAP0304.RecheckApps.databinding.ActivityMainBinding
import id.B21_CAP0304.RecheckApps.model.DetailItems
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

    private fun recycleData(data: List<DetailItems>){
        binding.rvHistory.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MainAdapter(data, this@MainActivity)
        }
    }

    private fun loadDummy(){
        var data = listOf<DetailItems>(
            DetailItems("Estimation 0", 2, "02/02/2021"),
            DetailItems("Estimation 1", 4, "02/03/2021"),
            DetailItems("Estimation 2", 6, "02/04/2021"),
        )

        recycleData(data)

    }
}