package com.example.lab2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide;
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2.classes.Anime

class AnimeRecyclerViewAdapter(
    private val animeList:List<Anime>,
    private val clickListener:(Anime) ->Unit
):RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item,parent,false)
        return MyViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val anime = animeList[position]
        holder.bind(anime, clickListener)
    }

    override fun getItemCount(): Int {
        return animeList.size
    }
}

class MyViewHolder(private val view: View):RecyclerView.ViewHolder(view){

    fun bind(anime: Anime, clickListener:(Anime) ->Unit){
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvDescription = view.findViewById<TextView>(R.id.tvDescription)
        val ivPoster = view.findViewById<ImageView>(R.id.ivPoster)

        tvTitle.text = anime.name
        tvDescription.text = anime.description
        Glide.with(view.context)
            .load(anime.posterUrl)
            .into(ivPoster)

        view.setOnClickListener{
            clickListener(anime)

        }
    }
}