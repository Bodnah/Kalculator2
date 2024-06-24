package task1;

import java.util.Scanner;

public class SimpleCalculator {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Программа калькулятора запущена.");
        while (true) {
            System.out.println("Введите данные для вычисления или 'exit' для выхода:");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Выход из программы.");
                break;
            }
            String result = calc(input);
            System.out.println("Результат вычислений: " + result);
        }
        scanner.close();
    }

    private static String calc(String inputStr) throws Exception {
        String outputStr = "";
        int number1;
        int number2;
        int result;
        char operation;
        String[] inputArray = inputStr.split(" ");
        if (inputArray.length < 3) {
            throw new Exception("Строка не является математической операцией");
        }
        if (inputArray.length > 3) {
            throw new Exception("Формат математической операции не удовлетворяет заданию - " +
                    "два операнда и один оператор (+, -, /, *)");
        }
        if (!isValidType(inputArray)) {
            throw new Exception("Используются одновременно разные системы счисления");
        }
        number1 = getFirstNumber(inputArray);
        if (!isValidNumber(number1)) {
            throw new Exception("Диапазон первого числа от 1 до 10 включительно");
        }
        operation = getOperation(inputArray);
        if (!isValidOperation(operation)) {
            throw new Exception("Введите корректную вычислительную операцию:\n" +
                    "'+' - сложение\n" +
                    "'-' - вычитание\n" +
                    "'*' - умножение\n" +
                    "'/' - деление\n");
        }
        number2 = getSecondNumber(inputArray);
        if (!isValidNumber(number2)) {
            throw new Exception("Диапазон первого числа от 1 до 10 включительно");
        }
        result = getResultByOperation(operation, number1, number2);
        if (isRomanResult(inputArray)) {
            if (result < 0) {
                throw new Exception("В римской системе нет отрицательных чисел");
            }
            outputStr = arabicToRoman(result);
        } else {
            outputStr = Integer.toString(result);
        }
        return outputStr;
    }

    private static boolean isValidOperation(char operationSymbol) {
        if (operationSymbol == '+' || operationSymbol == '-'
            || operationSymbol == '*' || operationSymbol == '/') {
            return true;
        }
        return false;
    }

    private static int getResultByOperation(char operationSymbol, int number1, int number2) {
        if (operationSymbol == '+') {
            return number1 + number2;
        } else if (operationSymbol == '-') {
            return number1 - number2;
        } else if (operationSymbol == '*') {
            return number1 * number2;
        } else if (operationSymbol == '/') {
            return number1 / number2;
        }
        return -1;
    }

    private static boolean isValidNumber(int number) {
        if (number < 1) {
            return false;
        }
        if (number > 10) {
            return false;
        }
        return true;
    }

    private static int getFirstNumber(String[] inputArray) {
        String firstNumber = inputArray[0];
        if (isRomanNumeral(firstNumber)) {
            return getIntByRomanNumeral(firstNumber);
        }
        return Integer.parseInt(firstNumber);
    }

    private static char getOperation(String[] inputArray) {
        return inputArray[1].charAt(0);
    }

    private static int getSecondNumber(String[] inputArray) {
        String secondNumber = inputArray[2];
        if (isRomanNumeral(secondNumber)) {
            return getIntByRomanNumeral(secondNumber);
        }
        return Integer.parseInt(secondNumber);
    }

    private static boolean isValidType(String[] inputArray) {
        if (isRomanNumeral(inputArray[0]) && !isRomanNumeral(inputArray[2])) {
            return false;
        }
        if (!isRomanNumeral(inputArray[0]) && isRomanNumeral(inputArray[2])) {
            return false;
        }
        return true;
    }

    private static boolean isRomanNumeral(String romanNumeral) {
        if (romanNumeral.equals("I") || romanNumeral.equals("II") || romanNumeral.equals("III") ||
                romanNumeral.equals("IV") || romanNumeral.equals("V") || romanNumeral.equals("VI") ||
                romanNumeral.equals("VII") || romanNumeral.equals("VIII") || romanNumeral.equals("IX") ||
                romanNumeral.equals("X")) {
            return true;
        }
        return false;
    }

    private static boolean isRomanResult(String[] inputArray) {
        if (isRomanNumeral(inputArray[0]) && isRomanNumeral(inputArray[2])) {
            return true;
        }
        return false;
    }

    private static int getIntByRomanNumeral(String romanNumeral) {
        if (romanNumeral.equals("I")) {
            return 1;
        } else if (romanNumeral.equals("II")) {
            return 2;
        } else if (romanNumeral.equals("III")) {
            return 3;
        } else if (romanNumeral.equals("IV")) {
            return 4;
        } else if (romanNumeral.equals("V")) {
            return 5;
        } else if (romanNumeral.equals("VI")) {
            return 6;
        } else if (romanNumeral.equals("VII")) {
            return 7;
        } else if (romanNumeral.equals("VIII")) {
            return 8;
        } else if (romanNumeral.equals("IX")) {
            return 9;
        } else if (romanNumeral.equals("X")) {
            return 10;
        }
        throw new IllegalArgumentException("Недопустимая римская цифра: " + romanNumeral);
    }

    public static String arabicToRoman(int number) {
        StringBuilder roman = new StringBuilder();
        while (number >= 1000) {
            roman.append("M");
            number -= 1000;
        }
        while (number >= 900) {
            roman.append("CM");
            number -= 900;
        }
        while (number >= 500) {
            roman.append("D");
            number -= 500;
        }
        while (number >= 400) {
            roman.append("CD");
            number -= 400;
        }
        while (number >= 100) {
            roman.append("C");
            number -= 100;
        }
        while (number >= 90) {
            roman.append("XC");
            number -= 90;
        }
        while (number >= 50) {
            roman.append("L");
            number -= 50;
        }
        while (number >= 40) {
            roman.append("XL");
            number -= 40;
        }
        while (number >= 10) {
            roman.append("X");
            number -= 10;
        }
        while (number >= 9) {
            roman.append("IX");
            number -= 9;
        }
        while (number >= 5) {
            roman.append("V");
            number -= 5;
        }
        while (number >= 4) {
            roman.append("IV");
            number -= 4;
        }
        while (number >= 1) {
            roman.append("I");
            number -= 1;
        }
        return roman.toString();
    }
}