package id.B21_CAP0304.RecheckApps.ui.newes

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.B21_CAP0304.RecheckApps.databinding.ItemsAddBinding

class NewesAdapter(
    private val listData: List<Int>,
    private val modelNewes: NewesViewModel
) : RecyclerView.Adapter<NewesAdapter.ViewHolder>() {

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view)

    private lateinit var binding: ItemsAddBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewesAdapter.ViewHolder {
        binding = ItemsAddBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: NewesAdapter.ViewHolder, position: Int) {
        binding.apply {

//            ddItems.editText = ""
//            tfPrice.editText = ""

            btnDelete.setOnClickListener {
                Log.d("NewesAct", "remove Items: ${position}")
                modelNewes.removeCountItems(position)
            }
        }
    }

    override fun getItemCount(): Int = listData.size
}