package com.example.rc3b3week2

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.annotation.NonNull
import com.example.rc3b3week2.databinding.ItemHeaderBinding
import com.example.rc3b3week2.databinding.ItemRecycleMainBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.collections.ArrayList
import com.example.rc3b3week2.MyAdapter.HeaderViewHolder

private lateinit var binding:ItemRecycleMainBinding
private lateinit var headerBinding:ItemHeaderBinding

class MyAdapter(private val dataSet: ArrayList<youTube>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_HEADER = 0
    private val TYPE_ITEM = 1

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            if(viewType == TYPE_HEADER){
                headerBinding = ItemHeaderBinding.inflate(LayoutInflater.from(parent.context),parent, false)
                return HeaderViewHolder(headerBinding)
            }
            else{
                binding = ItemRecycleMainBinding.inflate(LayoutInflater.from(parent.context),parent, false)
                return ViewHolder(binding)
            }

        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            when(holder){
                is HeaderViewHolder -> holder.bind()
                is ViewHolder -> holder.bind(dataSet[position])
            }
                //holder.bind(dataSet[position])

        }
        override fun getItemViewType(position: Int): Int {
            return if (position == 0) TYPE_HEADER else TYPE_ITEM
        }
        override fun getItemCount(): Int {
            return dataSet.size + 1
        }

    inner class ViewHolder(private val binding: ItemRecycleMainBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: youTube) {
            binding.ivThumbnail.setImageResource(data.thumbnailImage)
            binding.ivCreaterImage.setImageResource(data.createrImage)
            binding.tvTime.text = data.time
            binding.tvTitle.text = data.title
            binding.tvSubTitle.text = data.subTitle
            binding.ivMore.setImageResource(data.moreIcon)

            binding.vhLayout.setOnClickListener {
                Snackbar.make(it, "Item $layoutPosition touched!", Snackbar.LENGTH_SHORT).show()
                //setData(layoutPosition)
            }
        }
    }
    class HeaderViewHolder(private val headerBinding: ItemHeaderBinding) : RecyclerView.ViewHolder(headerBinding.root) {
        fun bind() {
        }
    }

    fun removeData(position: Int) {
        dataSet.removeAt(position)
        notifyItemRemoved(position)
    }

    // ??? ?????? ????????? ???????????? ?????? Collections.swap?????? ????????? ????????? ????????? ????????? ???????????? ??????
    fun swapData(fromPos: Int, toPos: Int) {
        Collections.swap(dataSet, fromPos, toPos)
        notifyItemMoved(fromPos, toPos)
    }
    // ????????? ????????? ???????????? ????????? ????????? ???????????? ???
    fun setData(position: Int) {
        dataSet[position] = youTube(com.example.rc3b3week2.R.drawable.thumbnail_image2_ntime, " 17:24 ",com.example.rc3b3week2.R.drawable.creater_image2,
            "???????????? ????????????..... ??????","???????????????????????? 7.3?????????1?????????", com.example.rc3b3week2.R.drawable.ic_more)
        notifyItemChanged(position)
    }

}