package com.example.baubap.feature.message.presentation.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout.LayoutParams.WRAP_CONTENT
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.baubap.databinding.DialogFragmentMessageBinding

class MessageDialogFragment : DialogFragment() {
    private var _binding: DialogFragmentMessageBinding? = null

    private val binding get() = _binding!!

    private var title: String? = null
    private var body: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = arguments?.getString(TITLE_ARG)
        body = arguments?.getString(BODY_ARG)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(MATCH_PARENT, WRAP_CONTENT)
        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = DialogFragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateUI()

        clickListenerViews()
    }

    private fun updateUI() {
        binding.tvTitle.text = title
        binding.tvBody.text = body
    }

    private fun clickListenerViews() {
        binding.btnOk.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TITLE_ARG = "title"
        private const val BODY_ARG = "body"
        fun show(
            fragmentManager: FragmentManager, title: String, body: String?
        ): MessageDialogFragment {
            val fragment = MessageDialogFragment()
            val args = Bundle()
            args.putString(TITLE_ARG, title)
            args.putString(BODY_ARG, body)
            fragment.arguments = args
            fragment.show(fragmentManager, "MessageDialogFragment")
            return fragment
        }
    }
}
