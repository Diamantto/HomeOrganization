package com.example.homeapp.ui.fragments.persons

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
import com.example.homeapp.databinding.FragmentPersonsBinding
import com.example.homeapp.ui.adapters.FlatAdapter
import com.example.homeapp.ui.adapters.PersonAdapter
import com.example.homeapp.ui.fragments.flatdetail.FlatDetailFragmentDirections
import com.example.homeapp.ui.fragments.flats.FlatsViewModel
import com.example.homeapp.ui.fragments.flats.FlatsViewModelFactory
import javax.inject.Inject

class PersonsFragment : Fragment(R.layout.fragment_persons) {
    @Inject
    lateinit var appDataSource: AppDataSource

    private lateinit var rvAdapter: PersonAdapter
    private lateinit var viewModel: PersonsViewModel

    private var _binding: FragmentPersonsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inject()
        configureViewModel()
        configureRvAdapter()
        setupObservers()
        configureClickListeners()
        viewModel.getPersons()
    }

    private fun setupObservers() {
        viewModel.persons.observe(viewLifecycleOwner) {
            rvAdapter.items = it
        }
    }

    private fun configureRvAdapter() {
        rvAdapter = PersonAdapter()
        binding.rvPersons.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvPersons.adapter = rvAdapter
        rvAdapter.onItemClickCallBack = {
            findNavController().navigate(PersonsFragmentDirections.actionPersonsFragmentToPersonEditFragment(it))
        }
    }

    private fun configureClickListeners() {
        binding.ivCreatePerson.setOnClickListener {
            findNavController().navigate(PersonsFragmentDirections.actionPersonsFragmentToPersonEditFragment(-1))
        }
    }

    private fun configureViewModel() {
        val factory = PersonsViewModelFactory(appDataSource)
        viewModel = ViewModelProvider(this, factory).get(PersonsViewModel::class.java)
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