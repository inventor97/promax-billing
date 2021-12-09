package com.inventor.utils;

import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class printDoc {

    public static void initPrint(String file) throws FileNotFoundException {
        try{
            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            PrintService printService[] =
                    PrintServiceLookup.lookupPrintServices(flavor, pras);
            PrintService defaultService =
                    PrintServiceLookup.lookupDefaultPrintService();
            if (defaultService != null) {
                DocPrintJob job = defaultService.createPrintJob();
                FileInputStream fis = new FileInputStream(file);
                DocAttributeSet das = new HashDocAttributeSet();
                Doc doc = new SimpleDoc(fis, flavor, das);
                job.print(doc, pras);

            }
        }
        catch(PrintException a){
            System.out.println(a.getLocalizedMessage());
        }
    }
}
