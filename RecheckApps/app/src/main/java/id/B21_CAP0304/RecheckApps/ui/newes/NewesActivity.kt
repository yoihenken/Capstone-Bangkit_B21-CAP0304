package id.B21_CAP0304.RecheckApps.ui.newes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import id.B21_CAP0304.RecheckApps.databinding.ActivityNewesBinding
import androidx.activity.viewModels
import kotlinx.android.synthetic.main.activity_newes.*

class NewesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewesBinding
    private val model : NewesViewModel by viewModels()
    private var curTotalItems = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.appBar.setNavigationOnClickListener {
            finish()
        }

        model.countItems.observe(this){
            curTotalItems = it.size-1
            recycleData(it)
        }

        binding.btnAdd.setOnClickListener {
            model.addCountItems(curTotalItems)
            Log.d("NewesAct", "Add Items: ${curTotalItems}")
        }

    }

    private fun recycleData(data: List<Int>){
        binding.rvAddItems.apply {
            layoutManager = LinearLayoutManager(this@NewesActivity)
            adapter = NewesAdapter(data, model)
        }
    }

    private fun loadDummy(){
        var data = arrayListOf<Int>(0,1,2)
        recycleData(data)
    }

}