package com.example.quicknumbers

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.quicknumbers.databinding.FragmentSortResultBinding
import kotlinx.coroutines.launch

class SortResult : Fragment() {
    private val viewModel: ViewModelSort by activityViewModels()
    private var _binding: FragmentSortResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("SortResult", "onCreateView")
        _binding = FragmentSortResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("SortResult", "onViewCreated")



        with(binding){

            lifecycleScope.launch{

                viewModel.uiState.collect { uiState ->

                    fragResultGuide.text = getString(R.string.draw_result, uiState.currentDrawNumber.toString())

                    clearLastDrewNumbers()
                    uiState.drawNumbers.forEach{ number -> generateSortedNumberText(number) }
                }
            }
        }
    }

    override fun onDestroy() {
        Log.d("SortResult", "onDestroy")
        super.onDestroy()
        _binding = null
    }

    private fun FragmentSortResultBinding.generateSortedNumberText(number: Int) {
        val tvSortedNumber = TextView(requireContext()).apply {
            id = View.generateViewId()
            text = number.toString()
            setTextAppearance(R.style.TextAppearance_RobotoMono_Overline)
            textSize = 48f
            setTextColor(ContextCompat.getColor(requireContext(), R.color.content_brand))
        }


        root.addView(tvSortedNumber)
        fragResultResults.referencedIds = fragResultResults.referencedIds.plus(tvSortedNumber.id)
    }

    private fun FragmentSortResultBinding.clearLastDrewNumbers() {
        fragResultResults.referencedIds.forEach {
            root.removeView(root.findViewById(it))
        }
    }
}