package edu.handong.csee.java.utils;

import java.io.*;
//import java.util.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;

import org.apache.tools.zip.*;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;

public class ZipReader {
	public static ArrayList<String> innerContext1 = new ArrayList<>();
	public static ArrayList<String> innerContext2 = new ArrayList<>();

	public static void readZip(String zipFilePath) {
		String filepath = zipFilePath.substring(0, zipFilePath.indexOf(".")) + "\\";

		Unziper.unZipIt(zipFilePath, zipFilePath.substring(0, zipFilePath.indexOf(".")));

		File unzipedFolder = new File(filepath);
		File[] unzipedFolderFiles = unzipedFolder.listFiles();

		try {
			for (File f : unzipedFolderFiles) {
				String innerZipPath = filepath + f.getName();

				ZipFile zipFile = new ZipFile(innerZipPath, "EUC-KR");

				Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();
				String stdID = f.getName().substring(0, f.getName().indexOf("."));

				int q = 1;
				while (entries.hasMoreElements()) {
					if (q == 1) {
						ZipArchiveEntry entry = entries.nextElement();
						String name = entry.getName();

						System.out.println(name);

						InputStream inputStream = zipFile.getInputStream(entry);

						ExcelReader Handleer_xlsx = new ExcelReader();
						innerContext1.addAll(Handleer_xlsx.getData(inputStream, stdID));

						inputStream.close();
						q = 2;
					} else {
						ZipArchiveEntry entry = entries.nextElement();
						String name = entry.getName();

						System.out.println(name);

						InputStream inputStream = zipFile.getInputStream(entry);

						ExcelReader Handleer_xlsx = new ExcelReader();
						innerContext2.addAll(Handleer_xlsx.getData(inputStream, stdID));

						inputStream.close();
						q = 1;
					}
				}

			}
		} catch (IOException e) {
			System.out.println(e);
		}

	}

}