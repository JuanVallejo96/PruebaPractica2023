package com.example.pruebapractica

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(myDataset: List<OfferModel>, listener: OnListInteractionListener?) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private var mDataset: List<OfferModel>

    interface OnListInteractionListener {
        fun onListInteraction(url: String?)
    }

    var mListener: OnListInteractionListener?

    class MyViewHolder(var mView: View) : RecyclerView.ViewHolder(
        mView
    ) {
        // each data item is just a string in this case
        var titleTv: TextView
        var normalPriceTv: TextView
        var dealPriceTv: TextView

        init {
            titleTv = mView.findViewById(R.id.gameTitle)
            normalPriceTv = mView.findViewById(R.id.normalPrice)
            dealPriceTv = mView.findViewById(R.id.dealPrice)
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    init {
        mDataset = myDataset
        mListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        // create a new view
        // Create new views (invoked by the layout manager)
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_item, parent, false)
        return MyViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.titleTv.text = mDataset[position].title
        holder.normalPriceTv.text = mDataset[position].normalPrice
        holder.dealPriceTv.text = mDataset[position].salePrice
        holder.mView.setOnClickListener {
            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener!!.onListInteraction(mDataset[position].dealID)
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return mDataset.size
    }

    fun swap(dataset: List<OfferModel>) {
        mDataset = dataset
        notifyDataSetChanged()
    }
}