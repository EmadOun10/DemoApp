package com.demo.app.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.demo.app.databinding.ActivityMainBinding
import com.demo.app.presentation.adapter.CurrenciesAdapter
import com.demo.app.presentation.resource.ResourceState
import com.demo.app.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    private val currenciesAdapter: CurrenciesAdapter by lazy { CurrenciesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewBindingWithActivityView()
        setUpRecyclerView()
        viewModel.getCurrenciesList()
//        viewModel.getFact()
        observeData()
    }

    private fun setupViewBindingWithActivityView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setUpRecyclerView() {
        binding.rvCurrencies.adapter = currenciesAdapter
        currenciesAdapter.itemCallback = viewModel::callFactApi
    }

    private fun observeData() {
        viewModel.currenciesObserver.observe(this) { resource ->
            when (resource.state) {
                ResourceState.LOADING -> {
                  binding.progress.isVisible = true
                }
                ResourceState.SUCCESS -> {
                    binding.progress.isVisible = false
                    currenciesAdapter.submitList(resource.data)
                }
                ResourceState.ERROR -> {
                    binding.progress.isVisible = false
                    Toast.makeText(this, resource.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.factObserver.observe(this) { resource ->
            when (resource.state) {
                ResourceState.LOADING -> {
                    binding.progress.isVisible = true
                }
                ResourceState.SUCCESS -> {
                    binding.progress.isVisible = false
                    Toast.makeText(this, resource.data?.fact, Toast.LENGTH_SHORT).show()
                }
                ResourceState.ERROR -> {
                    binding.progress.isVisible = false
                    Toast.makeText(this, resource.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}