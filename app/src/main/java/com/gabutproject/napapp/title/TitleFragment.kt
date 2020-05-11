package com.gabutproject.napapp.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.gabutproject.napapp.databinding.TitleFragmentBinding
import com.gabutproject.napapp.R

class TitleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<TitleFragmentBinding>(
            inflater,
            R.layout.title_fragment,
            container,
            false
        )

        return binding.root
    }
}