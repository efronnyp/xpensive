package com.efronnypardede.xpensive.manage

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.efronnypardede.xpensive.R
import com.efronnypardede.xpensive.databinding.FragmentAddSourceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddSourceFragment : Fragment(), MenuProvider {
    private val viewModel: AddSourceViewModel by viewModels()
    private val navController: NavController
        get() = findNavController()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.addMenuProvider(this, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentAddSourceBinding.inflate(inflater, container, false).let {
            it.lifecycleOwner = viewLifecycleOwner
            it.viewModel = viewModel
            it.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            isSuccess.observe(viewLifecycleOwner) {
                if (it == true) {
                    Toast.makeText(context, R.string.data_added, Toast.LENGTH_SHORT)
                        .show()
                    navController.popBackStack()
                }
            }

            errorMessage.observe(viewLifecycleOwner) {
                if (it != null) {
                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return false
    }
}
