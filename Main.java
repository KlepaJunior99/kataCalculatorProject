import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение в формате I+I или 4*5, в одной системе счисления с числами от 1 до 10");
        String condition = scanner.nextLine();
        Calculator calculator = new Calculator();
        String result = calculator.calculate(condition);
        System.out.println(result);
    }
}
