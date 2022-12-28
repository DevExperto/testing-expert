package com.devexperto.testingexpert.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.devexperto.testingexpert.R
import com.devexperto.testingexpert.databinding.ActivityMainBinding
import com.devexperto.testingexpert.ui.board.BoardFragment
import com.devexperto.testingexpert.ui.scoreboard.ScoreboardFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            bottomNavigation.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_board -> {
                        supportFragmentManager.commit {
                            replace(R.id.app_container, BoardFragment())
                        }
                        true
                    }
                    R.id.navigation_scoreboard -> {
                        supportFragmentManager.commit {
                            replace(R.id.app_container, ScoreboardFragment())
                        }
                        true
                    }
                    else -> false
                }
            }

            bottomNavigation.selectedItemId = R.id.navigation_board
        }
    }
}