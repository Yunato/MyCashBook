package io.github.yunato.mycashbook.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.yunato.mycashbook.R
import kotlinx.android.synthetic.main.activity_money.*

class MoneyActivity : AppCompatActivity() {

    var content: String = ""

    companion object {
        @JvmField
        val EXTRA_MONEY: String = "EXTRA_MOENY"

        fun intent(context: Context): Intent =
                Intent(context, MoneyActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_money)

        decide_button.setOnClickListener {
            if (money_edit_text.text.toString().isBlank()) {
                money_text_layout.isErrorEnabled = true
                money_text_layout.error = "未入力です．"
            } else{
                val number = money_edit_text.text.toString().replace(",", "").toLong()
                val intent: Intent = Intent()
                intent.putExtra(EXTRA_MONEY, number)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
}
