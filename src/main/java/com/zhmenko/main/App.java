package com.zhmenko.main;

import com.zhmenko.searcher.CSVSearcher;
import com.zhmenko.ui.Menu;
import com.zhmenko.utils.FileReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        int columnNumber = -1;
        try {
            if (args.length > 0) columnNumber = Integer.parseInt(args[0]);
            if (columnNumber < 1) {
                System.out.println("Ошибка! Параметр должен представлять собой номер столбца (положительное целочисленное значение)!");
                return;
            }
            String filePath = inputFilePath();
            CSVSearcher csvSearcher = new CSVSearcher(filePath, columnNumber);
            FileReader fileReader = new FileReader(new File(filePath));
            Menu menu = new Menu(csvSearcher, fileReader);
            menu.start();
        } catch (NumberFormatException e) {
            System.out.println("Ошибка! Параметр должен представлять собой номер столбца (положительное целочисленное значение)!");
            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    public static String inputFilePath() {
        String filePath = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Введите путь к файлу: ");
            filePath = scanner.next();
            if (Files.isRegularFile(Path.of(filePath))) break;
            System.out.println("Файла по введённому пути не существует! Повторите попытку!");
        }
        return filePath;
    }
}
