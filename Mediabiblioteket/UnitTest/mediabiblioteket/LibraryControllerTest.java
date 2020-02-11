package mediabiblioteket;

import org.junit.jupiter.api.Test;

import java.awt.Robot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;


class LibraryControllerTest {


    LibraryController libraryController = new LibraryController();

    @Test
    void test_checkUserInput_inputNonDigits_checkIfFalse() {
        //Tillåter endast a-z, A-Z, 1-9, -
        boolean test=  libraryController.checkUserInput("@££$€$€£€€${{${");
        assertEquals(false, test);

    }

    @Test
    void test_checkInputOnlyDigits_inputValidNumbers_CheckIfTrue() {

        //Tillåter endast 1-9
        boolean test = libraryController.checkInputOnlyDigits("12345");

        assertEquals(true,test);
    }

    @Test
    void test_writeToFile_writeMockdataToFile_checkIfMockdataRegistered() {
        // Fyll på strukturen borrowed med data
        String testdata = "121212-1212;123456"; // Personnummer;MedieID
        libraryController.borrowed.add(testdata);

        // Skriv dessa data till fil
        libraryController.writeToFile();

        // Läs tillbaka filen. Efter loopen skall den sista posten ligga i theLine. Det är den vi vill titta närmare på
        String theLine="";
        try {
            Scanner theScanner = new Scanner(new File("Mediabiblioteket/files/Utlanade.txt"));

            while (theScanner.hasNext()){
                theLine = theScanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Jämför filens innehåll med det vi skickade till den
        assertEquals(testdata, theLine);

        // Ta bort inskriven testdata
        libraryController.borrowed.removeLast();
        libraryController.writeToFile();
    }

    @Test
    void test_borrowMedia_checkIfMediaIsBorrowed_trueIfBorrowed() {
        // Skapa testdata
        Book testbok = new Book("Bok","Boktitel", "BokID", 1920, "Hermann Hesse");
        Borrower testB= new Borrower("testnamn", "121212-1212", "0700900909");

        // Registrera testboken som utlånad till testanvändaren
        libraryController.setCurrentBorrower(testB);
        libraryController.borrowMedia(testbok);

        // Verifiera att testboken är utlånad
        assertTrue(testbok.borrowed);

        // Rensa bort inskriven testdata
        libraryController.borrowed.removeLast();
        libraryController.writeToFile();

    }


    @Test
    void test_returnMedia_checkIfMediaReturnedAfterBorrowed_falseBorrowedIfReturned() {
        // Skapa testdata
        Book testbok = new Book("Bok","Boktitel", "BokID", 1920, "Hermann Hesse");
        Borrower testB= new Borrower("testnamn", "121212-1212", "0700900909");

        // Registrera testboken som utlånad till testanvändaren
        libraryController.setCurrentBorrower(testB);
        libraryController.borrowMedia(testbok);
        libraryController.returnMedia(testbok);

        // Verifiera att testboken är utlånad
        assertFalse(testbok.borrowed);

        // Rensa bort inskriven testdata
        //libraryController.borrowed.removeLast();
        //libraryController.writeToFile();
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