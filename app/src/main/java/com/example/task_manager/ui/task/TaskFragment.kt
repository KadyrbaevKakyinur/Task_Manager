package com.example.task_manager.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.task_manager.App
import com.example.task_manager.R
import com.example.task_manager.databinding.FragmentTaskBinding
import com.example.task_manager.model.Task


class TaskFragment : Fragment() {

    private var _binding: FragmentTaskBinding? = null
    private val binding get() = _binding!!
    lateinit var task: Task

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentTaskBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments == null) {
            binding.btnSave.setOnClickListener {
                onSave()
            }
        }else{
            task = arguments!!.getSerializable("TASK") as Task
            binding.edtTitle.setText(task.title)
            binding.edtDesc.setText(task.desc)
            binding.btnSave.text = (getString(R.string.update))

            binding.btnSave.setOnClickListener {
                onUpdate()
            }
        }
    }

    private fun onSave() {
        val data = Task(
            title = binding.edtTitle.text.toString(),
            desc = binding.edtDesc.text.toString()
        )
        App.db.taskDao().insert(data)
        findNavController().navigateUp()
    }

    private fun onUpdate() {
        val data = Task(
            id = task.id,
            title = binding.edtTitle.text.toString(),
            desc = binding.edtDesc.text.toString()
        )
        App.db.taskDao().update(data)
        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}