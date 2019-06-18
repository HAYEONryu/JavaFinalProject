package edu.handong.csee.java.Writer;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;
import java.util.ArrayList;

import edu.handong.csee.java.utils.FileError;

public class filewriter {
	public static void writeFile(ArrayList<String> context, String ouputpath, int flg) {

		if(flg == 1)
			writesummaryfile(context, ouputpath);
		else
			writetablefile(context, ouputpath);
	}

	public static void writesummaryfile(ArrayList<String> summaryfile, String ouputpath) {
		int colums = 0;
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(ouputpath + "1" + ".csv");
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		for(int k = 0; k < summaryfile.size(); k++) {
			pw.println(summaryfile.get(k));
		}
		pw.close();
	}

	public static void writetablefile(ArrayList<String> tablefile, String ouputpath) {
		int colums = 0;
		try {
			BufferedWriter fw;
			fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ouputpath + "2" + ".csv"), "UTF-8"));
			for (String w : tablefile) {
				colums++;
				fw.write(w);
				//fw.write(",");
				fw.newLine();

			}
			fw.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
