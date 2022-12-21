package com.devexperto.testingexpert.ui.board

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.devexperto.testingexpert.databinding.ViewBoardBinding

class BoardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var onClick: (row: Int, column: Int) -> Unit = { _, _ -> }

    private val binding: ViewBoardBinding = ViewBoardBinding.inflate(LayoutInflater.from(context), this)

    init {
        orientation = VERTICAL

        binding.btn00.setOnClickListener { onClick(0, 0) }
        binding.btn01.setOnClickListener { onClick(0, 1) }
        binding.btn02.setOnClickListener { onClick(0, 2) }
        binding.btn10.setOnClickListener { onClick(1, 0) }
        binding.btn11.setOnClickListener { onClick(1, 1) }
        binding.btn12.setOnClickListener { onClick(1, 2) }
        binding.btn20.setOnClickListener { onClick(2, 0) }
        binding.btn21.setOnClickListener { onClick(2, 1) }
        binding.btn22.setOnClickListener { onClick(2, 2) }

        // Temp: random values
        binding.btn00.text = "0"
        binding.btn01.text = "X"
        binding.btn20.text = "X"
        binding.btn22.text = "0"
    }
}