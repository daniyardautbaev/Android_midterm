package com.example.aviatickets.adapter;

public class DiffUtil {
    public class OffersAdapter : RecyclerView.Adapter<OffersAdapter.OfferViewHolder>() {
        private val differ = AsyncListDiffer(this, OfferDiffCallback())

        getItemCount(): Int = differ.currentList.size

        override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
            val offer = differ.currentList[position]
            // Привязка данных к ViewHolder
        }

        fun submitList(list: List<Offer>) = differ.submitList(list)

        // Остальные методы адаптера (создание ViewHolder и т.д.)
    }

}
