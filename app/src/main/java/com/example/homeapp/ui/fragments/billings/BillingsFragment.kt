package com.example.homeapp.ui.fragments.billings

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
import com.example.homeapp.databinding.FragmentBillingsBinding
import com.example.homeapp.ui.adapters.BillingAdapter
import com.example.homeapp.ui.fragments.flats.FlatsFragmentDirections
import com.example.homeapp.ui.fragments.flats.FlatsViewModelFactory
import com.example.homeapp.ui.fragments.persons.PersonsFragmentDirections
import javax.inject.Inject

class BillingsFragment : Fragment(R.layout.fragment_billings) {
    @Inject
    lateinit var appDataSource: AppDataSource

    private lateinit var rvAdapter: BillingAdapter
    private lateinit var viewModel: BillingsViewModel

    private var _binding: FragmentBillingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBillingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inject()
        configureViewModel()
        configureRvAdapter()
        setupObservers()
        configureClickListeners()
        viewModel.getBillings()
    }

    private fun setupObservers() {
        viewModel.billings.observe(viewLifecycleOwner) {
            rvAdapter.items = it
        }
    }

    private fun configureRvAdapter() {
        rvAdapter = BillingAdapter()
        binding.rvBillings.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvBillings.adapter = rvAdapter
        rvAdapter.onItemClickCallBack = {
            findNavController().navigate(BillingsFragmentDirections.actionBillingsFragmentToBillingEditFragment(it))
        }
    }

    private fun configureClickListeners() {
        binding.ivCreateBilling.setOnClickListener {
            findNavController().navigate(BillingsFragmentDirections.actionBillingsFragmentToBillingEditFragment(-1))
        }
    }

    private fun configureViewModel() {
        val factory = BillingsViewModelFactory(appDataSource)
        viewModel = ViewModelProvider(this, factory).get(BillingsViewModel::class.java)
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