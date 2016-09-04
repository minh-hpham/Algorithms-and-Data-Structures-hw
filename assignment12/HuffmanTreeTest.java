package assignment12;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;

public class HuffmanTreeTest
{
    HuffmanTree t = new HuffmanTree();
    // original files
    File original = new File("original.txt");
    File original2 = new File("original2.txt");
    File original3 = new File("original3.txt");
    File lotsOfAs = new File("lotsOfAs.txt");
    File alphabet = new File("alphabet.txt");
    File aristotle = new File("aristotle.txt");
    File random = new File("random.txt");
    // compressed files
    File cOriginal = new File("cOriginal.txt");
    File cOriginal2 = new File("cOriginal2.txt");
    File cOriginal3 = new File("cOriginal3.txt");
    File cLotsOfAs = new File("cLotsOfAs.txt");
    File cAlphabet = new File("cAlphabet.txt");
    File cAristotle = new File("cAristotle.txt");
    File cRandom = new File("cRandom.txt");
    // decompressed files
    File dOriginal = new File("dOriginal.txt");
    File dOriginal2 = new File("dOriginal2.txt");
    File dOriginal3 = new File("dOriginal3.txt");
    File dLotsOfAs = new File("dDotsOfAs.txt");
    File dAlphabet = new File("dAlphabet.txt");
    File dAristotle = new File("dAristotle.txt");
    File dRandom = new File("dRandom.txt");

    static double origR, orig2R, orig3R, lotsOfAsR, alphabetR, aristotleR, randomR;

    @Before
    public void setUp ()
    {
        t.compressFile(original, cOriginal);
        t.decompressFile(cOriginal, dOriginal);

        t.compressFile(original2, cOriginal2);
        t.decompressFile(cOriginal2, dOriginal2);

        t.compressFile(original3, cOriginal3);
        t.decompressFile(cOriginal3, dOriginal3);

        t.compressFile(lotsOfAs, cLotsOfAs);
        t.decompressFile(cLotsOfAs, dLotsOfAs);

        t.compressFile(alphabet, cAlphabet);
        t.decompressFile(cAlphabet, dAlphabet);

        t.compressFile(aristotle, cAristotle);
        t.decompressFile(cAristotle, dAristotle);

        t.compressFile(random, cRandom);
        t.decompressFile(cRandom, dRandom);

    }

    @Test
    public void testFile () throws FileNotFoundException
    {
        assertTrue(compareFiles(original, dOriginal));
        assertTrue(compareFiles(original2, dOriginal2));
        assertTrue(compareFiles(original3, dOriginal3));
        assertTrue(compareFiles(lotsOfAs, dLotsOfAs));
        assertTrue(compareFiles(alphabet, dAlphabet));
        assertTrue(compareFiles(aristotle, dAristotle));
        assertTrue(compareFiles(random, dRandom));
    }

    /**
     * Returns true if these files are equal
     * 
     * @throws FileNotFoundException if file cannot be found.
     */
    private boolean compareFiles (File f1, File f2) throws FileNotFoundException
    {
        String original = "";
        String decompressed = "";
        Scanner s = new Scanner(f1);
        while (s.hasNextLine())
        {
            original += s.nextLine();
        }
        s.close();

        Scanner p = new Scanner(f2);
        while (p.hasNextLine())
        {
            decompressed += p.nextLine();
        }
        p.close();
        return original.equals(decompressed);
    }

}
