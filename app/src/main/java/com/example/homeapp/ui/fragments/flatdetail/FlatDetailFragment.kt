package com.example.homeapp.ui.fragments.flatdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeapp.MyApplication
import com.example.homeapp.R
import com.example.homeapp.data.db.AppDataSource
import com.example.homeapp.databinding.FragmentFlatDetailBinding
import com.example.homeapp.ui.adapters.BillingAdapter
import com.example.homeapp.ui.adapters.PersonAdapter
import javax.inject.Inject

class FlatDetailFragment : Fragment(R.layout.fragment_flat_detail) {
    @Inject
    lateinit var appDataSource: AppDataSource

    private val args by navArgs<FlatDetailFragmentArgs>()
    private lateinit var rvBillingAdapter: BillingAdapter
    private lateinit var rvPersonAdapter: PersonAdapter
    private lateinit var viewModel: FlatDetailViewModel

    private var _binding: FragmentFlatDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlatDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inject()
        configureViewModel()
        configureRvAdapter()
        setupObservers()
        configureClickListeners()
        defaultView()
    }

    private fun setupObservers() {
        viewModel.persons.observe(viewLifecycleOwner) {
            rvPersonAdapter.items = it
        }

        viewModel.billings.observe(viewLifecycleOwner) {
            rvBillingAdapter.items = it
        }

        viewModel.flat.observe(viewLifecycleOwner) { flat ->
            binding.tvItemFlat.text = getString(
                R.string.flat_number_template, flat.number
            )
            binding.tvItemPersonQuantity.text = getString(
                R.string.flat_quantity_person_template, flat.personQuantity
            )
            binding.tvItemRoomQuantity.text = getString(
                R.string.flat_quantity_room_template, flat.roomQuantity
            )
            binding.tvItemDescription.text = getString(
                R.string.flat_description_template, flat.flatDescription
            )
            binding.tvItemFlatId.text = getString(
                R.string.flat_id_template, flat.flatId
            )
        }
    }

    private fun defaultView() {
        viewModel.getPersons(args.id)
        binding.rvFlatBillings.visibility = View.GONE
        binding.rvFlatPersons.visibility = View.VISIBLE
    }

    private val btnListener = View.OnClickListener { view ->
        when (view.id) {
            binding.btnPersons.id -> {
                viewModel.getPersons(args.id)
                binding.rvFlatBillings.visibility = View.GONE
                binding.rvFlatPersons.visibility = View.VISIBLE
            }
            binding.btnBillings.id -> {
                viewModel.getBillings(args.id)
                binding.rvFlatBillings.visibility = View.VISIBLE
                binding.rvFlatPersons.visibility = View.GONE
            }
        }
    }

    private fun configureClickListeners() {
        binding.btnPersons.setOnClickListener(btnListener)
        binding.btnBillings.setOnClickListener(btnListener)
    }

    private fun configureRvAdapter() {
        rvPersonAdapter = PersonAdapter()
        binding.rvFlatPersons.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvFlatPersons.adapter = rvPersonAdapter
        rvPersonAdapter.onItemClickCallBack = {
            findNavController().navigate(FlatDetailFragmentDirections.actionFlatDetailFragmentToPersonEditFragment(it))
        }

        rvBillingAdapter = BillingAdapter()
        binding.rvFlatBillings.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvFlatBillings.adapter = rvBillingAdapter
        rvBillingAdapter.onItemClickCallBack = {
            findNavController().navigate(FlatDetailFragmentDirections.actionFlatDetailFragmentToBillingEditFragment(it))
        }
    }


    private fun configureViewModel() {
        val factory = FlatDetailViewModelFactory(appDataSource, args.id)
        viewModel = ViewModelProvider(this, factory)[FlatDetailViewModel::class.java]
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