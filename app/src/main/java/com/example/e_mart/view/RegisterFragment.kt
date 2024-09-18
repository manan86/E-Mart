package com.example.e_mart.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.e_mart.R
import com.example.e_mart.databinding.FragmentRegisterBinding
import com.example.e_mart.viewmodel.RegisterViewModel


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel : RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            btnGotologin.setOnClickListener {
                val loginFragment = LoginFragment()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container1, loginFragment)
                    .commit()
            }

            btnRegister.setOnClickListener {
                val Name = etName.text.toString()
                val Contact = etPhone.text.toString().toLong()
                val Email = etEmail2.text.toString()
                val Finger = etPassword2.text.toString()

                viewModel.registerUser(Name,Contact,Email,Finger)

                //Observer for Response
                viewModel.registratonStatus.observe(viewLifecycleOwner) {
                    Toast.makeText(requireContext(), "Register success!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
