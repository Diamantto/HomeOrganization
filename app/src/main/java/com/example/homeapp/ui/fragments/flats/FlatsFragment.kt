package com.example.homeapp.ui.fragments.flats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeapp.MyApplication
import com.example.homeapp.R
import com.example.homeapp.data.db.AppDataSource
import com.example.homeapp.databinding.FragmentFlatsBinding
import com.example.homeapp.ui.adapters.FlatAdapter
import javax.inject.Inject


class FlatsFragment : Fragment(R.layout.fragment_flats) {
    @Inject
    lateinit var appDataSource: AppDataSource

    private lateinit var rvAdapter: FlatAdapter
    private lateinit var viewModel: FlatsViewModel

    private var _binding: FragmentFlatsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inject()
        configureViewModel()
        configureRvAdapter()
        setupObservers()
        configureClickListeners()
        viewModel.getFlats()
    }

    private fun setupObservers() {
        viewModel.flats.observe(viewLifecycleOwner) {
            rvAdapter.items = it
        }
    }

    private fun configureRvAdapter() {
        rvAdapter = FlatAdapter()
        rvAdapter.onItemClickCallBack = {
            findNavController().navigate(
                FlatsFragmentDirections.actionFlatsFragmentToFlatDetailFragment(
                    it
                )
            )
        }
        binding.rvFlats.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvFlats.adapter = rvAdapter
    }

    private fun configureClickListeners() {
        binding.ivCreateFlat.setOnClickListener {
            findNavController().navigate(FlatsFragmentDirections.actionFlatsFragmentToFlatEditFragment())
        }
    }

    private fun configureViewModel() {
        val factory = FlatsViewModelFactory(appDataSource)
        viewModel = ViewModelProvider(this, factory)[FlatsViewModel::class.java]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun inject() {
        val app = requireActivity().application as MyApplication
        app.appComponent.inject(this)
    }

}