package com.demo.app.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.app.databinding.ItemCurrencyBinding
import com.demo.app.domain.models.CurrencyResponseUiModel
import com.demo.app.presentation.extensions.layoutInflater

class CurrenciesAdapter : ListAdapter<CurrencyResponseUiModel,
        RecyclerView.ViewHolder>(diffUtilItemCallback) {

    var itemCallback: ((CurrencyResponseUiModel) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val itemView = ItemCurrencyBinding.inflate(parent.layoutInflater, parent, false)
        return ViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        getItem(position)?.let { (holder as ViewHolder).bindItem(it) }
    }

    inner class ViewHolder(private val binding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(currencyModel: CurrencyResponseUiModel) =
            with(binding) {
                tvTitle.text = currencyModel.symbol
                tvPriceChange.text = currencyModel.priceChange

                itemView.setOnClickListener {
                    itemCallback?.invoke(currencyModel)
                }
            }
    }

    companion object {
        val diffUtilItemCallback = object : DiffUtil.ItemCallback<
                CurrencyResponseUiModel>() {
            override fun areItemsTheSame(
                oldItem: CurrencyResponseUiModel,
                newItem: CurrencyResponseUiModel
            ): Boolean = oldItem.symbol == newItem.symbol

            override fun areContentsTheSame(
                oldItem: CurrencyResponseUiModel,
                newItem: CurrencyResponseUiModel
            ): Boolean = oldItem == newItem
        }
    }
}