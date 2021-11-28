package com.example.simplecalculator

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.simplecalculator.databinding.FragmentMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_main, container, false)
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        Dot

        binding.textViewDot.setOnClickListener { setDotButton(".") }

//        Numbers

        binding.textViewOh.setOnClickListener { setNumbers("0") }
        binding.textViewOne.setOnClickListener { setNumbers("1") }
        binding.textViewTwo.setOnClickListener { setNumbers("2") }
        binding.textViewTree.setOnClickListener { setNumbers("3") }
        binding.textViewFour.setOnClickListener { setNumbers("4") }
        binding.textViewFive.setOnClickListener { setNumbers("5") }
        binding.textViewSix.setOnClickListener { setNumbers("6") }
        binding.textViewSeven.setOnClickListener { setNumbers("7") }
        binding.textViewEight.setOnClickListener { setNumbers("8") }
        binding.textViewNine.setOnClickListener { setNumbers("9") }

//        Operators

        binding.textViewPlus.setOnClickListener { setOperator("+") }
        binding.textViewMinus.setOnClickListener { setOperator("-") }
        binding.textViewDivision.setOnClickListener { setOperator("/") }
        binding.textViewMultiplication.setOnClickListener { setOperator("*") }

//        Brackets

        binding.textViewBracketOpen.setOnClickListener { setBrackets("(") }
        binding.textViewBracketClose.setOnClickListener { setBrackets(")") }

//        Clear
        binding.textViewClear.setOnClickListener {
            binding.textViewExpression.text = ""
            binding.textViewResult.text = ""
        }

//        ClearLastElement

        binding.textViewBack.setOnClickListener { clearLastElement() }

//        Equal

        binding.textViewEqual.setOnClickListener {
            try {
                val ex = ExpressionBuilder(binding.textViewExpression.text.toString()).build()
                val result = ex.evaluate()

                val longRes = result.toLong()
                if (result == longRes.toDouble()) {
                    binding.textViewResult.text = longRes.toString()
                } else {
                    binding.textViewResult.text = result.toString()

                }
            } catch (e: Exception) {
                Log.d("Error", "message: ${e.message}")
            }
        }

    }

    private fun setDotButton(textDot: String) {
        val text = binding.textViewExpression.text.toString()
        val lastElement = text.lastOrNull().toString()

        if (text == "") {
            binding.textViewExpression.append(0.toString())
        }

        if (textDot == lastElement) {
            clearLastElement()
            binding.textViewExpression.append(textDot)
        } else {
            binding.textViewExpression.append(textDot)
        }


    }

    private fun setNumbers(textNumbers: String) {


        binding
            .textViewExpression
            .append(textNumbers)
    }

    private fun setOperator(operator: String) {
        val list = listOf("+", "-", "*", "/", ".")
        val text = binding.textViewExpression.text.toString()


        val lastElement = text.lastOrNull().toString()

        if (binding.textViewResult.text != "") {
            binding.textViewExpression.text = binding.textViewResult.text
            binding.textViewResult.text = ""
        }

        if (list.any { it == lastElement }) {
            clearLastElement()
            binding.textViewExpression.append(operator)
        } else {
            binding.textViewExpression.append(operator)
        }
    }

    private fun setBrackets(textBracket: String) {
        val text = binding.textViewExpression.text.toString()
        val lastElement = text.lastOrNull().toString()
        val listOfBrackets = listOf("(", ")")

        if (listOfBrackets.any { it == lastElement }) {
            clearLastElement()
            binding.textViewExpression.append(textBracket)
        } else binding.textViewExpression.append(textBracket)
    }

    private fun clearLastElement() {
        val text = binding.textViewExpression.text.toString()
        if (text != "") {
            binding.textViewExpression.text = text.substring(0, text.length - 1)
        }
    }


}