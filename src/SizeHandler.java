import java.io.File;

public class SizeHandler {

    private final long[] size;

    public SizeHandler(boolean c, String[] filePath) {
        int length = filePath.length;
        size = new long[length];
        long sum = 0;
        if (c) {
            length--;
        }

        // Перебор файлов/каталогов
        for (int i = 0; i < length; i++) {
            File currentFile = new File(filePath[i]);

            // Есди файл не существует, выдать код ошибки 1
            if (!currentFile.exists()) {
                throw new IllegalArgumentException();
            }

            // Вычисление размера файла/каталога
            if (currentFile.isFile()) {
                size[i] = currentFile.length();
            } else {
                size[i] = sizeOfDirectory(currentFile);
            }

            sum += size[i];
        }

        // Посчитать суммарный объём, если был указан флаг -c
        if (c) {
            size[length] = sum;
        }
    }

    // Метод для вычисления размера каталога
    private long sizeOfDirectory(File directory) {
        long size = 0;
        for (File currentFile : directory.listFiles()) {
            if (currentFile.isFile()) {
                size += currentFile.length();
            } else {
                size += sizeOfDirectory(currentFile);
            }
        }
        return size;
    }

    // Метод для получения в виде массива размеров всех запрашиваемых файлов
    public long[] getSize() {
        return size;
    }
}