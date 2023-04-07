package com.example.lab2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.lab2.R
import com.example.lab2.classes.Anime
import com.example.lab2.classes.MyDatabaseHelper


class DetailsFragment : Fragment(), OnClickListener {
   // private val inputRecipient = view?.findViewById<TextInputEditText>(R.id.input_recipient)
    var navController: NavController? = null
    private lateinit var MyDBHelper: MyDatabaseHelper
    private lateinit var tvName: TextView
    private lateinit var tvDetailedDescription: TextView
    private lateinit var ivPoster: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        MyDBHelper = MyDatabaseHelper(requireContext())
        tvName = view.findViewById(R.id.tvName)
        tvDetailedDescription = view.findViewById(R.id.tvDetailedDescription)
        ivPoster = view.findViewById(R.id.ivPoster2)
        val currentAnimeIndex= arguments?.getInt("Current Anime Index")


        val animes = MyDBHelper.getAllAnimes()
        var currentAnime: Anime
        for (anime in animes){
            if (animes.indexOf(anime) == currentAnimeIndex){
                currentAnime = anime
                initializeInterface(currentAnime)
            }
        }
    }

    private fun initializeInterface(currentAnime: Anime) {
        if (currentAnime != null) {
            tvName.text = currentAnime.name
            tvDetailedDescription.text = currentAnime.detailedDescription
            Glide.with(requireContext())
                .load(currentAnime.posterUrl)
                .into(ivPoster)
        }
    }

    override fun onClick(v: View?) {
          when(v!!.id){
          }
    }
}