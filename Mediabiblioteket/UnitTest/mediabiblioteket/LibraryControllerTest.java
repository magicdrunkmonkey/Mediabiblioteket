package mediabiblioteket;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryControllerTest {

    LibraryController controller = new LibraryController();

    @Test
    void checkUserInput() {

        //Tillåter input a-z, A-Z, 1-9, -
        boolean test=  controller.checkUserInput("@££$€$€£€€${{${");
        assertEquals(false, test);

    }

    @Test
    void checkInputOnlyDigits() {

        boolean test = controller.checkInputOnlyDigits("1212121212");
        assertEquals(true,test);
    }

    @Test
    void writeToFile() {
    }

    @Test
    void borrowMedia() {
    }

    @Test
    void returnMedia() {
    }

    @Test
    void checkIfBorrowerExist() {
    }

    @Test
    void sortMedia() {
    }

    @Test
    void getMedia() {
    }

    @Test
    void showSelectedMediaInfo() {
    }

    @Test
    void searchMediaAllByString() {
    }

    @Test
    void getMediaFromSearchResult() {
    }

    @Test
    void searchMediaTitleByString() {
    }

    @Test
    void getBorrower() {
    }

    @Test
    void loadBorrowedMedia() {
    }

    @Test
    void searchBorrowed() {
    }
}