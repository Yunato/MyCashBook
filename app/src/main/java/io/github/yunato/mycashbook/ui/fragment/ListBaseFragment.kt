package io.github.yunato.mycashbook.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.yunato.mycashbook.R
import kotlinx.android.synthetic.main.fragment_list_base.*

class ListBaseFragment : Fragment() {

    private var strMoney: String = "0"

    companion object {

        private val ARGV_MONEY = "ARGV_MONEY"

        fun newInstance(money: Long): ListBaseFragment {
            val fragment: ListBaseFragment = ListBaseFragment()
            val args: Bundle = Bundle()
            args.putLong(ARGV_MONEY, money)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null){
            strMoney = "${arguments.getLong(ARGV_MONEY)}"
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_list_base, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        money_text_view.text = "${strMoney} 円"
        val fragment: RecordListFragment = RecordListFragment()
        childFragmentManager.beginTransaction().replace(R.id.container_fragment, fragment).commit()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }
}
