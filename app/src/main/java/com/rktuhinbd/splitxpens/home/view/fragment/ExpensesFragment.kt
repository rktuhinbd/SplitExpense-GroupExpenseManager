package com.rktuhinbd.splitxpens.home.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rktuhinbd.splitxpens.databinding.FragmentExpensesBinding
import com.rktuhinbd.splitxpens.home.viewmodel.ExpensesViewModel

class ExpensesFragment : Fragment() {

    private var _binding: FragmentExpensesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    fun newInstance(counter: Int?): ExpensesFragment {
        val fragment = ExpensesFragment()
        val args = Bundle()
        args.putInt("_home_", counter!!)
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val expensesViewModel =
            ViewModelProvider(this).get(ExpensesViewModel::class.java)

        _binding = FragmentExpensesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textExpenses
        expensesViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}