package com.appiskey.socialpostsapp.views.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.appiskey.socialpostsapp.R
import com.appiskey.socialpostsapp.databinding.DialogPostDetailBinding
import com.bumptech.glide.Glide

class PostDetailDialog(
    private val imageURL: String
): DialogFragment(), View.OnClickListener {

    private lateinit var binding: DialogPostDetailBinding

    override fun onStart() {
        super.onStart()
        dialog?.let{
            it.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            it.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogPostDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivClose.setOnClickListener(this)

        //For Circular Progress bar for loading
        val circularProgressDrawable = CircularProgressDrawable(requireContext())
        circularProgressDrawable.strokeWidth = 8f
        circularProgressDrawable.centerRadius = 60f
        circularProgressDrawable.setTint(requireContext().getColor(R.color.colorSecondary))
        circularProgressDrawable.start()

        Glide
            .with(requireContext())
            .load(imageURL)
            .centerCrop()
            .placeholder(circularProgressDrawable)
            .into(binding.imageView)
    }

    override fun onClick(p0: View?) {
        when(p0){
            binding.ivClose -> {
                dismiss()
            }
        }
    }
}