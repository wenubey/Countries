package com.mertfatih.kotlincountries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mertfatih.kotlincountries.R
import com.mertfatih.kotlincountries.databinding.ItemCountryBinding
import com.mertfatih.kotlincountries.model.Country
import com.mertfatih.kotlincountries.util.downloadFromURL
import com.mertfatih.kotlincountries.util.placeholderProgressBar
import com.mertfatih.kotlincountries.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.item_country.view.*
import java.util.ArrayList

class CountryAdapter(private var countryList: ArrayList<Country>): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(), CountryClickListener {


    class CountryViewHolder(var view: ItemCountryBinding): RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemCountryBinding>(inflater, R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int = countryList.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.country = countryList[position]
        holder.view.listener = this
    }

    fun updateCountryList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClicked(v: View) {
        val uuid = v.countryUuidText.text.toString().toInt()
        val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }


}