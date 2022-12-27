package com.devexperto.testingexpert.ui.scoreboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.devexperto.testingexpert.R
import com.devexperto.testingexpert.databinding.FragmentScoreboardBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ScoreboardFragment : Fragment(R.layout.fragment_scoreboard) {

    private val vm: ScoreboardViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FragmentScoreboardBinding.bind(view).apply {
            val adapter = ScoreboardAdapter()
            score.adapter = adapter
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    vm.scores.collect(adapter::submitList)
                }
            }
        }

    }
}