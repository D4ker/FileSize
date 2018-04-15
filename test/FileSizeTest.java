import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class FileSizeTest {

    @Test
    public void FileSize() {
        assertArrayEquals(
                new String[]{"35,30", "18,42", "1,43"},
                new FileSize("du files/ files/books files/documents/doc1.txt").getSize()
        );
        System.out.println("\n");
        assertArrayEquals(
                new String[]{"35,30", "18,42", "2,45", "1,43", "57,60"},
                new FileSize("du -c files/ files/books files/films/action files/documents/doc1.txt").getSize()
        );
        System.out.println("\n");
        assertArrayEquals(
                new String[]{"35,30KB", "18,42KB", "2,45KB", "1,43KB", "57,60KB"},
                new FileSize("du -h -c files/ files/books files/films/action files/documents/doc1.txt").getSize()
        );
        System.out.println("\n");
        assertArrayEquals(
                new String[]{"36,15KB", "0,00B", "18,86KB", "10,30KB", "1,30KB", "2,51KB", "1,47KB", "70,59KB"},
                new FileSize("du -h --si -c files/ files/empty files/books files/films" +
                        " files/documents/doc3.txt files/films/action files/documents/doc1.txt").getSize()
        );
    }

    @Test
    public void errorChecking() {
        try {
            new FileSize("du ergegr/ files/books fittthles/dos/doc1.txt");
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        try {
            new FileSize("du");
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        try {
            new FileSize("files/ files/books files/documents/doc1.txt");
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        try {
            new FileSize("du -c -h");
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        try {
            new FileSize("-c --si files/ files/books files/documents/doc1.txt");
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        try {
            new FileSize("du -c -c -c files/ files/books files/documents/doc1.txt");
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }
}
