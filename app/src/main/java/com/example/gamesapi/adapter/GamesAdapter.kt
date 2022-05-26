package com.example.gamesapi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.navArgument
import androidx.recyclerview.widget.RecyclerView
import com.example.gamesapi.R
import com.example.gamesapi.model.GamesModel
import com.example.gamesapi.model.Results
import com.example.gamesapi.ui.home.HomeFragment
import com.example.gamesapi.ui.home.HomeFragmentDirections
import com.example.gamesapi.util.downloadFromUrl
import com.example.gamesapi.util.placeHolderProgressBar
import kotlinx.android.synthetic.main.item_game.view.*

class GamesAdapter (val gameList: ArrayList<Results>): RecyclerView.Adapter<GamesAdapter.GameViewHolder>() {

    class GameViewHolder(var view:View): RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_game,parent,false)
        return GameViewHolder(view)
    }

    override fun getItemCount(): Int {
       return gameList.size
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.view.gameName.text = gameList[position].name
        holder.view.gameRating.text = gameList[position].rating.toString()
        holder.view.gameReleased.text = gameList[position].released

        holder.view.setOnClickListener{
          //  val action = Navigation.
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }

        holder.view.imageView.downloadFromUrl(gameList[position].backgroundImage,
            placeHolderProgressBar(holder.view.context))

    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateGameList(newGameList: List<Results>){
        gameList.clear()
        gameList.addAll(newGameList)
        notifyDataSetChanged()
    }


}