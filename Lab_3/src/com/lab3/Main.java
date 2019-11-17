package com.lab3;

import com.lab3.parser.DateParser;
import org.apache.log4j.Logger;
import com.lab3.word.Word;

import java.util.List;
import java.util.Scanner;


public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        String tempString = null;
        int userChoice = 0;

        while (true){
            System.out.println("\t\t\t\tМеню");
            System.out.println("1. Проверка даты на правильность формата (dd/mm/yyyy)");
            System.out.println("2. Напечатать слова текста в алфавитном порядке");
            System.out.println("3. Выход");
            System.out.print("Выберите пункт меню:    ");

            try {
                userChoice = new Scanner(System.in).nextInt();
            }catch (Exception e){
                LOGGER.error(e.getMessage(), e);
            }

            switch (userChoice){
                case 1:
                    System.out.print("Введите строку:\t");
                    tempString = new Scanner(System.in).nextLine();
                    System.out.printf(DateParser.isCorrectDate(tempString) ? "Строка %s - дата в формате dd/mm/yyyy\n" :
                            "Строка %s - не в формате dd/mm/yyyy\n", tempString);
                    break;
                case 2:
                    System.out.print("Введите строку:\t");
                    tempString = new Scanner(System.in).nextLine();
                    System.out.printf("Исходный текст:\n\t%s\n\n", tempString);

                    List<Word> words = Word.separatorText(tempString);
                    System.out.println("Обработанный текст:");
                    Word.sorting(words);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неверный ввод!!!");
                    break;
            }
        }

    }
}
