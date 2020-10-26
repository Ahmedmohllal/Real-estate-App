package com.example.datakotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class RealStateRecyclerAdapter :
    RecyclerView.Adapter<RealStateRecyclerAdapter.RealStateViewHolder>() {
    var arrayList : ArrayList<RealState> = ArrayList()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RealStateViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return RealStateViewHolder(v)

    }

    override fun onBindViewHolder(holder: RealStateViewHolder, position: Int) {
       // holder.imgView.setImageResource(arrayList.get(position).img_src)
        holder.priceText.setText(""+arrayList.get(position).price)
        holder.type.setText(arrayList.get(position).type+"")

        val url : String = arrayList.get(position).img_src
        Picasso.get().load(url).into(holder.imgView)

    }


    override fun getItemCount(): Int {
        return arrayList.size
    }
    fun setList(list : ArrayList<RealState>){
        arrayList = list
        notifyDataSetChanged()
    }
    open class RealStateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val priceText : TextView = itemView.findViewById(R.id.priceView)
        val type : TextView = itemView.findViewById(R.id.typeView)
        val imgView : ImageView = itemView.findViewById(R.id.img)
    }
}