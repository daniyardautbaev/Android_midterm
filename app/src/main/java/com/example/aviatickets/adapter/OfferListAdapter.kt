package com.example.aviatickets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aviatickets.R
import com.example.aviatickets.databinding.ItemOfferBinding
import com.example.aviatickets.model.entity.Offer

class OfferListAdapter : RecyclerView.Adapter<OfferListAdapter.ViewHolder>() {

    private val items: ArrayList<Offer> = arrayListOf()

    fun setItems(offerList: List<Offer>) {
        items.clear()
        items.addAll(offerList)
        notifyDataSetChanged()

        /**
         * think about recycler view optimization using diff.util
         */
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemOfferBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(
        private val binding: ItemOfferBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        fun bind(offer: Offer) {
            val flight = offer.flight

            with(binding) {
                departureTime.text = flight.departureTimeInfo
                arrivalTime.text = flight.arrivalTimeInfo
                route.text = context.getString(
                    R.string.route_fmt,
                    flight.departureLocation.code,
                    flight.arrivalLocation.code
                )
                duration.text = context.getString(
                    R.string.time_fmt,
                    getTimeFormat(flight.duration).first.toString(),
                    getTimeFormat(flight.duration).second.toString()
                )
                direct.text = context.getString(R.string.direct)
                price.text = context.getString(R.string.price_fmt, offer.price.toString())
            }
        }

        private fun getTimeFormat(minutes: Int): Pair<Int, Int> = Pair(
            first = minutes / 60,
            second = minutes % 60
        )

    }
    class OfferDiffCallback : DiffUtil.ItemCallback<Offer>() {
        override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            // Ваша логика для определения, являются ли объекты одним и тем же предложением
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            // Ваша логика для определения, изменились ли данные предложения
            return oldI tem == newItem
        }
    }
    class OfferAdapter : RecyclerView.Adapter<OfferViewHolder>() {
        private var offers: List<Offer> = listOf()

        fun updateOffers(newOffers: List<Offer>) {
            offers = newOffers
            notifyDataSetChanged() // Обновление RecyclerView
        }
        import androidx.recyclerview.widget.ListAdapter

        class OfferAdapter : ListAdapter<Offer, OfferViewHolder>(OfferDiffCallback()) {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
                // Create ViewHolder
            }

            override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
                val offer = getItem(position)
                // Bind data to ViewHolder
            }
            fun sortOffersByPrice(offers: List<Offer>): List<Offer> {
                return offers.sortedBy { it.price }
            }

            fun sortOffersByDuration(offers: List<Offer>): List<Offer> {
                return offers.sortedBy { it.duration }
            }

        }


    }


}