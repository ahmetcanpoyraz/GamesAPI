package com.example.gamesapi.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamesapi.R

import com.example.gamesapi.adapter.GamesAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var viewModel : HomeViewModel
    private val gamesAdapter = GamesAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        viewModel.refreshData()

        gameList.layoutManager = LinearLayoutManager(context)
        gameList.adapter = gamesAdapter

        swipeRefreshLayout.setOnRefreshListener {
            gameList.visibility = View.GONE
            gameError.visibility = View.GONE
            gameLoading.visibility = View.VISIBLE

            viewModel.refreshFromAPI()
            swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()

    }

    fun observeLiveData(){
        viewModel.games.observe(this, Observer { games ->
            games?.let {
                gameList.visibility = View.VISIBLE
                gamesAdapter.updateGameList(games)
            }
        })

        viewModel.gameError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if(it){
                    gameError.visibility = View.VISIBLE
                }else{
                    gameError.visibility = View.GONE
                }
            }
        })

        viewModel.gameLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if(it){
                    gameLoading.visibility = View.VISIBLE
                    gameList.visibility = View.GONE
                    gameError.visibility = View.GONE
                }else{
                    gameLoading.visibility = View.GONE
                }
            }
        })
    }

}