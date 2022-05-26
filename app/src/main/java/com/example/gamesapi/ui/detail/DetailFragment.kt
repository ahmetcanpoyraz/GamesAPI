package com.example.gamesapi.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.gamesapi.R
import com.example.gamesapi.util.downloadFromUrl
import com.example.gamesapi.util.placeHolderProgressBar
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.item_game.*

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private var gameId = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            gameId = DetailFragmentArgs.fromBundle(it).age
        }
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.getDataFromRoom(gameId)



        observeLiveData()

    }

    private fun observeLiveData(){
        viewModel.gameLiveData.observe(viewLifecycleOwner, Observer { game->
            game?.let {
                detailName.text = game.name
                detailRelaeseDate.text = game.released
                detailMetacriticRate.text = game.metacritic.toString()
                context?.let {
                    detailImage.downloadFromUrl(game.backgroundImage, placeHolderProgressBar(it))
                }

            }
        })
    }



}