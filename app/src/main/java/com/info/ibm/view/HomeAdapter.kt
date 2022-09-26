package com.info.ibm.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.info.ibm.databinding.ItemHomeAdapterBinding
import com.info.ibm.model.VehiclesResponseItem
import java.util.*

class HomeAdapter(
    private val context: Context,
    private val onItemClickListner: OnItemClickListner
) : RecyclerView.Adapter<MainViewHolder>() {
    var vehiclesResponseItems = mutableListOf<VehiclesResponseItem>()

    fun setVehiclesResponseItemList(vehiclesResponseItems: List<VehiclesResponseItem>) {
        this.vehiclesResponseItems = vehiclesResponseItems.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeAdapterBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.binding.tvMakeModel.text = vehiclesResponseItems[position].makeAndModel

        holder.binding.tvVin.text = vehiclesResponseItems[position].vin
        holder.binding.rlParent.setOnClickListener {
            onItemClickListner.onClick(
                (vehiclesResponseItems[position].vin),
                (vehiclesResponseItems[position].makeAndModel),
                (vehiclesResponseItems[position].color),
                (vehiclesResponseItems[position].carType)
            )
        }

    }

    override fun getItemCount(): Int {
        return vehiclesResponseItems.size
    }

    interface OnItemClickListner {
        fun onClick(str: String, strMake: String,strColor:String,strCarType:String)
    }
}

class MainViewHolder(val binding: ItemHomeAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
}