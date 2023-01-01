package com.devexperto.testingexpert.ui.games

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.devexperto.testingexpert.R
import com.devexperto.testingexpert.databinding.FragmentGamesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GamesFragment : Fragment(R.layout.fragment_games) {

    private val vm by viewModels<GamesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FragmentGamesBinding.bind(view).apply {
            val gamesAdapter = GamesAdapter()
            games.adapter = gamesAdapter

            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    vm.state.collect { state ->
                        gamesAdapter.submitList(state.games)
                        loading.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                    }
                }
            }
        }

        vm.onUiReady()
    }
}