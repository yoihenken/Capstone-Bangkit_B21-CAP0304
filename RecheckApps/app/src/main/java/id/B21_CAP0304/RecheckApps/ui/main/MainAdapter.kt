package id.B21_CAP0304.RecheckApps.ui.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.B21_CAP0304.RecheckApps.R
import id.B21_CAP0304.RecheckApps.databinding.ItemsDetailBinding
import id.B21_CAP0304.RecheckApps.model.ItemsDetail
import id.B21_CAP0304.RecheckApps.ui.detail.DetailActivity

class MainAdapter(
    private val listData : List<ItemsDetail>,
    private val context : Context
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    class MainViewHolder (view: View) : RecyclerView.ViewHolder(view)

    private lateinit var binding: ItemsDetailBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.items_detail, parent, false)
        binding = ItemsDetailBinding.bind(layout)
        return MainViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val data = listData[position]
        binding.apply {
            tvTitle.text = data.title
            tvCountItems.text = data.count.toString()
            tvDate.text = data.date

            itemsDetail.setOnClickListener {
                context.startActivity(Intent(context, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_DATA, data)
                })
            }
         }
    }

    override fun getItemCount(): Int = listData.size
}