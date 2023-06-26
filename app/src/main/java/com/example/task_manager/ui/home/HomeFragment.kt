package com.example.task_manager.ui.home


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.task_manager.App
import com.example.task_manager.R
import com.example.task_manager.databinding.FragmentHomeBinding
import com.example.task_manager.model.Task
import com.example.task_manager.ui.home.adapter.TaskAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val adapter = TaskAdapter(this::onLongClickForTask,this::onClickTask)
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
        val list = App.db.taskDao().getAll()
        adapter.setTasks(list)
        binding.fab.setOnClickListener{
            findNavController().navigate(R.id.taskFragment)
        }
        putData()
        attachToTaskFragment()
        binding.recuclerView.adapter= adapter
    }

    private fun putData() {
        val list = App.db.taskDao().getAll()
        adapter.setTasks(list)
    }

    private fun attachToTaskFragment() {
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }


    private fun onClickTask(bundle: Bundle){
        findNavController().navigate(R.id.taskFragment,bundle)
    }

    private fun onLongClickForTask(task: Task) {
        val alertDialogDeleteTask = AlertDialog.Builder(requireContext())

        val dialogView = layoutInflater.inflate(R.layout.alert_dialog_from_task, null)
        alertDialogDeleteTask.setView(dialogView)


        alertDialogDeleteTask.setPositiveButton("Confirm") { _, _ ->
            App.db.taskDao().delete(task)
            putData()
        }

        alertDialogDeleteTask.setNegativeButton("Cancel") { dialog, _ ->
            dialog?.cancel()
        }

        alertDialogDeleteTask.create().show()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}