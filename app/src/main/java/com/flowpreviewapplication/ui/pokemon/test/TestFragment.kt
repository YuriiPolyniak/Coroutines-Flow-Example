package com.flowpreviewapplication.ui.pokemon.test

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.flowpreviewapplication.R
import com.flowpreviewapplication.databinding.FragmentTestBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TestFragment : Fragment(R.layout.fragment_test) {

    private val viewModel: TestViewModel by sharedViewModel()

    private var _binding: FragmentTestBinding? = null
    val binding: FragmentTestBinding
        get() = requireNotNull(_binding)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTestBinding.bind(view)

        binding.testLabel.text = "${System.currentTimeMillis()}"
        viewModel.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}