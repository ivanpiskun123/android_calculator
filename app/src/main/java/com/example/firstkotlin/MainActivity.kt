package com.example.firstkotlin

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.math.RoundingMode
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {
    var digitOnScreenStr = StringBuilder()
    var operat : Char = ' '
    var leftDigit: Double = 0.0
    var rightDigit: Double = 0.0
    var isOperationWritten: Boolean=true
    var lastLeftDigit: Double=0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        outView.text = "0"
        initializeButtons()

        outView.text = intent.getStringExtra("DIG_ON_SCREEN")

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)



        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){

            var intent = Intent(this, MainActivityLand::class.java);
            intent.putExtra("DIG_ON_SCREEN", outView.text);
            startActivity(intent);
        }


    }

    private fun initializeButtons() {

        funcButtons()
        operationalOneDigitsButtons()
        operationalTwoDigitsButtons()
        numButtons()
    }

    private fun numButtons() {

        btn_0.setOnClickListener {
            appendToScreen("0")
        }

        btn_1.setOnClickListener {
            appendToScreen("1")
        }

        btn_2.setOnClickListener {
            appendToScreen("2")
        }

        btn_3.setOnClickListener {
            appendToScreen("3")
        }

        btn_4.setOnClickListener {
            appendToScreen("4")
        }

        btn_5.setOnClickListener {
            appendToScreen("5")
        }

        btn_6.setOnClickListener {
            appendToScreen("6")
        }

        btn_7.setOnClickListener {
            appendToScreen("7")
        }

        btn_8.setOnClickListener {
            appendToScreen("8")
        }

        btn_9.setOnClickListener {
            appendToScreen("9")
        }

        btn_dot.setOnClickListener {

            if( outView.text != "" && !outView.text.contains(".")  ){
                appendToScreen(".")
            }
        }

    }


    private fun appendToScreen(digit: String) {
        digitOnScreenStr.append(digit)
        outView.text = digitOnScreenStr.toString()
    }

    private fun operationalTwoDigitsButtons() {

        btn_plus.setOnClickListener {
            setOperation('+')
        }

        btn_minus.setOnClickListener {
            setOperation('-')
        }

        btn_multi.setOnClickListener {
            setOperation('*')
        }

        btn_devide.setOnClickListener {
            setOperation('/')
        }

    }

    private fun operationalOneDigitsButtons() {
        val df = DecimalFormat("#.###")
        df.roundingMode = RoundingMode.CEILING

        btn_square.setOnClickListener {
            var sqrt = Math.pow(outView.text.toString().toDouble(), 2.0)
            var devStr =  df.format(sqrt).toString().replace(',', '.')
            outView.text = devStr
            digitOnScreenStr.clear()
            digitOnScreenStr.append(devStr.toDouble())
            isOperationWritten = true
            leftDigit = digitOnScreenStr.toString().toDouble()
        }
        btn_rotate.setOnClickListener {
            var rot =  1/outView.text.toString().toDouble()
            var devStr =  df.format(rot).toString().replace(',', '.')
            outView.text = devStr
            digitOnScreenStr.clear()
            digitOnScreenStr.append(devStr.toDouble())
            isOperationWritten = true
            leftDigit = digitOnScreenStr.toString().toDouble()
        }
        btn_sq_root.setOnClickListener {
            if (outView.text.toString().toDouble() != 0.0){
                var sq_r =  Math.sqrt(outView.text.toString().toDouble())
                var devStr =  df.format(sq_r).toString().replace(',', '.')
                outView.text = devStr
                digitOnScreenStr.clear()
                digitOnScreenStr.append(devStr.toDouble())
                isOperationWritten = true
                leftDigit = digitOnScreenStr.toString().toDouble()
            }
            else {
                outView.text = "Ошибка"
            }
        }
        btn_sign.setOnClickListener {
        var resigned = -1 * outView.text.toString().toDouble()
        outView.text = resigned.toString()
            digitOnScreenStr.clear()
        digitOnScreenStr.append(resigned)
            isOperationWritten = true
            leftDigit = digitOnScreenStr.toString().toDouble()
        }

    }

    private fun setOperation(c: Char) {
        operat = c
        isOperationWritten = true
        leftDigit = digitOnScreenStr.toString().toDouble()
        digitOnScreenStr.clear()
        outView.text = ""
    }


    private fun funcButtons() {

        btn_clear_all.setOnClickListener {
            digitOnScreenStr.clear()
            outView.text="0"
        }

        btn_clear.setOnClickListener {
            if(outView.text!="") {
                val length = digitOnScreenStr.length
                digitOnScreenStr.deleteCharAt(length - 1)
                outView.text = digitOnScreenStr.toString()
            }
        }

        btn_equal.setOnClickListener {

            performOperationTwoDigit()
        }

    }

    private fun performOperationTwoDigit() {
        if (digitOnScreenStr.toString()!=""){

                //  equal was pressed again
            if(!isOperationWritten)
            {
                leftDigit = outView.text.toString().toDouble()
            }
            else{
                rightDigit = outView.text.toString().toDouble()
            }

                digitOnScreenStr.clear()
                val df = DecimalFormat("#.###")
                df.roundingMode = RoundingMode.CEILING

                when (operat) {
                    '+' -> {

                        var sum = leftDigit.toDouble() + rightDigit.toDouble()
                        outView.text = sum.toString()
                        digitOnScreenStr.append(sum)
                    }
                    '-' -> {
                        var subtract = leftDigit.toDouble() - rightDigit.toDouble()
                        outView.text = subtract.toString()
                        digitOnScreenStr.append(subtract)
                    }
                    '*' -> {
                        var multi = leftDigit.toDouble() * rightDigit.toDouble()
                        outView.text = multi.toString()
                        digitOnScreenStr.append(multi)
                    }
                    '/' -> {
                        if (rightDigit != 0.0) {
                            var devide = leftDigit.toDouble() / rightDigit.toDouble()
                            var devStr = df.format(devide).toString().replace(',', '.')
                            outView.text = devStr
                            digitOnScreenStr.append(devStr.toDouble())
                        } else {
                            outView.text = "Ошибка"
                        }


                    }

                }

            }
                else{
                    outView.text ="Ошибка"
                }


        isOperationWritten = false
        leftDigit = (digitOnScreenStr.toString()).toDouble()
        lastLeftDigit = leftDigit
    }



}