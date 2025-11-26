package com.cookandroid.team_project.ui.players

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cookandroid.team_project.R
import com.cookandroid.team_project.di.ServiceLocator
import com.cookandroid.team_project.ui.MainViewModel
import com.cookandroid.team_project.ui.common.AssistsVMFactory

class AssistsFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    private var swipeLayout: SwipeRefreshLayout? = null

    private val mainViewModel: MainViewModel by activityViewModels()
    private val viewModel: AssistsViewModel by viewModels {
        AssistsVMFactory(ServiceLocator.repository)
    }

    private val adapter = PlayerAdapter(statLabel = "도움")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(R.layout.fragment_players, container, false)
        recyclerView = view.findViewById(R.id.recycler)
        swipeLayout = view.findViewById(R.id.swipe)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        recyclerView?.adapter = adapter

        swipeLayout?.setOnRefreshListener {
            val sel = mainViewModel.selection.value ?: return@setOnRefreshListener
            viewModel.load(sel.leagueId, sel.season)
        }

        viewModel.rows.observe(viewLifecycleOwner) { adapter.submit(it) }
        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            swipeLayout?.isRefreshing = loading
        }

        mainViewModel.selection.observe(viewLifecycleOwner) { sel ->
            viewModel.load(sel.leagueId, sel.season)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerView = null
        swipeLayout = null
    }
}


