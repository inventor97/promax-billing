package com.inventor.utils;

import javax.print.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class printDoc {

    public static void initPrint(String file) throws FileNotFoundException {
        FileInputStream in = new FileInputStream(file);
        Doc doc = new SimpleDoc(in, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();

        try {
            service.createPrintJob().print(doc, null);
        } catch (PrintException e) {
            e.printStackTrace();
        }
    }
}
