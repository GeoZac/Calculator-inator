package com.inator.calculator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.inator.calculator.History.History
import com.inator.calculator.R
import com.inator.calculator.adapters.HistoryAdapter
import com.inator.calculator.adapters.HistoryItemClickListener
import com.inator.calculator.viewmodel.HistoryViewModel
import kotlinx.android.synthetic.main.fragment_history.*
import java.util.*

class HistoryFragment : Fragment(), HistoryItemClickListener {
    private var historyItems = ArrayList<History>()
    private var historyAdapter: HistoryAdapter? = null
    private val viewModel: HistoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        historyRecyclerView.apply {
            setHasFixedSize(false)
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager.apply {
                reverseLayout = false
                stackFromEnd = true
            }
            historyAdapter = HistoryAdapter(context, historyItems, this@HistoryFragment)
            adapter = historyAdapter

        }

        viewModel.allHistory.observe(viewLifecycleOwner) { list ->
            historyAdapter?.updateList(list)
        }

        super.onViewCreated(view, savedInstanceState)
    }


    override fun onItemClicked(history: History) {
        viewModel.setClickedExpression(history)
    }
}