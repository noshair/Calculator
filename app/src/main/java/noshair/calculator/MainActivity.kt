package noshair.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    private lateinit var result: EditText
    private lateinit var valueget: EditText
    private val displayOperation by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.textView) }

    //variables for holding value
    private var operand1: Double? = null
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
        val buttonEqual=findViewById<Button>(R.id.buttonequal)

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
                try{
                    val value =valueget.text.toString().toDouble()

                    if(value!=null){

                        performOperation(value,selectedButton)}}
                catch (e :NumberFormatException){
                    valueget.setText("")
                }

            pendingOperator =selectedButton
            displayOperation.text=pendingOperator
        }
        //opeartions
        buttonPlus.setOnClickListener(listeneroperation)
        buttonMinus.setOnClickListener(listeneroperation)
        buttonMultiple.setOnClickListener(listeneroperation)
        buttonDivide.setOnClickListener(listeneroperation)
        buttonEqual.setOnClickListener(listeneroperation)
    }

    private fun performOperation(value: Double, selectedButton: String) {
        displayOperation.text=selectedButton
        if(operand1==null){
            operand1=value.toDouble()
        }
        else{
            if (pendingOperator=="="){
                pendingOperator=selectedButton
            }
            when(pendingOperator){
                "=" -> operand1=value
                "/" -> if (value==0.0){
                    operand1= Double.NaN
                }
                else{
                    operand1=operand1!! /value
                }
                "*" -> operand1=operand1!! * value
                "-" -> operand1=operand1!! - value
                "+" -> operand1=operand1!! + value
            }
        }
        result.setText(operand1.toString())
        valueget.setText("")
    }
}