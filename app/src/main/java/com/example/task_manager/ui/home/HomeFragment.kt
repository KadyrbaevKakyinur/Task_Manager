package com.example.task_manager.ui.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.task_manager.R
import com.example.task_manager.databinding.FragmentHomeBinding
import com.example.task_manager.model.Task
import com.example.task_manager.ui.home.adapter.TaskAdapter
import com.example.task_manager.ui.task.TaskFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val adapter = TaskAdapter()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragmentResultListener(TaskFragment.TASK_REQUEST){ requestKey,bundle ->
            val data = bundle.getSerializable(TaskFragment.TASK_KEY) as Task
           adapter.setTask(data)
        }
        binding.fab.setOnClickListener{
            findNavController().navigate(R.id.taskFragment)
        }
        binding.recuclerView.adapter= adapter
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}