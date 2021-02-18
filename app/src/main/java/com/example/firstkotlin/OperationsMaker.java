package com.example.firstkotlin;

import net.objecthunter.exp4j.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class OperationsMaker {

        private NumberFormat decFormat;

    public OperationsMaker(){
            decFormat = new DecimalFormat("#.###");
        decFormat.setRoundingMode(RoundingMode.CEILING);
        }

       public String calculate(String strExp){
            String resStr = "";
            try {
                Expression e = new ExpressionBuilder(strExp).build();
                double result = e.evaluate();
                resStr = restrictLengthDouble(result);
            }
            catch (Exception e)
            {
                resStr =  "Error";
            }
            return resStr;
       }

       private String restrictLengthDouble(Double result){
           String answerStr = decFormat.format(result).toString().replace(',', '.');
           return answerStr;
       }

}
