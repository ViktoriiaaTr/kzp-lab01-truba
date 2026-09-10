package ua.lpnu.kzp;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class Main {

    private static final String VERSION = "1.0.0";
    private static final Path DEFAULT_INPUT = Path.of("data", "input.csv");
    private static final Path DEFAULT_OUTPUT = Path.of("out", "report.txt");

    private Main() {
    }

    public static void main(String[] args) {
        Path input = DEFAULT_INPUT;
        Path output = DEFAULT_OUTPUT;

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            switch (arg) {
                case "--help":
                    printHelp();
                    return;
                case "--version":
                    System.out.printf("Application version: %s%n", VERSION);
                    return;
                case "--input":
                    if (i + 1 < args.length) {
                        input = Path.of(args[++i]);
                    } else {
                        System.err.println("Помилка: --input потребує шлях до файлу");
                        return;
                    }
                    break;
                case "--output":
                    if (i + 1 < args.length) {
                        output = Path.of(args[++i]);
                    } else {
                        System.err.println("Помилка: --output потребує шлях до файлу");
                        return;
                    }
                    break;
                default:
                    System.err.println("Невідомий аргумент: " + arg);
                    printHelp();
                    return;
            }
        }

        List<String> lines;
        try {
            lines = FileIO.readLines(input);
        } catch (IOException exception) {
            System.err.println("Не вдалося прочитати файл: " + input);
            System.err.println(exception.getMessage());
            return;
        }

        List<CurrencyRecord> records = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            try {
                CurrencyRecord record = Parser.parseLine(line);
                records.add(record);
            } catch (IllegalArgumentException exception) {
                errors.add("Рядок " + (i + 1) + ": " + exception.getMessage());
            }
        }

        Statistics statistics = Calculator.calculate(records);
        String report = Reporter.formatReport(statistics, errors);

        System.out.print(report);

        try {
            FileIO.writeReport(output, report);
        } catch (IOException exception) {
            System.err.println("Не вдалося записати звіт: " + output);
            System.err.println(exception.getMessage());
        }
    }

    private static void printHelp() {
        System.out.println("Використання: java -jar lab01-1.0.0.jar [--help] [--version] [--input <файл>] [--output <файл>]");
        System.out.println("  --help            показати цю довідку");
        System.out.println("  --version         показати версію програми");
        System.out.println("  --input <файл>    шлях до вхідного CSV-файлу (за замовчуванням data/input.csv)");
        System.out.println("  --output <файл>   шлях до вихідного файлу звіту (за замовчуванням out/report.txt)");
    }
}