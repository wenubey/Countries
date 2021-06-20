package com.mertfatih.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mertfatih.kotlincountries.R
import com.mertfatih.kotlincountries.adapter.CountryAdapter
import com.mertfatih.kotlincountries.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*


class FeedFragment : Fragment() {

    private lateinit var viewModel: FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        countryList.layoutManager = LinearLayoutManager(context)
        countryList.adapter = countryAdapter

        refresh_layout.setOnRefreshListener {
            countryList.visibility = View.GONE
            countryError.visibility = View.GONE
            country_loading.visibility = View.VISIBLE
            viewModel.refreshFromAPI()
            refresh_layout.isRefreshing = false
        }

        observerLiveData()
    }

    private fun observerLiveData() {
        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {
                countryList.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }

        })
        viewModel.countryError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) {
                    countryError.visibility = View.VISIBLE
                } else {
                    countryError.visibility = View.GONE
                }
            }

        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { load ->
            load?.let {
                if (it) {
                    country_loading.visibility = View.VISIBLE
                    countryList.visibility = View.GONE
                    countryError.visibility = View.GONE
                } else {
                    country_loading.visibility = View.GONE
                }
            }

        })
    }
}