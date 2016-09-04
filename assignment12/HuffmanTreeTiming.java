package assignment12;

import java.io.File;

/**
 * Class provides size ratio analysis for huffman compression.
 * @author Sara Adamson, Minh Pham
 */
public class HuffmanTreeTiming
{
    public static void main (String[] args)
    {
        HuffmanTree t = new HuffmanTree();
        // original files
        File unique1 = new File("unique1");
        File unique2 = new File("unique2");
        File unique4 = new File("unique4");
        File unique8 = new File("unique8");
        File unique10 = new File("unique10");
        File unique20 = new File("unique20");
        File unique40 = new File("unique40");
        File unique60 = new File("unique60");
        File unique80 = new File("unique80");
        File unique90 = new File("unique90");
        // compressed files
        File cunique1 = new File("cunique1");
        File cunique2 = new File("cunique2");
        File cunique4 = new File("cunique4");
        File cunique8 = new File("cunique8");
        File cunique10 = new File("cunique10");
        File cunique20 = new File("cunique20");
        File cunique40 = new File("cunique40");
        File cunique60 = new File("cunique60");
        File cunique80 = new File("cunique80");
        File cunique90 = new File("cunique90");
        // decompressed files
        File dunique1 = new File("dunique1");
        File dunique2 = new File("dunique2");
        File dunique4 = new File("dunique4");
        File dunique8 = new File("dunique8");
        File dunique10 = new File("dunique10");
        File dunique20 = new File("dunique20");
        File dunique40 = new File("dunique40");
        File dunique60 = new File("dunique60");
        File dunique80 = new File("dunique80");
        File dunique90 = new File("dunique90");

        double u1, u2, u4, u8, u10, u20, u40, u60, u80, u90;

        t.compressFile(unique1, cunique1);
        t.decompressFile(cunique1, dunique1);
        
        t.compressFile(unique2, cunique2);
        t.decompressFile(cunique2, dunique2);
        
        t.compressFile(unique4, cunique4);
        t.decompressFile(cunique4, dunique4);
        
        t.compressFile(unique8, cunique8);
        t.decompressFile(cunique8, dunique8);

        t.compressFile(unique10, cunique10);
        t.decompressFile(cunique10, dunique10);

        t.compressFile(unique20, cunique20);
        t.decompressFile(cunique20, dunique20);

        t.compressFile(unique40, cunique40);
        t.decompressFile(cunique40, dunique40);

        t.compressFile(unique60, cunique60);
        t.decompressFile(cunique60, dunique60);

        t.compressFile(unique80, cunique80);
        t.decompressFile(cunique80, dunique80);

        t.compressFile(unique90, cunique90);
        t.decompressFile(cunique90, dunique90);

        u1 = (double) cunique1.length() / unique1.length();
        u2 = (double) cunique2.length() / unique2.length();
        u4 = (double) cunique4.length() / unique4.length();
        u8 = (double) cunique8.length() / unique8.length();
        u10 = (double) cunique10.length() / unique10.length();
        u20 = (double) cunique20.length() / unique20.length();
        u40 = (double) cunique40.length() / unique40.length();
        u60 = (double) cunique60.length() / unique60.length();
        u80 = (double) cunique80.length() / unique80.length();
        u90 = (double) cunique90.length() / unique90.length();

        System.out.println("unique1: \t" + u1);
        System.out.println("unique2: \t" + u2);
        System.out.println("unique4: \t" + u4);
        System.out.println("unique8: \t" + u8);
        System.out.println("unique10: \t" + u10);
        System.out.println("unique20: \t" + u20);
        System.out.println("unique40: \t" + u40);
        System.out.println("unique60: \t" + u60);
        System.out.println("unique80: \t" + u80);
        System.out.println("unique90: \t" + u90);

    }

}
