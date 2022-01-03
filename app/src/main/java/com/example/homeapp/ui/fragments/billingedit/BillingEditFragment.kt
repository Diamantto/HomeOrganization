package com.example.homeapp.ui.fragments.billingedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.homeapp.MyApplication
import com.example.homeapp.R
import com.example.homeapp.data.db.AppDataSource
import com.example.homeapp.data.db.entities.Billing
import com.example.homeapp.data.db.entities.Month
import com.example.homeapp.data.db.entities.Service
import com.example.homeapp.databinding.FragmentBillingEditBinding
import javax.inject.Inject

class BillingEditFragment : Fragment(R.layout.fragment_person_edit) {
    private lateinit var viewModel: BillingEditViewModel

    @Inject
    lateinit var appDataSource: AppDataSource
    private val args by navArgs<BillingEditFragmentArgs>()

    private var _binding: FragmentBillingEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBillingEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inject()
        configureViewModel()
        configureClickListeners()
        defaultView()

        binding.spnBillingMonth.adapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            viewModel.billingMonth
        )
        binding.spnBillingService.adapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            viewModel.billingService
        )
    }

    private fun defaultView() {
        if (args.id > 0) {
            viewModel.billing.observe(viewLifecycleOwner) { billing ->
                binding.etBillingFlat.setText(billing.flatNumber.toString())
                binding.etBilllingSum.setText(billing.sum.toString())
                binding.etBillingId.setText(args.id.toString())
                binding.spnBillingMonth.setSelection(viewModel.billingMonth.indexOf(billing.time.toString()))
                binding.spnBillingService.setSelection(viewModel.billingService.indexOf(billing.serviceType.toString()))
            }
        }
    }

    private fun insertData() {
        val flatNumber = binding.etBillingFlat.text?.toString()
        val month = Month.valueOf(binding.spnBillingMonth.selectedItem.toString())
        val sum = binding.etBilllingSum.text?.toString()
        val service = Service.valueOf(binding.spnBillingService.selectedItem.toString())

        val isSumCorrect = !sum.isNullOrBlank()
        val isFlatCorrect =
            !flatNumber.isNullOrBlank() && flatNumber.isDigitsOnly() && (flatNumber.toInt() > 0)

        if (isFlatCorrect && isSumCorrect) {
            val billing =
                Billing(flatNumber!!.toInt(), month, sum!!.toFloat(), service)
            viewModel.insertBilling(billing)
            Toast.makeText(context, R.string.new_billing_created, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, R.string.error_billing_created, Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateData() {
        val flatNumber = binding.etBillingFlat.text?.toString()
        val month = Month.valueOf(binding.spnBillingMonth.selectedItem.toString())
        val sum = binding.etBilllingSum.text?.toString()
        val service = Service.valueOf(binding.spnBillingService.selectedItem.toString())
        val billingId = binding.etBillingId.text?.toString()

        val isSumCorrect = !sum.isNullOrBlank()
        val isFlatCorrect =
            !flatNumber.isNullOrBlank() && flatNumber.isDigitsOnly() && (flatNumber.toInt() > 0)
        val isBillingIdCorrect = !billingId.isNullOrBlank() && billingId.isDigitsOnly()

        if (isFlatCorrect && isSumCorrect && isBillingIdCorrect) {
            val billing =
                Billing(flatNumber!!.toInt(), month, sum!!.toFloat(), service, billingId!!.toInt())
            viewModel.insertBilling(billing)
            Toast.makeText(context, R.string.billing_updated, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, R.string.error_billing_updated, Toast.LENGTH_SHORT).show()
        }
    }


    private fun deleteData() {
        val billingId = binding.etBillingId.text?.toString()
        val isBillingIdCorrect = !billingId.isNullOrBlank() && billingId.isDigitsOnly()

        if (isBillingIdCorrect) {
            viewModel.deleteBilling(billingId!!.toInt())
            Toast.makeText(context, R.string.billing_deleted, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, R.string.error_billing_deleted, Toast.LENGTH_SHORT).show()
        }
    }

    private fun configureClickListeners() {
        binding.btnAddBilling.setOnClickListener {
            insertData()
        }
        binding.btnDeleteBilling.setOnClickListener {
            deleteData()
        }
        binding.btnUpdateBilling.setOnClickListener {
            updateData()
        }
    }

    private fun configureViewModel() {
        val factory = BillingEditViewModelFactory(appDataSource, args.id)
        viewModel = ViewModelProvider(this, factory)[BillingEditViewModel::class.java]
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