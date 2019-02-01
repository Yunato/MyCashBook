package io.github.yunato.mycashbook.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.github.yunato.mycashbook.R
import io.github.yunato.mycashbook.model.dto.Record
import io.github.yunato.mycashbook.ui.fragment.RecordListFragment.OnSelectListener
import java.text.SimpleDateFormat
import java.util.*

class MyRecordRecyclerViewAdapter(private val mValues: List<Record>, private val mListener: OnSelectListener?) : RecyclerView.Adapter<MyRecordRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_record_item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        val sdf: SimpleDateFormat = SimpleDateFormat("yy/MM/dd", Locale.JAPAN)
        val date: Date = Date()
        date.time = mValues[position].date
        holder.mDateView.text = sdf.format(date)
        holder.mContentView.text = mValues[position].content
        holder.mFluctuationView.text = mValues[position].fluctuation
        val strMoney: String = if(mValues[position].fluctuation.contentEquals(
                holder.mView.context.getString(R.string.plus_button))){
            "+${mValues[position].money}円"
        }else{
            "-${mValues[position].money}円"
        }
        holder.mMoneyView.text = strMoney

        holder.mView.setOnClickListener {
            mListener?.onSelect(mValues[position])
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mDateView: TextView = mView.findViewById(R.id.date_text_view) as TextView
        val mContentView: TextView = mView.findViewById(R.id.content_text_view) as TextView
        val mFluctuationView: TextView = mView.findViewById(R.id.fluctuation_text_view) as TextView
        val mMoneyView: TextView = mView.findViewById(R.id.money_text_view) as TextView
        var mItem: Record? = null
    }
}
