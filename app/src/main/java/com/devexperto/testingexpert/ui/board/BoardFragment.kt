package com.devexperto.testingexpert.ui.board

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.devexperto.testingexpert.App
import com.devexperto.testingexpert.R
import com.devexperto.testingexpert.data.GameRepository
import com.devexperto.testingexpert.data.RoomGameDataSource
import com.devexperto.testingexpert.databinding.FragmentBoardBinding
import com.devexperto.testingexpert.domain.GameState
import com.devexperto.testingexpert.domain.Winner
import kotlinx.coroutines.launch

class BoardFragment : Fragment(R.layout.fragment_board) {

    private val viewModel: BoardViewModel by viewModels {
        val db = (requireActivity().application as App).db
        val gameRepository = GameRepository(RoomGameDataSource(db.gameDao))
        BoardViewModelFactory(gameRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentBoardBinding.bind(view).init()
    }

    private fun FragmentBoardBinding.init() {
        boardView.onClick = viewModel::move

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state.gameState) {
                        GameState.NotStarted -> bindNotStarted()
                        GameState.InProgress -> bindInProgress()
                        is GameState.Finished -> bindFinished(state.gameState.winner)
                    }
                    boardView.update(state)
                }
            }
        }
    }

    private fun FragmentBoardBinding.bindNotStarted() {
        boardView.visibility = View.GONE

        message.text = getString(R.string.welcome)
        message.visibility = View.VISIBLE

        startBtn.text = getString(R.string.start_game)
        startBtn.visibility = View.VISIBLE
        startBtn.setOnClickListener { viewModel.startGame() }

    }

    private fun FragmentBoardBinding.bindInProgress() {
        boardView.visibility = View.VISIBLE
        message.visibility = View.GONE
        startBtn.visibility = View.GONE
    }

    private fun FragmentBoardBinding.bindFinished(winner: Winner) {
        boardView.visibility = View.GONE

        message.text = getString(R.string.winner, winner.toString())
        message.visibility = View.VISIBLE

        startBtn.text = getString(R.string.play_again)
        startBtn.visibility = View.VISIBLE
        startBtn.setOnClickListener { viewModel.resetGame() }
    }

}