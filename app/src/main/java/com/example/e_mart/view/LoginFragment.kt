package com.example.e_mart.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.e_mart.R
import com.example.e_mart.databinding.FragmentLoginBinding
import com.example.e_mart.viewmodel.LoginViewModel


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel : LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeViewModel()
    }

    private fun initViews() {
        binding.btnLogin.setOnClickListener{
            val Email = binding.etEmail.text.toString()
            val FingerPrint = binding.etPassword.text.toString()

            if(Email.isEmpty() || FingerPrint.isEmpty()){
                Toast.makeText(requireContext(),"Please Enter Email and Password",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.existUser(Email,FingerPrint)
        }

        binding.btnGotoregister.setOnClickListener{
            navigateToSecondFragment()
        }
    }

    private fun navigateToSecondFragment() {
        val secondFragment = RegisterFragment()
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container1, secondFragment)
            .commit()
    }

    private fun observeViewModel() {
        viewModel.message.observe(viewLifecycleOwner) { msg ->
            if (msg != null) {
                Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
            }
        }
        viewModel.existingUser.observe(viewLifecycleOwner) { userResponse ->
            if(userResponse.Status == 0){
                navigateToDashBoard()
            }
        }
    }

    private fun navigateToDashBoard() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container1, CategoryFragment())  // Make sure DashBoardFragment exists and is imported
            .addToBackStack(null)
            .commit()
    }
}
