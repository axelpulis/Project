package project;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Desktop;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Axel
 */
public class printPdf{
    

    
    
    
    
    
    
    public static void getPdf(String id,String fullname,String[] array) throws IOException {
    String DEST = "invoice/"+id+"_"+fullname+".pdf";
    File file= new File(DEST);
    file.getParentFile().mkdirs();
    new printPdf().createPdf(DEST,array);
    
    }
    
    
    
    public void createPdf (String dest,String[] array) throws IOException{
     FileOutputStream fos = new FileOutputStream(dest);
     
     PdfWriter writer = new PdfWriter(fos);
     
     PdfDocument pdf = new PdfDocument(writer);
     
     Document document = new Document(pdf);
     
     
     
     document.add(new Paragraph(array[0]+array[1]+array[2]+array[3]));
    
    document.close();
     
    if (Desktop.isDesktopSupported()) {
    try {
        File myFile = new File(dest);
        Desktop.getDesktop().open(myFile);
    } catch (IOException ex) {
        // no application registered for PDFs
    }
}
    
    }
    
}
