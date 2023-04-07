package com.example.lab2.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2.classes.Anime
import com.example.lab2.AnimeRecyclerViewAdapter
import com.example.lab2.R
import com.example.lab2.classes.MyDatabaseHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainFragment : Fragment(), View.OnClickListener{

    private lateinit var recyclerView: RecyclerView
    private lateinit var MyDBHelper: MyDatabaseHelper
    private lateinit var fab:FloatingActionButton
    private lateinit var sharedPreferences: SharedPreferences
    var navController:NavController? = null
    private var animeList = mutableListOf<Anime>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = requireContext()
        sharedPreferences = requireActivity().getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)

        navController = Navigation.findNavController(view)
        MyDBHelper = MyDatabaseHelper(context)
        //region Initialise Anime
        animeList = mutableListOf(
            Anime("進撃の巨人 Attack on Titan",
                "A great story about humans fightning titans and at the same time about everything.",
                "The story of Attack on Titan centers on a civilization inside three circular walls. According to the knowledge propagated locally, it is the last surviving vestige of human civilization. Its inhabitants, known as Eldians, have been led to believe that over one hundred years ago, humanity was driven to the brink of extinction after the emergence of humanoid giants called Titans, who attack and eat humans on sight. The last remnants of humanity retreated behind three concentric walls and enjoyed roughly a century of peace. Within the walls, the thought of venturing outside is strongly frowned upon and discouraged. To combat Titans, the country's military employs Vertical Maneuvering Equipment (VME), also called Omni-Directional Maneuvering Gear (ODM Gear): a set of waist-mounted grappling hooks and gas-powered propulsion enabling immense mobility in three dimensions. Swords made of ultrahard steel are used in conjunction with the gear, and eventually rocket launcher-like weapons called Thunder Spears are also developed.",
                "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/51K4w8DY5cL._AC_.jpg"),
            Anime("モンスター　Monster",
                "A story about what is right and what is wrong, what is Monster and what isn't.",
                "Dr. Kenzo Tenma is a young Japanese brain surgeon, working at Eisler Memorial Hospital in Düsseldorf, West Germany. Tenma is dissatisfied with the political bias of the hospital in treating patients, and seizes the chance to change things after a massacre brings fraternal twins Johan and Anna Liebert into the hospital. Johan has a gunshot wound to his head, and Anna mutters about killing; Tenma operates on Johan instead of the mayor, who arrived later. Johan is saved, but Mayor Roedecker dies; Tenma loses his social standing. Director Heinemann and the other doctors in Tenma's way are mysteriously murdered, and both children disappear from the hospital. The police suspect Tenma, but they have no evidence and can only question him.",
                "https://m.media-amazon.com/images/M/MV5BM2ZkYTAwMGMtOGEwOS00MzBjLTgxOGYtZTYwY2E1ZjMyZmY4XkEyXkFqcGdeQXVyNTgyNTA4MjM@._V1_.jpg"),
            Anime("ジョジョの奇妙な冒険　JoJo's Bizarre Adventure",
                "Humorous, uniquely odd anime about muscular men with unusual abilities.",
                "The universe of JoJo's Bizarre Adventure is a reflection of the real world with the added existence of supernatural forces and beings. In this setting, some people are capable of transforming their inner spiritual power into a Stand (スタンド, Sutando); another significant form of energy is Hamon (波紋, \"Ripple\"), a martial arts technique that allows its user to focus bodily energy into sunlight via controlled breathing. The narrative of JoJo's Bizarre Adventure is split into parts with independent stories and different characters. Each of the series' protagonists is a member of the Joestar family, whose mainline descendants possess a star-shaped birthmark above their left shoulder blade and a name that can be abbreviated to the titular \"JoJo\". The first six parts take place within a single continuity whose generational conflict stems from the rivalry between Jonathan Joestar and Dio Brando, while the latter three parts take place in an alternate universe where the Joestar family tree is heavily altered.",
                "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/81IF6cvmg8L._AC_UF894,1000_QL80_.jpg"),
            Anime("デス・ノート Death Note",
                "A dangerous Death Note suddenly appears at the disposal of the best japanese student Light Yagami...",
                "In Tokyo, a disaffected high school student named Light Yagami finds the \"Death Note\", a mysterious black notebook that can kill anyone as long as the user knows both the target's name and face. Initially terrified of its god-like power, Light considers the possibilities of the Death Note's abilities and kills high-profile Japanese criminals, then targets international criminals. Five days after discovering the notebook, Light is visited by Ryuk, a \"shinigami\" and the Death Note's previous owner. Ryuk, invisible to anyone who has not touched the notebook, reveals that he dropped the notebook into the human world out of boredom and is amused by Light's actions.\n" +
                        "As criminals around the world die from inexplicable accidents and heart attacks, the global media suggest that a single mastermind is responsible for the mysterious murders and name them \"Kira\" (キラ, the Japanese transliteration of the word \"killer\"). Hoping to apprehend Kira, Interpol requests the assistance of an enigmatic consulting detective, known as L, to assist their investigation..\n",
                "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/61k3qe5zitL.jpg"),
            Anime("カウボーイ・ビバップ　Cowboy Bebop",
                "Space bounty hunters explore deep space while discovering themselves.",
                "In 2071, roughly fifty years after an accident with a hyperspace gateway which made Earth almost uninhabitable, humanity has colonized most of the rocky planets and moons of the Solar System. Amid a rising crime rate, the Inter Solar System Police (ISSP) set up a legalized contract system, in which registered bounty hunters (also referred to as \"Cowboys\") chase criminals and bring them in alive in return for a reward. The series' protagonists are bounty-hunters working from the spaceship Bebop. The original crew are Spike Spiegel, an exiled former hitman of the criminal Red Dragon Syndicate, and Jet Black, a former ISSP officer. They are later joined by Faye Valentine, an amnesiac con artist; Edward, an eccentric child, skilled in hacking; and Ein, a genetically-engineered Pembroke Welsh Corgi with human-like intelligence. Over the course of the series, the team get involved in disastrous mishaps leaving them without money, while often confronting faces and events from their past: these include Jet's reasons for leaving the ISSP, and Faye's past as a young woman from Earth injured in an accident and cryogenically frozen to save her life.",
                "https://image.tmdb.org/t/p/original/g05iRzPzWwY2liQ60bvW51lLZHQ.jpg")
        )
        if (!checkIfAnimesAdded()){
            addAnimesAndNotify()
        }
        //endregion
        fab = view.findViewById(R.id.btnInfo)
        recyclerView = view.findViewById(R.id.recyclerView)

        fab.setOnClickListener(this)
        val drawable = fab.drawable
        DrawableCompat.setTint(drawable, ContextCompat.getColor(context, R.color.white))
        fab.setImageDrawable(drawable)
        /* Configuring the recyclerView */
        val layoutManager = LinearLayoutManager(context)
        val myAdapter = AnimeRecyclerViewAdapter(MyDBHelper.getAllAnimes()) { selectedAnime: Anime ->                        //assigning our adapter as an adapter for rv
            listItemClicked(selectedAnime)
        }
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = myAdapter
    }

    private fun listItemClicked(selectedAnime: Anime) {
        val bundle = Bundle().apply {
            putInt("Current Anime Index", MyDBHelper.getAllAnimes().indexOf(selectedAnime))
        }
        //DetailsFragment.arguments = bundle

        navController!!.navigate(R.id.action_mainFragment_to_detailsFragment, bundle)
       // Toast.makeText(context,"Hello from MainFragment! ${selectedAnime.name} clicked!", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnInfo ->navController!!.navigate(R.id.action_mainFragment_to_aboutFragment)
        }
    }
    //region initialising animelist
    fun checkIfAnimesAdded (): Boolean{
        val animesAdded = sharedPreferences.getBoolean("animesAdded",false)
        return animesAdded
    }
    fun addAnimesAndNotify(){
        for (anime in animeList){
            MyDBHelper.addAnime(anime)
        }
        val editor = sharedPreferences.edit()
        editor.putBoolean("animesAdded", true)
        editor.apply()
    }
    //endregion initialising animelist
}