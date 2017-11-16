package com.testframework.testdata;

import java.io.FileReader;


import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import au.com.bytecode.opencsv.CSVReader;
 
//import au.com.bytecode.opencsv.CSVReader;

public class ParseFullCSVExample
{
  public  String[] cols = null;
   public  String[] getcsvData(String csvFile, String tcID) throws Exception
   {
      //Build reader instance
      @SuppressWarnings("resource")
	 CSVReader reader = new CSVReader(new FileReader(csvFile), ',', '"', 1);
      //Read all rows at once
      List<String[]> allRows = reader.readAll();
    
      //Read CSV line by line and use the string array as you want
     for(String[] col : allRows){
        //System.out.println(row[0]);
        if (col[0].equalsIgnoreCase(tcID))
        {
        cols=col;
        //System.out.println(Arrays.toString(col));
        }
     } //Exit loop
     return cols;
   } //close method     
 
     
     @Test
   public void test001() throws Exception{
   //display filtered vals
   String[] retrow = getcsvData("./testdata/CSV/Test.csv","TC01");
   System.out.println("Filtered Array " + Arrays.toString(retrow));
   String t = retrow[1];
   System.out.println(t);
   }
}
