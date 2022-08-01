package com.efronnypardede.xpensive.dashboard

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
import com.efronnypardede.xpensive.databinding.FragmentAddHistoryBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddHistoryFragment : Fragment(), MenuProvider {
    private lateinit var dataBinding: FragmentAddHistoryBinding
    private val viewModel: AddHistoryViewModel by viewModels()
    private val navController: NavController
        get() = findNavController()

    @Inject
    lateinit var xpenseTypeArrayAdapter: XpenseTypeArrayAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.addMenuProvider(this, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentAddHistoryBinding.inflate(inflater, container, false).also {
            dataBinding = it
            it.lifecycleOwner = viewLifecycleOwner
            it.spinnerXpenseCategory.adapter = xpenseTypeArrayAdapter
            it.viewModel = viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            isSuccess.observe(viewLifecycleOwner) {
                if (it == true) {
                    Toast.makeText(context, R.string.data_added, Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }
            }

            errorMessage.observe(viewLifecycleOwner) {
                if (it != null) {
                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                }
            }

            showDatePicker.observe(viewLifecycleOwner) {
                XpenseDatePickerFragment().show(childFragmentManager, "DatePicker")
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
