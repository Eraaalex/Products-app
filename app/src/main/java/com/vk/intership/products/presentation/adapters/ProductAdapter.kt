package com.vk.intership.products.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vk.intership.products.R
import com.vk.intership.products.domain.model.Product

class ProductAdapter(private var productList: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageViewProduct)
        val nameTextView: TextView = view.findViewById(R.id.textViewProductName)
        val thumbnailTextView: TextView = view.findViewById(R.id.textViewProductThumbnail)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.nameTextView.text = product.title
        holder.thumbnailTextView.text = product.thumbnail
        Glide.with(holder.imageView.context).load(product.images[0]).into(holder.imageView)
    }

    override fun getItemCount(): Int = productList.size

    fun updateData(newProducts: List<Product>) {
        productList = newProducts
        notifyDataSetChanged()
    }

}
