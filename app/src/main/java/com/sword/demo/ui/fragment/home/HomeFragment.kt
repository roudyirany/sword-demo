package com.sword.demo.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.view.scrollChangeEvents
import com.sword.demo.R
import com.sword.demo.base.BaseFragment
import com.sword.demo.databinding.FragmentHomeBinding
import com.sword.demo.extensions.addSubscriptionTo
import com.sword.demo.ui.fragment.home.adapter.BreedItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private val homeViewModel: HomeViewModel by hiltNavGraphViewModels(R.id.main_navigation)
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @Inject
    @Named("list_adapter")
    lateinit var listAdapter: BreedItemAdapter

    @Inject
    @Named("grid_adapter")
    lateinit var gridAdapter: BreedItemAdapter

    @Inject
    @Named("linear_layout_manager")
    lateinit var linearLayoutManager: RecyclerView.LayoutManager

    @Inject
    @Named("grid_layout_manager")
    lateinit var gridLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.breedsChanges()
            .doOnSubscribe {
                if (listAdapter.itemCount == 0 || gridAdapter.itemCount == 0) {
                    homeViewModel.getBreeds(0)
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
            .doOnNext { (items, _) ->
                binding.progressBar.visibility = View.GONE
                listAdapter.add(items)
                gridAdapter.add(items)
            }
            .filter { (_, shouldScroll) ->
                shouldScroll && !binding.recyclerView.canScrollVertically(1)
            }
            .subscribe {
                binding.recyclerView.smoothScrollBy(0, 100)
            }
            .addSubscriptionTo(this)

        homeViewModel.errorOccurs()
            .doOnNext { binding.progressBar.visibility = View.GONE }
            .subscribe { error ->
                Toast.makeText(requireContext(), error.localizedMessage, Toast.LENGTH_LONG).show()
            }
            .addSubscriptionTo(this)

        binding.recyclerView
            .scrollChangeEvents()
            .map { binding.recyclerView }
            .filter { recyclerView ->
                !recyclerView.canScrollVertically(1)
                        && !homeViewModel.isLoading
            }
            .subscribe {
                homeViewModel.getBreeds()
            }
            .addSubscriptionTo(this)

        binding.listGridFab
            .clicks()
            .doOnSubscribe { setIsGridMode(homeViewModel.isGridMode) }
            .subscribe { setIsGridMode(!homeViewModel.isGridMode) }
            .addSubscriptionTo(this)

        Observable.merge(listAdapter.clicks(), gridAdapter.clicks())
            .map { breed ->
                HomeFragmentDirections.actionNavigationHomeToNavigationDetails(breed)
            }
            .subscribe { action ->
                findNavController().navigate(action)
            }
            .addSubscriptionTo(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.layoutManager = null
        _binding = null
    }

    private fun setIsGridMode(isGridMode: Boolean) {
        homeViewModel.isGridMode = isGridMode
        if (isGridMode) {
            binding.listGridFab.setImageResource(R.drawable.ic_baseline_view_list_24)
            binding.recyclerView.adapter = gridAdapter
            binding.recyclerView.layoutManager = gridLayoutManager
        } else {
            binding.listGridFab.setImageResource(R.drawable.ic_baseline_grid_on_24)
            binding.recyclerView.adapter = listAdapter
            binding.recyclerView.layoutManager = linearLayoutManager
        }
    }
}