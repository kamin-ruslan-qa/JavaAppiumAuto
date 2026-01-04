package org.example;

import java.util.Scanner;
import java.util.Random;

public class GuessTheNumber {
    public static void main(String[] args) {
        Random random = new Random();
        int secretNumber = random.nextInt(10) + 1;
        Scanner scanner = new Scanner(System.in);
        int attempts = 0;
        System.out.println("🎮 Добро пожаловать в 'Угадай число'!");
        System.out.println("Я загадал число от 1 до 10. Попробуй угадать!");

        while (true) {
            System.out.print("Ваш вариант: ");
            int guess = scanner.nextInt();
            attempts++;

            if (guess == secretNumber) {
                System.out.println("🎉 Поздравляю! Ты угадал за " + attempts + " попыток!");
                break;  // выходим из цикла
            } else if (guess < secretNumber) {
                System.out.println("⬆️ Слишком мало!");
            } else {
                System.out.println("⬇️ Слишком много!");
            }
        }
        scanner.close();
    }
}