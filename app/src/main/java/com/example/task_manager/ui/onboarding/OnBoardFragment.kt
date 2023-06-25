package com.example.task_manager.ui.onboarding

import androidx.navigation.fragment.findNavController
import com.example.task_manager.R
import com.example.task_manager.data.local.Pref
import com.example.task_manager.databinding.FragmentOnBoardBinding
import com.example.task_manager.ui.fragment.BaseFragment
import com.example.task_manager.ui.onboarding.adapter.BoardAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class OnBoardFragment : BaseFragment<FragmentOnBoardBinding>(FragmentOnBoardBinding::inflate),
    BoardAdapter.Result {

    private val adapter: BoardAdapter by lazy {
        BoardAdapter(this)
    }
    private val pref : Pref by lazy {
        Pref(requireContext())
    }

    override fun setupUI() {
        binding.pager.adapter = adapter
    }

    override fun setupObserver() {
        TabLayoutMediator(
            binding.tabLayout, binding.pager
        ) { tab: TabLayout.Tab, position: Int ->
            tab.setIcon(
                R.drawable.tab_selector
            )
        }.attach()
    }

    override fun onClick() {
        pref.saveSeen()
        findNavController().navigateUp()
    }



}
