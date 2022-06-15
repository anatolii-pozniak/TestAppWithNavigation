package com.example.testappwithnavigation

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.fragment.app.Fragment
import com.example.testappwithnavigation.databinding.FragmentFirstBinding
import com.example.testappwithnavigation.login.authResultLifecycleObserver

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val authLifecycleObserver by authResultLifecycleObserver(::onActivityResult)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            authLifecycleObserver.launchAuthActivity(requireContext())
        }
    }

    private fun onActivityResult(result: ActivityResult?) {
        Log.e("result", "code - ${result?.resultCode}")
        if (result?.resultCode == RESULT_OK) {
            result.data?.let {
                Log.e(
                    "result", "email - ${it.getStringExtra("username")} " +
                            "pswd - ${it.getStringExtra("password")}"
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}