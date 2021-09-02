package noshair.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
   /* private lateinit var result: EditText
    private lateinit var valueget: EditText
    private val displayOperation by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.textView) }
*/
    //variables for holding value
    private var operand1: Double? = null
    private var pendingOperator:String? = "="
    val TEXT_CONTENTS = "Noshair"
    var state="state"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       /* result = findViewById(R.id.editTextNumberSigned2)
        valueget = findViewById(R.id.editTextNumberSigned)*/

        //Data Input Buttons
      /*  val button0 = findViewById<Button>(R.id.button10)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)
        val buttonDot = findViewById<Button>(R.id.buttonDot)
        //Operational Buttons
        val buttonPlus = findViewById<Button>(R.id.buttonplus)
        val buttonMinus = findViewById<Button>(R.id.buttonminus)
        val buttonMultiple = findViewById<Button>(R.id.buttonmiltiple)
        val buttonDivide = findViewById<Button>(R.id.buttondivide)
        val buttonEqual = findViewById<Button>(R.id.buttonequal)*/
        clear.setOnClickListener(View.OnClickListener {
            operand1=null
            pendingOperator=null
            editTextNumberSigned.setText("")
            editTextNumberSigned2.setText("")
            textView.setText("")
        })
        val listenerbotton = View.OnClickListener { v ->
            val selectedButton = v as Button
            editTextNumberSigned.append(selectedButton.text)
        }
        //set the listenser for operational buttons
        button10.setOnClickListener(listenerbotton)
        button1.setOnClickListener(listenerbotton)
        button2.setOnClickListener(listenerbotton)
        button3.setOnClickListener(listenerbotton)
        button4.setOnClickListener(listenerbotton)
        button5.setOnClickListener(listenerbotton)
        button6.setOnClickListener(listenerbotton)
        button7.setOnClickListener(listenerbotton)
        button8.setOnClickListener(listenerbotton)
        button9.setOnClickListener(listenerbotton)
        buttonDot.setOnClickListener(listenerbotton)
        val listeneroperation = View.OnClickListener { v ->
            val selectedButton = (v as Button).text.toString()
            try {
                val value = editTextNumberSigned.text.toString().toDouble()
                if (value != null) {
                    performOperation(value, selectedButton)
                }
            } catch (e: NumberFormatException) {
                editTextNumberSigned.setText("")
            }
            pendingOperator = selectedButton
            textView.text = pendingOperator
        }
        //opeartions
        buttonplus.setOnClickListener(listeneroperation)
        buttonminus.setOnClickListener(listeneroperation)
        buttonmiltiple.setOnClickListener(listeneroperation)
        buttondivide.setOnClickListener(listeneroperation)
        buttonequal.setOnClickListener(listeneroperation)
    }

    private fun performOperation(value: Double, selectedButton: String) {
        textView.text = selectedButton
        if (operand1 == null) {
            operand1 = value.toDouble()
        } else {
            if (pendingOperator == "=") {
                pendingOperator = selectedButton
            }
            when (pendingOperator) {
                "=" -> operand1 = value
                "/" -> if (value == 0.0) {
                    operand1 = Double.NaN
                } else {
                    operand1 = operand1!! / value
                }
                "*" -> operand1 = operand1!! * value
                "-" -> operand1 = operand1!! - value
                "+" -> operand1 = operand1!! + value
            }
        }
        editTextNumberSigned2.setText(operand1.toString())
        editTextNumberSigned.setText("")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if(savedInstanceState.getBoolean(state,false))
        operand1 = savedInstanceState.getDouble(TEXT_CONTENTS)
        pendingOperator = savedInstanceState?.getString("n")
        textView.text = pendingOperator
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (operand1 != null) {
            outState.putDouble(TEXT_CONTENTS, operand1!!)
            outState.putBoolean(state,true)
        }
        else{
            operand1=null
        }
        outState.putString("n", pendingOperator)
    }
}