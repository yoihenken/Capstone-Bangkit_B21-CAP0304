package id.B21_CAP0304.RecheckApps.ui.detail

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.B21_CAP0304.RecheckApps.R
import id.B21_CAP0304.RecheckApps.databinding.ItemsDetailBinding
import id.B21_CAP0304.RecheckApps.model.ItemsResult
import id.B21_CAP0304.RecheckApps.toRupiah

class DetailAdapter(private val data: List<ItemsResult>, detailActivity: DetailActivity) :
    RecyclerView.Adapter<DetailAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemsDetailBinding.bind(itemView)
        fun bind(itemsResult: ItemsResult) {
            binding.apply {
                tvTitle.text = itemsResult.title
                tvCountItems.text = itemsResult.price?.toLong()?.toRupiah()
                tvDate.text = itemsResult.status

                if(itemsResult.status == "Overprice"){
                    tvDate.setTextColor(Color.RED)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.items_detail, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

}
