package id.B21_CAP0304.RecheckApps.ui.newes


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import id.B21_CAP0304.RecheckApps.databinding.ItemsAddBinding
import id.B21_CAP0304.RecheckApps.model.ItemDataResponse
import kotlinx.android.synthetic.main.items_add.view.*

class NewesAdapter(
    private val listItem: List<ItemDataResponse>,
) : RecyclerView.Adapter<NewesAdapter.ViewHolder>() {
    companion object TYPE {
        const val DELETE = "delete"
        const val UPDATE = "update"
    }

    private lateinit var binding: ItemsAddBinding

    private var listData = mutableListOf<ItemDataResponse>()

    fun addItem() {
        listData.add(ItemDataResponse())
        notifyItemInserted(listData.size - 1)
    }

    fun getitem(): List<ItemDataResponse> = listData

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemsAddBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(listItem, listData[position]) { type, itemData ->
            when (type) {
                UPDATE -> {
                    val idx = listData.indexOfFirst { it.id == itemData.id }
                    listData[idx] = itemData
                    //notifyItemChanged(position)
                }
                DELETE -> {
                    listData.remove(itemData)
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun getItemCount(): Int = listData.size

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(
            listItem: List<ItemDataResponse>,
            dataItem: ItemDataResponse,
            callback: (String, ItemDataResponse) -> Unit
        ) {
            view.apply {
                val itemNames = Array(listItem.size) { i ->
                    listItem[i].itemName
                }

                val adapter = ArrayAdapter(
                    view.context,
                    android.R.layout.simple_dropdown_item_1line,
                    itemNames
                )
                spnItemName.setAdapter(
                    adapter
                )

                spnItemName.setOnItemClickListener { _, _, position, _ ->
                    val itemData = listItem[position]
                    if (etPrice.text.isNullOrBlank()) {
                        itemData.price = 0
                    } else {
                        itemData.price = Integer.parseInt(etPrice.text.toString())
                    }
                    dataItem.itemName = itemData.itemName
                    dataItem.brand = itemData.brand
                    dataItem.price = itemData.price
                    callback(TYPE.UPDATE, dataItem)
                }
                spnItemName.setText(dataItem.itemName)
                adapter.filter.filter(null)
                etPrice.doAfterTextChanged {
                    if (!etPrice.text.isNullOrBlank() && dataItem.price != etPrice.text.toString()
                            .toInt()
                    ) {
                        dataItem.price = etPrice.text.toString().toInt()
                        callback(TYPE.UPDATE, dataItem)
                    }
                }


                btn_delete.setOnClickListener {
                    callback(TYPE.DELETE, dataItem)
                }
            }
        }
    }
}