package edu.handong.csee.java.utils;

import java.io.*;
import java.util.*;
import java.util.zip.*;

public class ZipReader {

	public static ArrayList<String> readzip(String zipFilePath) {
        String filepath = zipFilePath + "/";
		ArrayList<String> context = new ArrayList<>();
		try {
			ZipFile zipFile = new ZipFile(zipFilePath);
			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				String name = entry.getName();
				name = name.substring(0, name.indexOf("."));
				filepath = filepath + name;

				ExcelReader Handleer_xlsx = new ExcelReader();
				context.addAll(Handleer_xlsx.getData(filepath ,name ));
			}
			zipFile.close();
			} catch (IOException ex) {
				System.out.println(ex);
				}
		return context;
	}
}
