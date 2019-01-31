package io.github.yunato.mycashbook.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.yunato.mycashbook.R
import io.github.yunato.mycashbook.model.dto.Record
import io.github.yunato.mycashbook.ui.fragment.RecordListFragment.OnSelectListener

class MyRecordRecyclerViewAdapter(private val mValues: List<Record>, private val mListener: OnSelectListener?) : RecyclerView.Adapter<MyRecordRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_record_item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
//        holder.mIdView.text = mValues[position].id
//        holder.mContentView.text = mValues[position].content

        holder.mView.setOnClickListener {
            mListener?.onSelect(mValues[position])
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
//        val mIdView: TextView
//        val mContentView: TextView
        var mItem: Record? = null

        init {
//            mIdView = mView.findViewById(R.id.id) as TextView
//            mContentView = mView.findViewById(R.id.content) as TextView
        }
    }
}
