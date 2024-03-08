package com.example.aviatickets.fragment

import android.os.Bundle
import android.telecom.Call
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aviatickets.R
import com.example.aviatickets.adapter.OfferListAdapter
import com.example.aviatickets.databinding.FragmentOfferListBinding
import com.example.aviatickets.model.service.FakeService


class OfferListFragment : Fragment() {

    companion object {
        fun newInstance() = OfferListFragment()
    }

    private var _binding: FragmentOfferListBinding? = null
    private val binding
        get() = _binding!!

    private val adapter: OfferListAdapter by lazy {
        OfferListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOfferListBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        adapter.setItems(FakeService.offerList)
    }

    private fun setupUI() {
        with(binding) {
            offerList.adapter = adapter

            sortRadioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.sort_by_price -> {
                        /**
                         * implement sorting by price
                         */
                    }

                    R.id.sort_by_duration -> {
                        /**
                         * implement sorting by duration
                         */
                    }
                }
            }
        }
    }
}
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val service = ApiClient.createService()
    service.getOffers().enqueue(object : Callback<List<Offer>> {
        override fun onResponse(call: Call<List<Offer>>, response: Response<List<Offer>>) {
            if (response.isSuccessful) {
                val offers = response.body()
                adapter.setItems(offers ?: emptyList())
            } else {
                // Обработка ошибки
            }
        }

        override fun onFailure(call: Call<List<Offer>>, t: Throwable) {
            // Обработка ошибки
        }
    })
    call.enqueue(object : retrofit2.Callback<List<Offer>> {
        override fun onResponse(call: Call<List<Offer>>, response: Response<List<Offer>>) {
            TODO("Not yet implemented")
        }

        override fun onFailure(call: Call<List<Offer>>, t: Throwable) {
            TODO("Not yet implemented")
        }

    })  }
}
