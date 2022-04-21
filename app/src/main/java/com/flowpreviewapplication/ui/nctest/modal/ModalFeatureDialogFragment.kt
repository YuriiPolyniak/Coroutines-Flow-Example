package com.flowpreviewapplication.ui.nctest.modal

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.flowpreviewapplication.R
import com.flowpreviewapplication.databinding.DialogModalFeatureBinding

class ModalFeatureDialogFragment : DialogFragment(R.layout.dialog_modal_feature) {

    private var _binding: DialogModalFeatureBinding? = null
    private val binding: DialogModalFeatureBinding
        get() = requireNotNull(_binding)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = DialogModalFeatureBinding.bind(view)

        setupListeners()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setupListeners() {
        binding.repeatButton.setOnClickListener {
//            findNavController().navigate(NavGraphDirections.toBottomDialog())
        }
        binding.actionButton.setOnClickListener {
//            findNavController().navigate(R.id.featureCFragment)
        }
    }
}