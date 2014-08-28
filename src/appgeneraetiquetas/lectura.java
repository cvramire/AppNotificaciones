/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgeneraetiquetas;

/**
 *
 * @author Lucas Monce
 */
import static appgeneraetiquetas.sendsms.enviar;
import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class lectura {

    public lectura(File fileName) throws ParseException {
        List cellDataList = new ArrayList();
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
            XSSFSheet hssfSheet = workBook.getSheetAt(0);
            Iterator rowIterator = hssfSheet.rowIterator();
            while (rowIterator.hasNext()) {
                XSSFRow hssfRow = (XSSFRow) rowIterator.next();
                Iterator iterator = hssfRow.cellIterator();
                List cellTempList = new ArrayList();
                while (iterator.hasNext()) {
                    XSSFCell hssfCell = (XSSFCell) iterator.next();
                    cellTempList.add(hssfCell);
                }
                cellDataList.add(cellTempList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Leer(cellDataList);
    }

    private void Leer(List cellDataList) {
        String[] campos= new String[6];
        
        for (int i = 0; i < cellDataList.size(); i++) {
            List cellTempList = (List) cellDataList.get(i);
            for (int j = 0; j < cellTempList.size(); j++) {
                XSSFCell hssfCell = (XSSFCell) cellTempList.get(j);
                String stringCellValue = hssfCell.toString();
                System.out.print(stringCellValue +" ");
                campos[j]= stringCellValue;
            }
            enviar(campos[5],campos[4]);
            
            System.out.println();
        }
    }
    
    public static int contar(File fileName) throws ParseException {
        List cellDataList = new ArrayList();
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
            XSSFSheet hssfSheet = workBook.getSheetAt(0);
            Iterator rowIterator = hssfSheet.rowIterator();
            while (rowIterator.hasNext()) {
                XSSFRow hssfRow = (XSSFRow) rowIterator.next();
                Iterator iterator = hssfRow.cellIterator();
                List cellTempList = new ArrayList();
                while (iterator.hasNext()) {
                    XSSFCell hssfCell = (XSSFCell) iterator.next();
                    cellTempList.add(hssfCell);
                }
                cellDataList.add(cellTempList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return(cellDataList.size());
    }
    
    public static List obtener(File fileName) throws ParseException {
        List cellDataList = new ArrayList();
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
            XSSFSheet hssfSheet = workBook.getSheetAt(0);
            Iterator rowIterator = hssfSheet.rowIterator();
            while (rowIterator.hasNext()) {
                XSSFRow hssfRow = (XSSFRow) rowIterator.next();
                Iterator iterator = hssfRow.cellIterator();
                List cellTempList = new ArrayList();
                while (iterator.hasNext()) {
                    XSSFCell hssfCell = (XSSFCell) iterator.next();
                    cellTempList.add(hssfCell);
                }
                cellDataList.add(cellTempList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return(cellDataList);
    }

}
