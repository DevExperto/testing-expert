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
import kotlinx.coroutines.launch

class BoardFragment : Fragment(R.layout.fragment_board) {

    private val viewModel: BoardViewModel by viewModels {
        val db = (requireActivity().application as App).db
        val gameRepository = GameRepository(RoomGameDataSource(db.gameDao))
        BoardViewModelFactory(gameRepository)
    }
    private lateinit var binding: FragmentBoardBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBoardBinding.bind(view)
        binding.boardView.onClick = viewModel::move

        viewModel.startGame()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    binding.boardView.update(state)
                }
            }
        }
    }

}