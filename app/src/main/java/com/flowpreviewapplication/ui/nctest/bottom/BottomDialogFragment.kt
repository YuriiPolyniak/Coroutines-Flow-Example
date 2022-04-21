package com.flowpreviewapplication.ui.nctest.bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.flowpreviewapplication.R
import com.flowpreviewapplication.databinding.DialogBottomFeatureBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomDialogFragment : BottomSheetDialogFragment() {

    private var _binding: DialogBottomFeatureBinding? = null
    private val binding: DialogBottomFeatureBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogBottomFeatureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setupListeners() {
        binding.repeatButton.setOnClickListener {
//            findNavController().navigate(NavGraphDirections.toModalFeatureDialog())
        }
        binding.actionButton.setOnClickListener {
//            findNavController().navigate(R.id.featureCFragment)
        }
    }
}