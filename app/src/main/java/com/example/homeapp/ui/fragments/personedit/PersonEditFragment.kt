package com.example.homeapp.ui.fragments.personedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.homeapp.MyApplication
import com.example.homeapp.R
import com.example.homeapp.data.db.AppDataSource
import com.example.homeapp.data.db.entities.Person
import com.example.homeapp.databinding.FragmentPersonEditBinding
import javax.inject.Inject

class PersonEditFragment : Fragment(R.layout.fragment_person_edit) {
    private lateinit var viewModel: PersonEditViewModel

    @Inject
    lateinit var appDataSource: AppDataSource
    private val args by navArgs<PersonEditFragmentArgs>()
    private var _binding: FragmentPersonEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inject()
        configureViewModel()
        defaultView()
        configureClickListeners()

    }

    private fun defaultView() {
        if (args.id > 0) {
            viewModel.person.observe(viewLifecycleOwner) { person ->
                binding.etPersonSurname.setText(person.surname)
                binding.etPersonName.setText(person.name)
                binding.etPersonPhone.setText(person.phoneNumber)
                binding.etPersonFlat.setText(person.flatNumber.toString())
                binding.etPersonId.setText(args.id.toString())
            }
        }
    }

    private fun insertData() {
        val surname = binding.etPersonSurname.text?.toString()
        val name = binding.etPersonName.text?.toString()
        val flatNumber = binding.etPersonFlat.text?.toString()
        val phone = binding.etPersonPhone.text?.toString()

        val isSurnameCorrect = !surname.isNullOrBlank()
        val isNameCorrect = !name.isNullOrBlank()
        val isFlatCorrect =
            !flatNumber.isNullOrBlank() && flatNumber.isDigitsOnly() && (flatNumber.toInt() > 0)
        val isPhoneCorrect = !phone.isNullOrEmpty()

        if (isSurnameCorrect && isNameCorrect && isFlatCorrect && isPhoneCorrect) {
            val person = Person(surname!!, name!!, flatNumber!!.toInt(), phone!!)
            viewModel.insertPerson(person)
            Toast.makeText(context, R.string.new_person_created, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, R.string.error_person_created, Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateData() {
        val surname = binding.etPersonSurname.text?.toString()
        val name = binding.etPersonName.text?.toString()
        val flatNumber = binding.etPersonFlat.text?.toString()
        val phone = binding.etPersonPhone.text?.toString()
        val personId = binding.etPersonId.text?.toString()

        val isSurnameCorrect = !surname.isNullOrBlank()
        val isNameCorrect = !name.isNullOrBlank()
        val isFlatCorrect =
            !flatNumber.isNullOrBlank() && flatNumber.isDigitsOnly() && (flatNumber.toInt() > 0)
        val isPhoneCorrect = !phone.isNullOrEmpty()
        val isPersonIdCorrect = !personId.isNullOrBlank() && personId.isDigitsOnly()

        if (isSurnameCorrect && isNameCorrect && isFlatCorrect && isPhoneCorrect && isPersonIdCorrect) {
            val person =
                Person(surname!!, name!!, flatNumber!!.toInt(), phone!!, personId!!.toInt())
            viewModel.insertPerson(person)
            Toast.makeText(context, R.string.person_updated, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, R.string.error_person_updated, Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteData() {
        val personId = binding.etPersonId.text?.toString()
        val isPersonIdCorrect = !personId.isNullOrBlank() && personId.isDigitsOnly()

        if (isPersonIdCorrect) {
            viewModel.deletePerson(personId!!.toInt())
            Toast.makeText(context, R.string.person_deleted, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, R.string.error_person_deleted, Toast.LENGTH_SHORT).show()
        }
    }

    private fun configureClickListeners() {
        binding.btnAddPerson.setOnClickListener {
            insertData()
        }
        binding.btnDeletePerson.setOnClickListener {
            deleteData()
        }
        binding.btnUpdatePerson.setOnClickListener {
            updateData()
        }

    }

    private fun configureViewModel() {
        val factory = PersonEditViewModelFactory(appDataSource, args.id)
        viewModel = ViewModelProvider(this, factory)[PersonEditViewModel::class.java]
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