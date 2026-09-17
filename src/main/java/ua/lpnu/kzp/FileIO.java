package ua.lpnu.kzp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Клас для читання та запису текстових файлів у кодуванні UTF-8.
 */
public final class FileIO {

    private FileIO() {

    }
    
    /**
     * Читає всі рядки з файлу у кодуванні UTF-8.
     *
     * @param input шлях до вхідного файлу
     * @return список рядків файлу
     * @throws IOException якщо файл не знайдено або виникла помилка вводу-виводу
     */
    public static List<String> readLines(Path input) throws IOException {
        return Files.readAllLines(input, StandardCharsets.UTF_8);
    }

    /**
     * Записує сформований звіт у текстовий файл у кодуванні UTF-8.
     * За потреби створює батьківський каталог.
     *
     * @param output шлях до вихідного файлу
     * @param report сформований текстовий звіт
     * @throws IOException якщо не вдалося створити каталог або записати файл
     */
    public static void writeReport(Path output, String report) throws IOException {
        Path parent = output.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        Files.writeString(output, report, StandardCharsets.UTF_8);
    }
}