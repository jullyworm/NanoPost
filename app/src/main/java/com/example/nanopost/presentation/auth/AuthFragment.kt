package com.example.nanopost.presentation.auth

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.nanopost.R
import com.example.nanopost.data.models.CheckUsernameResult
import com.example.nanopost.databinding.FragmentAuthBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment: Fragment(R.layout.fragment_auth) {

    private val binding by viewBinding(FragmentAuthBinding::bind)
    private val viewModel: AuthViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonContinue.setOnClickListener {
                viewModel.checkUsername(binding.loginText.text.toString())
            }
        viewModel.checkUsernameLiveData.observe(viewLifecycleOwner){ result ->
            when(result){
                CheckUsernameResult.TooShort -> {
                    binding.login.setEndIconDrawable(R.drawable.baseline_error_24)
                    binding.login.helperText = getString(R.string.login_tooShort)
                }
                CheckUsernameResult.TooLong -> {
                    binding.login.setEndIconDrawable(R.drawable.baseline_error_24)
                    binding.login.helperText = getString(R.string.login_tooLong)
                }
                CheckUsernameResult.InvalidCharacters -> {
                    binding.login.setEndIconDrawable(R.drawable.baseline_error_24)
                    binding.login.helperText = getString(R.string.login_InvalidCharacters)
                }
                CheckUsernameResult.Taken -> {
                    binding.login.endIconDrawable = null
                    binding.login.helperText = null
                    binding.buttonContinue.visibility = GONE
                    binding.buttonSignin.visibility = VISIBLE
                    binding.password.visibility = VISIBLE
                    binding.login.isEnabled = false
                }
                CheckUsernameResult.Free -> {
                    binding.login.endIconDrawable = null
                    binding.login.helperText = null
                    binding.buttonContinue.visibility = GONE
                    binding.buttonRegistr.visibility = VISIBLE
                    binding.password.visibility = VISIBLE
                    binding.secondPassword.visibility = VISIBLE
                    binding.login.isEnabled = false
                }
                else -> {}
            }
        }

        binding.buttonSignin.setOnClickListener {
                val login = binding.loginText.text.toString()
                val password = binding.passwordText.text.toString()

                if(password.length > 7) {
                    viewModel.authorize(login, password)
                    binding.password.endIconDrawable = null
                    binding.password.helperText = null
                    val navController = findNavController()
                    navController.graph = navController.navInflater.inflate(R.navigation.nav_graph_main)
                }
                else{
                    binding.password.setEndIconDrawable(R.drawable.baseline_error_24)
                    binding.password.helperText = getString(R.string.password_tooShort)
                }
            }
        binding.buttonRegistr.setOnClickListener {
            val login = binding.loginText.text.toString()
            val password1 = binding.passwordText.text.toString()
            val password2 = binding.secondPasswordText.text.toString()
            if(password1.length > 7) {
                if (password1 == password2){
                    viewModel.register(login, password1)
                    binding.password.endIconDrawable = null
                    binding.password.helperText = null
                    binding.secondPassword.endIconDrawable = null
                    binding.secondPassword.helperText = null
                    val navController = findNavController()
                    navController.graph = navController.navInflater.inflate(R.navigation.nav_graph_main)
                }
                else {
                    binding.secondPassword.setEndIconDrawable(R.drawable.baseline_error_24)
                    binding.secondPassword.helperText = getString(R.string.password_notConfirm)
                }
            }
            else {
                binding.password.setEndIconDrawable(R.drawable.baseline_error_24)
                binding.password.helperText = getString(R.string.password_tooShort)
            }
        }

       /* viewModel.errLiveData.observe(viewLifecycleOwner){err ->
            Snackbar.make(view, err.message.toString(), Snackbar.LENGTH_LONG)
                .show();
        }*/
    }
}