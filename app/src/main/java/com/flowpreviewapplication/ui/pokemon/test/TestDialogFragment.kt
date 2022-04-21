package com.flowpreviewapplication.ui.pokemon.test

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.flowpreviewapplication.databinding.DialogTestBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TestDialogFragment : DialogFragment() {

    //    private val viewModel: TestViewModel by viewModel()
    private val viewModel: TestViewModel by sharedViewModel()

    private var _binding: DialogTestBinding? = null
    private val binding: DialogTestBinding
        get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
//        setStyle(STYLE_NO_TITLE, R.style.FullscreenDialogTheme)
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        enableFullScreenMode()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupListeners()
        setupObserver()


    }

    override fun onCancel(dialog: DialogInterface) {
//        viewModel.onCancel()
        super.onCancel(dialog)
    }

    override fun onDismiss(dialog: DialogInterface) {
//        binding.paymentVerificationWebView.destroy()
        super.onDismiss(dialog)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setupUI() {
//        ViewCompat.setElevation(binding.paymentVerificationProgressView.root, 0f)
    }

    private fun enableFullScreenMode() {
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog?.window?.setLayout(width, height)
    }

    private fun setupListeners() {
//        binding.paymentVerificationClose.setOnClickListener {
//            viewModel.onCancel()
//            dismiss()
//        }
    }

    private fun setupObserver() {
//        viewModel.getPaymentVerificationEvents().observe(viewLifecycleOwner, Observer {
//            when (it) {
//                is PaymentVerificationEvent.LoadPaymentVerificationUrl -> {
//                    binding.paymentVerificationProgressView.root.visible(true)
//                    binding.paymentVerificationWebView.loadUrl(it.pendingPayment.paymentUrl)
//                }
//                is PaymentVerificationEvent.ContentReady -> {
//                    binding.paymentVerificationProgressView.root.visible(false)
//                }
//                is PaymentVerificationEvent.ContentLoadingFailed -> {
//                    binding.paymentVerificationProgressView.root.visible(true)
//                    dismiss()
//                }
//                is PaymentVerificationEvent.VerificationFailed -> {
//                    dismiss()
//                }
//                is PaymentVerificationEvent.VerificationCanceled -> {
//                    dismiss()
//                }
//                is PaymentVerificationEvent.VerificationSuccess -> {
//                    dismiss()
//                }
//            }
//        })
    }

    companion object {
        val TAG: String = TestDialogFragment::class.java.simpleName

        private const val ARG_PENDING_PAYMENT = "PendingPayment"

        fun newInstance() = TestDialogFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }
}