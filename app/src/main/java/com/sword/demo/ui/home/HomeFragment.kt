package com.sword.demo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sword.demo.R
import com.sword.demo.base.BaseFragment
import com.sword.demo.base.BasePaginationScrollListener
import com.sword.demo.databinding.FragmentHomeBinding
import com.sword.demo.extensions.addSubscriptionTo
import com.sword.demo.ui.home.adapter.BreedGridItemAdapter
import com.sword.demo.ui.home.adapter.BreedListItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private val homeViewModel: HomeViewModel by hiltNavGraphViewModels(R.id.mobile_navigation)
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @Inject
    lateinit var listAdapter: BreedListItemAdapter

    @Inject
    lateinit var gridAdapter: BreedGridItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = listAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.breedsChanges()
            .doOnSubscribe { binding.progressBar.visibility = View.VISIBLE }
            .doOnNext { binding.progressBar.visibility = View.GONE }
            .subscribe { items ->
                listAdapter.add(items)
            }
            .addSubscriptionTo(this)

        homeViewModel.errorOccurs()
            .subscribe { error ->
                Toast.makeText(requireContext(), error.localizedMessage, Toast.LENGTH_LONG).show()
            }
            .addSubscriptionTo(this)

        binding.recyclerView.addOnScrollListener(object :
            BasePaginationScrollListener(binding.recyclerView.layoutManager) {
            override fun loadMoreItems() {
                homeViewModel.getBreeds()
            }

            override val isLoading: Boolean
                get() = homeViewModel.isLoading
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}