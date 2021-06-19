package com.mertfatih.kotlincountries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mertfatih.kotlincountries.R
import com.mertfatih.kotlincountries.model.Country
import com.mertfatih.kotlincountries.util.downloadFromURL
import com.mertfatih.kotlincountries.util.placeholderProgressBar
import com.mertfatih.kotlincountries.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.item_country.view.*
import java.util.ArrayList

class CountryAdapter(
    private var countryList: ArrayList<Country>
): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {


    class CountryViewHolder(var view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_country,parent,false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int = countryList.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.country_name.text = countryList[position].countryName
        holder.view.country_region.text = countryList[position].countryRegion

        holder.view.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment()
            Navigation.findNavController(it).navigate(action)
        }

        holder.view.imageView.downloadFromURL(countryList[position].imageUrl, placeholderProgressBar(holder.view.context))
    }

    fun updateCountryList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }




}