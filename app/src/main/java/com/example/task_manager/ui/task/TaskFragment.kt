package com.example.task_manager.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.task_manager.R
import com.example.task_manager.databinding.FragmentTaskBinding
import com.example.task_manager.model.Task


class TaskFragment : Fragment() {

    private lateinit var binding : FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentTaskBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            onSave()
        }
    }

    private fun onSave() {
        val data = Task(
            title = binding.edtTitle.text.toString(),
            desc = binding.edtDesc.text.toString()
        )
        setFragmentResult(TASK_REQUEST, bundleOf(TASK_KEY to data,))
        findNavController().navigateUp()
    }


    companion object{
        const val TASK_REQUEST = "task.result"
        const val TASK_KEY = "task.result"
    }



}