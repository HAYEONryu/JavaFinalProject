package edu.handong.csee.java.Writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

import edu.handong.csee.java.utils.FileError;

public class filewriter {
	public static void writeFile(ArrayList<String> context, String ouputpath) {
		int i = 0;
		ArrayList<String> summaryfile=new ArrayList<String>(); 
		ArrayList<String> tablefile=new ArrayList<String>();
		while(context.get(i+1) == null) {
			if(Integer.parseInt(context.get(i)) == 7) {
				while(context.get(i) != "#") {
					i++;
					summaryfile.add(context.get(i));
				}
				
			}
			if(Integer.parseInt(context.get(i)) == 5) {
				while(context.get(i) != "#") {
					i++;
					tablefile.add(context.get(i));
				}
			}
			try {
				if(Integer.parseInt(context.get(i)) != 7 || Integer.parseInt(context.get(i)) != 5)
					throw new FileError();
			}catch(FileError e) {
				System.out.println(e.getMessage());
			} 
		}
		
		writesummaryfile(summaryfile, ouputpath);
		writetablefile(tablefile, ouputpath);
	}
	public static void writesummaryfile(ArrayList<String> summaryfile, String ouputpath) {
		int colums = 0;
		try{
			BufferedWriter fw;
			if(ouputpath.contains("C:\\"))
			{
				fw = new BufferedWriter(new FileWriter(ouputpath+"1"+ ".csv", true));
			}
			else
			{
				fw = new BufferedWriter(new FileWriter(ouputpath+".csv", true));
			}
		for(String w : summaryfile){
			colums++;
			fw.write(w);
			fw.write(",");
			if( colums%8 == 0) {
				fw.newLine();
			}
		}
		fw.close();
		}catch (Exception e) {
		
			e.printStackTrace();
		}		
	}
	public static void writetablefile(ArrayList<String> tablefile, String ouputpath) {
		int colums = 0;
		try{
			BufferedWriter fw;
			if(ouputpath.contains("C:\\"))
			{
				fw = new BufferedWriter(new FileWriter(ouputpath+"2"+".csv", true));
			}
			else
			{
				fw = new BufferedWriter(new FileWriter(ouputpath+".csv", true));
			}
		for(String w : tablefile){
			colums++;
			fw.write(w);
			fw.write(",");
			if( colums%6 == 0) {
				fw.newLine();
			}
		}
		fw.close();
		}catch (Exception e) {
		
			e.printStackTrace();
		}		
	}
}
