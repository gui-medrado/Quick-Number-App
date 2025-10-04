package com.example.quicknumbers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.core.widget.addTextChangedListener
import com.example.quicknumbers.databinding.FragmentSortConfigBinding

class SortConfig : Fragment() {
    private var _binding: FragmentSortConfigBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSortConfigBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            fragSwitch.setOnCheckedChangeListener { _, isChecked ->
                fragSwitch.trackTintList = getColorStateList(requireContext(), if(isChecked) R.color.content_brand else R.color.content_tertiary)
            }

            fragEditTextAmountNumbers.addTextChangedListener { text ->

            }

            fragEditTextFromNumbers.addTextChangedListener { text ->

            }

            fragEditTextToNumbers.addTextChangedListener { text ->

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}