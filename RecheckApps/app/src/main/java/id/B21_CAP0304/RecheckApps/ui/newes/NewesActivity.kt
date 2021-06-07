package id.B21_CAP0304.RecheckApps.ui.newes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import id.B21_CAP0304.RecheckApps.databinding.ActivityNewesBinding
import androidx.activity.viewModels
import id.B21_CAP0304.RecheckApps.model.ItemDataResponse
import kotlinx.android.synthetic.main.activity_newes.*

class NewesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewesBinding
    private val model: NewesViewModel by viewModels()
    var isLoaded = false
    lateinit var adapter: NewesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.appBar.setNavigationOnClickListener {
            finish()
        }

        model.itemDataRequest.observe(this) {
            if(isLoaded){
                updateRecyclerViewData(it)
            }
        }

        model.itemData.observe(this) {
            if(!it.isNullOrEmpty()){
                initRecyclerview(it)
                isLoaded = true
            }

        }

        binding.apply {

            btnAdd.setOnClickListener {
                adapter.addItem()

            }
            btnDone.setOnClickListener {
                Log.d("TAG", "onCreate: ${adapter.getitem()}")
            }
        }

    }

    private fun updateRecyclerViewData(itemData: MutableList<ItemDataResponse>) {
        Log.d("TAG", itemData.toString())
        //adapter.updateData(itemData.toList())
    }

    fun initRecyclerview(itemData: List<ItemDataResponse>) {
        adapter = NewesAdapter(itemData)
        binding.rvAddItems.adapter = adapter
        binding.rvAddItems.layoutManager = LinearLayoutManager(this@NewesActivity)

    }

}