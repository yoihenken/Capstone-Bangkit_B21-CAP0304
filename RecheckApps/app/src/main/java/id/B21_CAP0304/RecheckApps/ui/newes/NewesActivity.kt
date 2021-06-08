package id.B21_CAP0304.RecheckApps.ui.newes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.B21_CAP0304.RecheckApps.R
import id.B21_CAP0304.RecheckApps.databinding.ActivityNewesBinding
import id.B21_CAP0304.RecheckApps.model.GetItemResponse
import id.B21_CAP0304.RecheckApps.model.ItemDataResponse
import id.B21_CAP0304.RecheckApps.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_newes.*

class NewesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewesBinding
    private val model: NewesViewModel by viewModels { NewesViewModelFactory(application) }
    var isLoaded = false
    lateinit var adapter: NewesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.appBar.setNavigationOnClickListener {
            finish()
        }

        model.itemData.observe(this) {
            if (!it.isNullOrEmpty()) {
                initRecyclerview(it)
                isLoaded = true
            }

        }

        model.onSave().observe(this) {
            startActivity(Intent(this@NewesActivity, DetailActivity::class.java).putExtra(DetailActivity.ITEM_DETAIL, it))
            finish()
        }

        binding.apply {

            btnAdd.setOnClickListener {
                adapter.addItem()

            }
            btnDone.setOnClickListener {
                val dataItems = adapter.getitem()
                var isValid = true
                for (item in dataItems) {
                    if (item.itemName == "" || item.price == 0.toBigInteger() || item.brand == "") {
                        isValid = false
                    }
                }
                isValid = if (dataItems.isEmpty()) false else isValid
                isValid = if (inputNameEstimation.text.isNullOrBlank()) false else isValid
                if (isValid) {
                    model.predict(
                        inputNameEstimation.text.toString(),
                        GetItemResponse(adapter.getitem()),
                        this@NewesActivity
                    )

                } else {
                    Log.d("TAG", "onCreate: $dataItems")
                    Toast.makeText(
                        this@NewesActivity,
                        resources.getString(R.string.fill_the_items),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    private fun initRecyclerview(itemData: List<ItemDataResponse>) {
        adapter = NewesAdapter(itemData)
        binding.rvAddItems.adapter = adapter
        binding.rvAddItems.layoutManager = LinearLayoutManager(this@NewesActivity)

    }

}