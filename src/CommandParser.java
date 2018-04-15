public class CommandParser {

    private boolean h, c, si;
    private final String[] filePath;

    public CommandParser(String line) {
        final String[] command = line.split("[ ]");
        final int length = command.length;

        // В случае, если не были введены имена файлов/директорий или отсутствует ключевое слово du, выдать код ошибки 1
        if (length < 2 || !command[0].equals("du")) {
            throw new IllegalArgumentException();
        }

        // Проверка флагов
        h = false;
        c = false;
        si = false;
        boolean stop = false;
        int index = 0;

        while (!stop) {
            if (++index == length) {
                throw new IllegalArgumentException();
            }

            switch (command[index]) {
                case "-h":
                    if (h) {
                        throw new IllegalArgumentException();
                    }
                    h = true;
                    break;
                case "-c":
                    if (c) {
                        throw new IllegalArgumentException();
                    }
                    c = true;
                    break;
                case "--si":
                    if (si) {
                        throw new IllegalArgumentException();
                    }
                    si = true;
                    break;
                default:
                    stop = true;
            }
        }

        // Добавление в поле filePath путей к файлам
        final int newLength = length - index;

        if (c) {
            filePath = new String[newLength + 1];
            filePath[newLength] = "Total size";
        } else {
            filePath = new String[newLength];
        }

        final int shift = index;
        for (int i = 0; i < newLength; i++) {
            filePath[i] = command[i + shift];
        }
    }

    public String[] getFilePath() {
        return filePath;
    }

    public boolean getH() {
        return h;
    }

    public boolean getC() {
        return c;
    }

    public boolean getSi() {
        return si;
    }
}