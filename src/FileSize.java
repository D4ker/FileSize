public final class FileSize {

    private final String[] filePath;
    private final String[] size;

    public FileSize(String line) {

        // Получаем значения флагов и пути к файлам
        final CommandParser treatedCommand = new CommandParser(line);

        filePath = treatedCommand.getFilePath();
        final boolean h = treatedCommand.getH();
        final boolean c = treatedCommand.getC();
        final boolean si = treatedCommand.getSi();

        // Получаем размеры файлов в байтах
        final SizeHandler receivedSize = new SizeHandler(c, filePath);

        // Обрабатываем размеры файлов с учётом флагов
        final FlagHandler treatedSize = new FlagHandler(h, si, receivedSize.getSize());

        size = treatedSize.getSize();
    }

    public String[] getSize() {
        return size;
    }

    public String[] getFilePath() {
        return filePath;
    }
}