package noshair.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import noshair.calculator.model.CalcukatorViewModel
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
   /* private lateinit var result: EditText
    private lateinit var valueget: EditText
    private val displayOperation by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.textView) }
*/
    //variables for holding value

    val TEXT_CONTENTS = "Noshair"
    var state="state"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel= ViewModelProviders.of(this).get(CalcukatorViewModel::class.java)
        viewModel.stringResult.observe(this, Observer<String> { stringResult -> editTextNumberSigned2.setText(stringResult)})
        viewModel.stringNewNumber.observe(this, Observer<String> { stringNumber -> editTextNumberSigned.setText(stringNumber) })
        viewModel.stringOperation.observe(this, Observer<String> { stringOperation -> textView.text = stringOperation})
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
        clear.setOnClickListener(View.OnClickListener { v ->
            viewModel.clearPressed((v as Button).text.toString())

        })
        val listenerbotton = View.OnClickListener { v ->
            viewModel.digitPressed((v as Button).text.toString())
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
            viewModel.opanentPressed((v as Button).text.toString())
        }
        //opeartions
        buttonplus.setOnClickListener(listeneroperation)
        buttonminus.setOnClickListener(listeneroperation)
        buttonmiltiple.setOnClickListener(listeneroperation)
        buttondivide.setOnClickListener(listeneroperation)
        buttonequal.setOnClickListener(listeneroperation)
    }



   /* override fun onRestoreInstanceState(savedInstanceState: Bundle) {
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
    }*/
}