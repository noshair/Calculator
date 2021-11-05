package noshair.calculator.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.lang.NumberFormatException
import java.math.BigDecimal

class CalcukatorViewModel : ViewModel() {
   private val editTextNumberSigned2=MutableLiveData<String>()
    val stringResult: LiveData<String>
        get() = Transformations.map(editTextNumberSigned2) { it.toString()}

    private val editTextNumberSigned=MutableLiveData<String>()
    val stringNewNumber: LiveData<String>
        get() = editTextNumberSigned
   private val textView=MutableLiveData<String>()
    val stringOperation: LiveData<String>
        get() = textView
    private var operand1: BigDecimal? = null
    private var pendingOperator:String? = "="
    fun  opanentPressed( op: String){
            try {
                val value = editTextNumberSigned.value?.toBigDecimal()
                if (value != null) {
                    performOperation(value, op)
                }
            } catch (e: NumberFormatException) {
                editTextNumberSigned.value=""
            }
            pendingOperator = op
            textView.value = pendingOperator
    }
    fun digitPressed( caption: String) {
        if(editTextNumberSigned.value!=null){
            editTextNumberSigned.value=editTextNumberSigned.value+caption
        }
        else{
            editTextNumberSigned.value=caption
        }
    }
    fun clearPressed(caption: String){
        operand1=null
        pendingOperator=null
        editTextNumberSigned.value=""
        editTextNumberSigned2.value=""
        textView.value=""
    }
        private fun performOperation(value: BigDecimal, selectedButton: String) {
        if (operand1 == null) {
            operand1 = value
        } else {
            if (pendingOperator == "=") {
                pendingOperator = selectedButton
            }
            when (pendingOperator) {
                "=" -> operand1 = value
                "/" -> if (value == BigDecimal.valueOf(0.0)) {
                    operand1 = BigDecimal.valueOf(Double.NaN)
                } else {
                    operand1 = operand1!! / value
                }
                "*" -> operand1 = operand1!! * value
                "-" -> operand1 = operand1!! - value
                "+" -> operand1 = operand1!! + value
            }
        }
        editTextNumberSigned2.value=operand1.toString()
        editTextNumberSigned.value=""
    }

}