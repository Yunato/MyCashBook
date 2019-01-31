package io.github.yunato.mycashbook.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.yunato.mycashbook.R
import kotlinx.android.synthetic.main.fragment_input_record.*

class InputRecordFragment : Fragment() {

    private var mListener: OnSaveListener? = null
    private var content: String = ""

    companion object {
        fun newInstance(): InputRecordFragment {
            return InputRecordFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_input_record, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (view != null) {
            content = ""
            zero_button.setOnClickListener {
                addNumToFormula(0)
            }
            one_button.setOnClickListener {
                addNumToFormula(1)
            }
            two_button.setOnClickListener {
                addNumToFormula(2)
            }
            three_button.setOnClickListener {
                addNumToFormula(3)
            }
            four_button.setOnClickListener {
                addNumToFormula(4)
            }
            five_button.setOnClickListener {
                addNumToFormula(5)
            }
            six_button.setOnClickListener {
                addNumToFormula(6)
            }
            seven_button.setOnClickListener {
                addNumToFormula(7)
            }
            eight_button.setOnClickListener {
                addNumToFormula(8)
            }
            nine_button.setOnClickListener {
                addNumToFormula(9)
            }
            del_button.setOnClickListener {
                if (!content.isEmpty()) {
                    content = content.subSequence(0, content.lastIndex).toString()
                    if (!content.isEmpty()) {
                        formula_text_view.text = String.format("%,d", Integer.parseInt(content))
                    } else {
                        formula_text_view.text = ""
                    }
                }
            }
            del_button.setOnLongClickListener {
                content = ""
                formula_text_view.text = ""
                false
            }
        }
    }

    fun addNumToFormula(num: Int) {
        val numStr = num.toString()
        if (formula_text_view.text.length < 10) {
            content = "$content$numStr"
            formula_text_view.text = String.format("%,d", Integer.parseInt(content))
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnSaveListener) {
            mListener = context as OnSaveListener?
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnSaveListener {
        fun onSave()
    }
}
