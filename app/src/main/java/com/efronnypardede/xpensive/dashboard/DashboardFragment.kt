package com.efronnypardede.xpensive.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.efronnypardede.xpensive.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private lateinit var dataBinding: FragmentDashboardBinding
    private val viewModel: DashboardViewModel by viewModels()

    @Inject
    lateinit var xpenseHistoryListAdapter: XpenseHistoryListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDashboardBinding.inflate(inflater, container, false).also {
            dataBinding = it
            it.lifecycleOwner = viewLifecycleOwner
            it.viewModel = viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.recyclerviewExpenseHistory.adapter = xpenseHistoryListAdapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchData()
    }
}
