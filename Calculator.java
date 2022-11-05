public class Calculator {
    public static String a;
    public static String calculate(String condition) {
        String result = null;
        String condition1 = condition.toUpperCase();
        String[] operator = {"+", "-", "/", "*"};
        String[] regOperator = {"\\+", "-", "/", "\\*"};
        int operatorIndex = -1;
        for (int i = 0; i<operator.length; i++) {
            if (condition1.contains(operator[i])) {
                operatorIndex = i;
                break;
            }
        } if (operatorIndex == -1) {
            throw new RuntimeException("throws Exception //т.к. строка не является математической операцией");
        }
        Calculator.a = operator[operatorIndex];
        String[] operands = condition1.split(regOperator[operatorIndex]);
        if (operands.length != 2) {
            throw new RuntimeException("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        String operand1 = operands[0].trim();
        String operand2 = operands[1].trim();
        if (isNumArabian(operand1) == true && isNumArabian(operand2) == true) {
        int operand11 = Integer.parseInt(operand1);
        int operand22 = Integer.parseInt(operand2);
           result = resultToActionsArabian(operand11, operand22, regOperator[operatorIndex]);
        }
        if (isNumArabian(operand1) == false && isNumArabian(operand2) == false) {
            int operand1IntValue = romeToArabian(operand1);
            int operand2IntValue = romeToArabian(operand2);
            String result2 = resultToActionsRome(operand1IntValue, operand2IntValue, regOperator[operatorIndex]);
            result = arabianToRome(Integer.parseInt(result2.toString()));
            if(romeToArabian(result) < 1) {
                throw new RuntimeException("throws Exception //т.к. в римской системе нет отрицательных чисел");
            }
        }
        if (result == null) {
            throw new RuntimeException("throws Exception //т.к. используются одновременно разные системы счисления");
        }
        return result;
    }

    public static boolean isNumArabian(String operand1) {
        try {
            Integer.parseInt(operand1);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
    public static int romeToArabian(String romeValue) {
        String accumulate = romeValue;
        int accumInts = 0;

        for (int i = RomeInts.values().length - 1; i >= 0 && accumulate.length() > 0; i--) {
            int arabian1 = RomeInts.values()[i].getRomeints();
            String av = RomeInts.values()[i].name();
            while (accumulate.startsWith(av)) {
                accumulate = accumulate.substring(av.length());
                accumInts += arabian1;
            }
        }
        return accumInts;
    }

    public static String arabianToRome(int arabianValue) {
        int accumulate = arabianValue;
        StringBuilder romeResult = new StringBuilder();
        for (int i = RomeInts.values().length - 1; i >= 0 && arabianValue > 0; i--) {
            String romeNumName = RomeInts.values()[i].name();
            int av = RomeInts.values()[i].getRomeints();
            while (accumulate >= av) {
                accumulate -= av;
                romeResult.append(romeNumName);
            }
        }
        return romeResult.toString();
    }

    public static String resultToActionsRome(int operand1IntValue, int operand2IntValue, String operator) {
        boolean q = operand1IntValue > 0 && operand1IntValue <= 10;
        boolean q1 = operand2IntValue > 0 && operand2IntValue <= 10;
        String result = null;
        if (q != q1) {
            throw new RuntimeException("throws Exception //используйте числа от I до X");
        }
        if (q == true && q1 == true) {
            switch (Calculator.a) {
                case "+":
                    result = String.valueOf(operand1IntValue + operand2IntValue);
                    break;
                case "-":
                    result = String.valueOf(operand1IntValue - operand2IntValue);
                    break;
                case "*":
                    result = String.valueOf(operand1IntValue * operand2IntValue);
                    break;
                case "/":
                    result = String.valueOf(operand1IntValue / operand2IntValue);
                    break;
                default:
                    System.out.println("Вы ввели что-то не то");
                    break;

            }
            result = Integer.toString(Integer.parseInt(result));
        }
        return result;
    }
    public static String resultToActionsArabian(int operand11, int operand22, String operator) {
        boolean q = operand11 > 0 && operand11 <= 10;
        boolean q1 = operand22 > 0 && operand22 <= 10;
        String result = null;
        if (q != q1) {
            throw new RuntimeException("throws Exception //используйте числа от 1 до 10");
        }
        if (q == true && q1 == true) {
            switch (Calculator.a) {
                case "+":
                    int i = operand11 + operand22;
                    result = Integer.toString(i);
                    break;
                case "-":
                    result = String.valueOf(operand11 - operand22);
                    break;
                case "*":
                    result = String.valueOf(operand11 * operand22);
                    break;
                case "/":
                    result = String.valueOf(operand11 / operand22);
                    break;
                default:
                    System.out.println("Вы ввели что-то не то");
                    break;

            }
        }
        return result;
    }
}
