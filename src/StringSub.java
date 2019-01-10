/*
Credit Karma Coding Challenge:
Write a function to subtract 2 number strings.

Examples:
"1" - "100" = "-99"
"102" - "11" = "91"
"1234578901234567891" - "1234578901234567890" = "1"
"1234578901234567890" - "1234578901234567891" = "-1"
"491" - "491" = "0"

 1230
-0456

Requirement:
DO NOT use Integer.parseInt(String input). The input can be very large number string and may overflow/underflow.
DO NOT use BigInteger or BigDecimal.

Code snippet:
public static String subtract( String num1, String num2) {
  // Your implementation here
}

*/

import java.io.*;
import java.util.*;

class StringSub {
    public static boolean isNegative(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        if (s2.length() > s1.length()) {
            return true;
        }
        for (int i = 0; i < s1.length(); i++) {
            int val1 = s1.charAt(i) - '0';
            int val2 = s2.charAt(i) - '0';
            if (val2 < val1) {
                return false;
            }
            if (val2 > val1) {
                return true;
            }
        }
        return false;
    }

    public static int getLastDigit(StringBuilder sb) {
        int result = sb.charAt(sb.length() - 1) - '0';
        sb.delete(sb.length() - 1, sb.length());
        return result;
    }

    public static String subtractPositive(String num1, String num2) {
        StringBuilder result = new StringBuilder();
        StringBuilder sb1 = new StringBuilder(num1);
        StringBuilder sb2 = new StringBuilder(num2);
        int carry = 0;
        while (sb2.length() > 0) {
            int digit1 = getLastDigit(sb1) - carry;
            int digit2 = getLastDigit(sb2);
            carry = 0;
            if (digit1 < digit2) {
                result.append(digit1 + 10 - digit2);
                carry++;
            } else {
                result.append(digit1 - digit2);
            }
        }
        while (sb1.length() > 0) {
            int digit = getLastDigit(sb1) - carry;
            carry = 0;
            if (digit < 0) {
                result.append(digit + 10);
                carry++;
            } else {
                result.append(digit);
            }
        }
        StringBuilder reversed = new StringBuilder();
        int index = result.length() - 1;
        for (; index >= 0; index--) {
            if (result.charAt(index) != '0') {
                break;
            }
        }
        for (; index >= 0; index--) {
            reversed.append(result.charAt(index));
        }
        return reversed.toString();
    }

    public static String subtract(String s1, String s2) {
        if (isNegative(s1, s2)) {
            return "-" + subtractPositive(s2, s1);
        } else {
            return subtractPositive(s1, s2);
        }
    }

    public static void main(String[] args) {
        String s1 = "1234578901234567891";
        String s2 = "1234578901234567890";

        System.out.println(subtract(s1, s2));
    }
}
