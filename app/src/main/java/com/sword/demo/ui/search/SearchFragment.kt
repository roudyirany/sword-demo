package com.sword.demo.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.jakewharton.rxbinding4.widget.queryTextChanges
import com.sword.demo.R
import com.sword.demo.base.BaseFragment
import com.sword.demo.databinding.FragmentSearchBinding
import com.sword.demo.extensions.addSubscriptionTo
import com.sword.demo.ui.search.adapter.BreedSearchItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : BaseFragment() {

    private val searchViewModel: SearchViewModel by hiltNavGraphViewModels(R.id.mobile_navigation)
    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @Inject
    lateinit var adapter: BreedSearchItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchView
            .queryTextChanges()
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { name ->
                searchViewModel.searchBreed(name)
            }
            .subscribe({ items ->
                adapter.update(items)
            }, { error ->
                Toast.makeText(requireContext(), error.localizedMessage, Toast.LENGTH_LONG).show()
            })
            .addSubscriptionTo(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}