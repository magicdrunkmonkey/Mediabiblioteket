package mediabiblioteket;

import collections.ArrayList;
import collections.LinkedList;
import org.junit.jupiter.api.Test;

import java.awt.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

import static org.junit.jupiter.api.Assertions.*;


class LibraryControllerTest {

    ArrayList<Media> allMediaObjects;
    LibraryController libraryController = new LibraryController();

    @Test
    void test_checkUserInput_inputNonDigits_expectedFalse() {
        //Tillåter endast a-z, A-Z, 1-9, -
        boolean test=  libraryController.checkUserInput("@££$€$€£€€${{${");
        assertEquals(false, test);

    }

    @Test
    void test_checkUserInput_inputOnlyDigits_expectedTrue() {
        //Tillåter endast a-z, A-Z, 1-9, -
        boolean test=  libraryController.checkUserInput("3578");
        assertEquals(true, test);
    }
    @Test
    void test_checkUserInput_inputNull_expectedFalse() {
        //Tillåter endast a-z, A-Z, 1-9, -
        boolean test=  libraryController.checkUserInput(null);
        assertEquals(false,test);
    }
    @Test
    void test_checkUserInput_emptyString_expectedFalse() {
        //Tillåter endast a-z, A-Z, 1-9, -
        boolean test=  libraryController.checkUserInput("");
        assertEquals(false,test);
    }

    @Test
    void test_checkInputOnlyDigits_inputValidNumbers_CheckIfTrue() {
        //Tillåter endast 1-9
        boolean test = libraryController.checkInputOnlyDigits("12345325");

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

        // Verifiera att testboken är utlånad
        assertTrue(testbok.borrowed);
        libraryController.returnMedia(testbok);

        // Verifiera att testboken är återlämnad
        assertFalse(testbok.borrowed);
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
    void test_sortMedia_checkIfMediaIsSorting_comparesListedMediaIsInCorrectOrder_GS() {
        //Arrange
        LibraryController libc = new LibraryController(false);
        //allMediaObjects = new ArrayList<Media>(3);

        try
        {
            StringTokenizer theTokenizer;
            Scanner theScanner = new Scanner(new File("Mediabiblioteket/files/MediaTest.txt"));

            while (theScanner.hasNext())
            {

                String theLine = theScanner.nextLine();
                theTokenizer = new StringTokenizer(theLine, ";");
                String mediaFormat = theTokenizer.nextToken();

                if (mediaFormat.equals("Bok"))
                {
                    String objectID = theTokenizer.nextToken();
                    String author = theTokenizer.nextToken();
                    String title = theTokenizer.nextToken();
                    String year = theTokenizer.nextToken();

                    libc.allMediaObjects.add(new Book("Bok", title, objectID, Integer.parseInt(year), author));
                } else
                {
                    String objectID = theTokenizer.nextToken();
                    String title = theTokenizer.nextToken();
                    String year = theTokenizer.nextToken();
                    LinkedList<String> theActorList = new LinkedList<String>();
                    while (theTokenizer.hasMoreTokens())
                    {
                        theActorList.add(theTokenizer.nextToken());
                    }

                    libc.allMediaObjects.add(new DVD("DVD", title, objectID, Integer.parseInt(year), theActorList));

                }

            }
            theScanner.close();
            //Act
            libc.sortMedia();

        } catch (Exception e)
        {
            e.printStackTrace();
            //return false;
        }
        //return true;
        //assertArrayEquals(allMediaObjects.get(0));
        //assertEquals(1,1);

        //Arrange some more
        String idNumbers[] = new String[3];
        for (int i=0; i<3; i++){
            idNumbers[i] = libc.allMediaObjects.get(i).objectID;
        }
        //idNumbers[0] = libc.allMediaObjects.get(0).objectID;

        String expected[] = new String[]{"427769", "635492","874591"};

        //Assert
        assertArrayEquals(idNumbers, expected);
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