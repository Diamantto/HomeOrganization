package com.example.homeapp.ui.fragments.flatedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.homeapp.MyApplication
import com.example.homeapp.R
import com.example.homeapp.data.db.AppDataSource
import com.example.homeapp.data.db.entities.Flat
import com.example.homeapp.databinding.FragmentFlatEditBinding
import javax.inject.Inject

class FlatEditFragment : Fragment(R.layout.fragment_person_edit) {
    private lateinit var viewModel: FlatEditViewModel

    @Inject
    lateinit var appDataSource: AppDataSource


    private var _binding: FragmentFlatEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlatEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inject()
        configureViewModel()
        configureClickListeners()
    }


    private fun insertData() {
        val flatNumber = binding.etFlatNumber.text?.toString()
        val personQuantity = binding.etPersonQuantity.text?.toString()
        val roomQuantity = binding.etRoomQuantity.text?.toString()
        val description = binding.etDescription.text?.toString()

        val isFlatCorrect =
            !flatNumber.isNullOrBlank() && flatNumber.isDigitsOnly() && (flatNumber.toInt() > 0)
        val isPersonQuantityCorrect =
            !personQuantity.isNullOrBlank() && personQuantity.isDigitsOnly() && (personQuantity.toInt() > 0)
        val isRoomQuantityCorrect =
            !roomQuantity.isNullOrBlank() && roomQuantity.isDigitsOnly() && (roomQuantity.toInt() > 0)

        if (isFlatCorrect && isPersonQuantityCorrect && isRoomQuantityCorrect) {
            val flat =
                Flat(
                    flatNumber!!.toInt(),
                    personQuantity!!.toInt(),
                    roomQuantity!!.toInt(),
                    description!!
                )
            viewModel.insertFlat(flat)
            Toast.makeText(context, R.string.new_flat_created, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, R.string.error_flat_created, Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateData() {
        val flatNumber = binding.etFlatNumber.text?.toString()
        val personQuantity = binding.etPersonQuantity.text?.toString()
        val roomQuantity = binding.etRoomQuantity.text?.toString()
        val description = binding.etDescription.text?.toString()
        val flatId = binding.etFlatId.text?.toString()

        val isFlatCorrect =
            !flatNumber.isNullOrBlank() && flatNumber.isDigitsOnly() && (flatNumber.toInt() > 0)
        val isPersonQuantityCorrect =
            !personQuantity.isNullOrBlank() && personQuantity.isDigitsOnly() && (personQuantity.toInt() > 0)
        val isRoomQuantityCorrect =
            !roomQuantity.isNullOrBlank() && roomQuantity.isDigitsOnly() && (roomQuantity.toInt() > 0)
        val isFlatIdCorrect = !flatId.isNullOrBlank() && flatId.isDigitsOnly()

        if (isFlatCorrect && isPersonQuantityCorrect && isRoomQuantityCorrect && isFlatIdCorrect) {
            val flat =
                Flat(
                    flatNumber!!.toInt(),
                    personQuantity!!.toInt(),
                    roomQuantity!!.toInt(),
                    description!!,
                    flatId!!.toInt()
                )
            viewModel.insertFlat(flat)
            Toast.makeText(context, R.string.flat_updated, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, R.string.error_flat_updated, Toast.LENGTH_SHORT).show()
        }
    }


    private fun deleteData() {
        val flatId = binding.etFlatId.text?.toString()
        val isFlatIdCorrect = !flatId.isNullOrBlank() && flatId.isDigitsOnly()

        if (isFlatIdCorrect) {
            viewModel.deleteFlat(flatId!!.toInt())
            Toast.makeText(context, R.string.flat_deleted, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, R.string.error_flat_deleted, Toast.LENGTH_SHORT).show()
        }
    }

    private fun configureClickListeners() {
        binding.btnAddFlat.setOnClickListener {
            insertData()
        }
        binding.btnDeleteFlat.setOnClickListener {
            deleteData()
        }
        binding.btnUpdateFlat.setOnClickListener {
            updateData()
        }
    }

    private fun configureViewModel() {
        val factory = FlatEditViewModelFactory(appDataSource)
        viewModel = ViewModelProvider(this, factory)[FlatEditViewModel::class.java]
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