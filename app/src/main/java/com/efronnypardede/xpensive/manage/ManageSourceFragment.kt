package com.efronnypardede.xpensive.manage

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.efronnypardede.xpensive.R
import com.efronnypardede.xpensive.databinding.FragmentManageSourceBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ManageSourceFragment : Fragment(), MenuProvider {
    private val viewModel: ManageSourceViewModel by viewModels()
    private val navController: NavController
        get() = findNavController()
    private lateinit var dataBinding: FragmentManageSourceBinding

    @Inject
    lateinit var xpenseSourceListAdapter: XpenseSourceListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.addMenuProvider(this, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentManageSourceBinding.inflate(inflater, container, false)
            .apply {
                viewModel = this@ManageSourceFragment.viewModel
                lifecycleOwner = viewLifecycleOwner
            }
            .also { dataBinding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.recyclerviewXpenseSource.adapter = xpenseSourceListAdapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchData()
    }

    override fun onCreateMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_manage_xpense_source, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.action_add_xpense_source -> {
                navController.navigate(R.id.action_manage_to_add_source)
                true
            }
            else -> false
        }
    }
}
