
import java.util.Scanner;
public class Bank {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calk(input));
    }
    public static String calk (String input) throws Exception{
        String in = "";
        StringBuilder inInArab = new StringBuilder();
        int a = 0;
        int b = 0;
        boolean ArabicA = false;
        boolean ArabicB = false;
        String[] str = input.split(" ");
        if (str.length > 3) {
            throw new Exception("т.к. формат математической операции не удовлетворяет" +
                    " заданию - два операнда и один оператор (+, -, /, *)");
        }
        if (str[0].length() > 4) {
            throw new Exception("т.к. введен не верный формат данных");
        }
        if (str.length < 3) {
            throw new Exception("т.к. строка не является математической операцией");
        }

        if (str[2].length() > 4) {
            throw new Exception("т.к. введен не верный формат данных");
        }

        if (!(str[1].contains("+")) && !(str[1].contains("-")) && !(str[1].contains("*")) && !(str[1].contains("/"))) {
            throw new Exception("т.к. нет подходящей математической операции");
        }
        String oper = str[1];

        for (Arab ar : Arab.values()) {
            if (ar.name().equals(str[0])) {
                ArabicA = true;
                break;
            }
        }
        for (Arab ar : Arab.values()) {
            if (ar.name().equals(str[2])) {
                ArabicB = true;
                break;
            }
        }
        if ((!ArabicA && ArabicB) || (ArabicA && !ArabicB)) {
            throw new Exception("т.к. используются одновременно разные системы счисления");
        }

        if (!ArabicA) {
            try {
                a = Integer.parseInt(str[0]);
                if (a > 10||a < 1) {
                    throw new Exception();
                }
            } catch (Exception e) {
                throw new Exception("т.к. введен не верный формат чисел");
            }
        }
        if (!ArabicB) {
            try {
                b = Integer.parseInt(str[2]);
                if (b > 10  || b < 1) {
                    throw new Exception();
                }
            } catch (Exception e) {
                throw new Exception("т.к. введен не верный формат чисел");
            }
        }
        if (ArabicA && ArabicB) {

            Arab arabNum1 = Arab.valueOf(str[0]);
            Arab arabNum2 = Arab.valueOf(str[2]);
            a = arabNum1.getZnachenie();
            b = arabNum2.getZnachenie();
            in = operating(in, oper, a, b);

            if (Integer.parseInt(in) < 1) {
                throw new Exception("т.к. в римской системе исчисления нет отрицательных чисел");
            }
            if (Integer.parseInt(in) == 0) {
                throw new Exception("т.к. в римской системе исчисления нет ноля");
            }
            if (Integer.parseInt(in) <= 10) {
                for (Arab ar : Arab.values()) {
                    if (ar.getZnachenie() == Integer.parseInt(in)) {
                        inInArab.append(ar.name());
                        return inInArab.toString();
                    }
                }
            }
            if (Integer.parseInt(in) > 10 && Integer.parseInt(in) <= 19) {
                inInArab.append("X");
                for (Arab ar : Arab.values()) {
                    if (ar.getZnachenie() == Integer.parseInt(String.valueOf(in.charAt(1)))) {
                        inInArab.append(ar.name());
                        return inInArab.toString();
                    }
                }
            }
            if (Integer.parseInt(in) > 19 && Integer.parseInt(in) < 50) {
                int ten = Integer.parseInt(in) / 10;
                int lastNum = Integer.parseInt(in) % 10;
                if(ten == 4){
                    inInArab.append("X");
                    inInArab.append("L");
                    for (Arab ar : Arab.values()) {
                        if (ar.getZnachenie() == lastNum) {
                            inInArab.append(ar.name());
                            return inInArab.toString();
                        }
                    }
                }else {
                    inInArab.append("X".repeat(Math.max(0, ten)));
                    for (Arab ar : Arab.values()) {
                        if (ar.getZnachenie() == lastNum) {
                            inInArab.append(ar.name());
                            return inInArab.toString();
                        }
                    }
                }

            }
            if (Integer.parseInt(in) > 51 && Integer.parseInt(in) < 90) {
                inInArab.append("L");
                int ten = (Integer.parseInt(in) - 50) / 10;
                int lastNum = Integer.parseInt(in) % 10;
                if(ten != 0){
                    inInArab.append("X".repeat(Math.max(0, ten)));
                }
                if (lastNum != 0) {
                    for (Arab ar : Arab.values()) {
                        if (ar.getZnachenie() == lastNum) {
                            inInArab.append(ar.name());
                            return inInArab.toString();
                        }
                    }
                }return inInArab.toString();
            }
            if (Integer.parseInt(in) == 50) {
                inInArab.append("L");
                return inInArab.toString();
            }

            if (Integer.parseInt(in) == 90) {
                inInArab.append("XC");
                return inInArab.toString();
            }
            if (Integer.parseInt(in) == 100) {
                inInArab.append("C");
                return inInArab.toString();
            }
            return inInArab.toString();
        } else {
            in = operating(in, oper, a, b);
        }
        return in;
    }
    static String operating(String in, String oper, int a, int b) {
        switch (oper) {
            case "+" -> in = String.valueOf(a + b);
            case "-" -> in= String.valueOf(a - b);
            case "*" -> in = String.valueOf(a * b);
            case "/" -> in = String.valueOf(a / b);
        }
        return in;
    }
}




