package com.info.ibm.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.info.ibm.databinding.ItemHomeAdapterBinding
import com.info.ibm.model.VehiclesResponseItem

class HomeAdapter : RecyclerView.Adapter<MainViewHolder>() {
    var vehiclesResponseItems = mutableListOf<VehiclesResponseItem>()

    fun setVehiclesResponseItemList(VehiclesResponseItems: List<VehiclesResponseItem>) {
        this.vehiclesResponseItems = VehiclesResponseItems.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeAdapterBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val vehiclesResponseItem = vehiclesResponseItems[position]
        holder.binding.tvMakeModel.text = vehiclesResponseItem.makeAndModel
        holder.binding.tvVin.text=vehiclesResponseItem.vin
    }

    override fun getItemCount(): Int {
        return vehiclesResponseItems.size
    }
}

class MainViewHolder(val binding: ItemHomeAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
}