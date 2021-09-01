package noshair.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var result: EditText
    private lateinit var valueget: EditText
    private val displayOperation by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.textView) }

    //variables for holding value
    private var operand1: Double? = null
    private var operand2: Double = 0.0
    private var pendingOperator = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        result=findViewById(R.id.editTextNumberSigned2)
        valueget=findViewById(R.id.editTextNumberSigned)

        //Data Input Buttons
        val button0=findViewById<Button>(R.id.button10)
        val button1=findViewById<Button>(R.id.button1)
        val button2=findViewById<Button>(R.id.button2)
        val button3=findViewById<Button>(R.id.button3)
        val button4=findViewById<Button>(R.id.button4)
        val button5=findViewById<Button>(R.id.button5)
        val button6=findViewById<Button>(R.id.button6)
        val button7=findViewById<Button>(R.id.button7)
        val button8=findViewById<Button>(R.id.button8)
        val button9=findViewById<Button>(R.id.button9)
        val buttonDot=findViewById<Button>(R.id.buttonDot)

        //Operational Buttons
        val buttonPlus=findViewById<Button>(R.id.buttonplus)
        val buttonMinus=findViewById<Button>(R.id.buttonminus)
        val buttonMultiple=findViewById<Button>(R.id.buttonmiltiple)
        val buttonDivide=findViewById<Button>(R.id.buttondivide)

        val listenerbotton= View.OnClickListener { v->
            val selectedButton=v as Button

           valueget.append(selectedButton.text)
        }
        //set the listenser for operational buttons
        button0.setOnClickListener(listenerbotton)
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
        val listeneroperation= View.OnClickListener { v->
            val selectedButton=(v as Button).text.toString()
            val value =valueget.text.toString()
            if(value.isNotEmpty()){
            performOperation(value,selectedButton)
            }
            pendingOperator =selectedButton
            displayOperation.text=pendingOperator
        }
    }

    private fun performOperation(value: String, selectedButton: String) {
        displayOperation.text=selectedButton
    }

}