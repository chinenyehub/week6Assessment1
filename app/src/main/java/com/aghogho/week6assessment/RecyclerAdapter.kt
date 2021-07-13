package com.aghogho.week6assessment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onovughe.week6assessment.R
import com.aghogho.week6assessment.database.UserEntity
import kotlinx.android.synthetic.main.row.view.*


class RecyclerAdapter(val listener: RowClickListener): RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {
    var items = ArrayList<UserEntity>()
    fun setListData(data: ArrayList<UserEntity>){
        this.items = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return MyViewHolder(inflate, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            listener.onItemClickListener(items[position])
        }
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
       return items.size
    }

    class MyViewHolder(view: View, val listener: RowClickListener): RecyclerView.ViewHolder(view){
      val tvName = view.tvName
        val tvSurname = view.tvSurname
        val deleteuserId = view.deleteuserId

        fun bind(data: UserEntity){
            tvName.text = data.name

            tvSurname.text = data.surname

            deleteuserId.setOnClickListener{
                listener.onDeleteUserClickListener(data)
            }
        }
    }

    interface RowClickListener{
        fun onDeleteUserClickListener(user: UserEntity)
        fun onItemClickListener(user: UserEntity)
    }
}