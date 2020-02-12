package mediabiblioteket;

import org.junit.jupiter.api.Test;

import java.awt.*;

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
    void test_checkInputOnlyDigits_inputValidNumbers_CheckIfTrue() throws AWTException {

        //Tillåter endast 1-9
        //Robot bot = new Robot();
        boolean test = libraryController.checkInputOnlyDigits("12345");

        assertEquals(true,test);
    }

    @Test
    void test_writeToFile_writeMockdataToFile_checkIfMockdataRegistered_GS() {
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
    void test_borrowMedia_checkIfMediaIsBorrowed_trueIfBorrowed_GS() {
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
    void test_returnMedia_checkIfMediaReturnedAfterBorrowed_falseBorrowedIfReturned_GS() {
        // Skapa testdata, bok och låntagare
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
    void test_checkIfBorrowerExist_checkIfuserIDexists_returnTrueIfExists_GS() {
        // Mock testdata of existing userID
        String userID1 = "900118-5555";
        // Mock testdata of non-existing userID
        String userID2 = "121212-1212";

        // Search "Lantagare" with mock userID if exists
        Boolean borrowerResult1 = libraryController.checkIfBorrowerExist(userID1);
        // Search "Lantagare" with mock userID if exists
        Boolean borrowerResult2 = libraryController.checkIfBorrowerExist(userID2);

        // Jämför om resultaten är korrekt
        assertTrue(borrowerResult1);
        assertFalse(borrowerResult2);
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