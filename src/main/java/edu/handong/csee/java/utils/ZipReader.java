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
	public static ArrayList<String> innerContext = new ArrayList<>();
	
	public static void readZip(String zipFilePath) {
		String filepath = zipFilePath.substring(0, zipFilePath.indexOf(".")) + "\\";

		Unziper.unZipIt(zipFilePath, zipFilePath.substring(0, zipFilePath.indexOf(".")));

		File unzipedFolder = new File(filepath);
		File[] unzipedFolderFiles = unzipedFolder.listFiles();

		try {
			for (File f : unzipedFolderFiles) {
				String innerZipPath = filepath + f.getName();
				/*
				ZipInputStream in = new ZipInputStream(new FileInputStream(innerZipPath));
		        ZipEntry entry = null;
		        while((entry = in.getNextEntry()) != null ){
		            System.out.println(entry.getName()+" 파일이 들어 있네요.");
		        }
		        */

		        //Charset CP866 = Charset.forName("CP866");				
				ZipFile zipFile = new ZipFile(innerZipPath, "EUC-KR");
				
				
				//byte[] buf = new byte[65536];

				Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();

				while (entries.hasMoreElements()) {
					ZipArchiveEntry entry = entries.nextElement();
					String name = entry.getName();
					String stdID = name.substring(0, name.indexOf("."));
					String absFilePath = "";
					
					System.out.println(name);
					

					InputStream inputStream = zipFile.getInputStream(entry);
					
					ExcelReader Handleer_xlsx = new ExcelReader();
					innerContext.addAll(Handleer_xlsx.getData(inputStream, stdID));
					innerContext.add("#");
					
					/*
					BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
					String line;
					StringBuffer response = new StringBuffer(); 
					while((line = rd.readLine()) != null) {
						//System.out.println(line);
						response.append(line);
						response.append('\r');
					}
					*/
					

					//System.out.println(response);
					
					/*
					File tempFile = new File(innerZipPath);
					
					absFilePath = tempFile.getAbsolutePath() +"\\"+name;
					
					System.out.println(absFilePath);
					
					ExcelReader Handleer_xlsx = new ExcelReader();
					innerContext.addAll(Handleer_xlsx.getData(absFilePath, stdID));
					innerContext.add("#");
					*/
				}
				
			}
		}
		catch(IOException e) {
			System.out.println(e);
		}
		

	}
	
	
	
	/*
	public static ArrayList<String> readzip(String zipFilePath) {

		String filepath = zipFilePath + "\\";
		ArrayList<String> context = new ArrayList<>();

		try {
			ZipFile zipFile = new ZipFile(zipFilePath);
			Enumeration<? extends ZipArchiveEntry> entries = zipFile.entries();

			while (entries.hasMoreElements()) {
				ZipArchiveEntry entry = entries.nextElement();
				String name = entry.getName();
				String stdID = name.substring(0, name.indexOf("."));
				InputStream input = zipFile.getInputStream(entry);

				System.out.println(name);
				//File dirFile = new File(filepath + name); ZipFile zipFile_inner = new
				ZipFile(filepath + name);
				while (inner_entries.hasMoreElements()) {
					ZipEntry inner_entry = inner_entries.nextElement();
					String name_of_inner_zip_file = inner_entry.getName();

					File dirFile = new File(filepath + name + "\\" + name_of_inner_zip_file);

					String absFilePath = dirFile.getAbsolutePath();

					ExcelReader Handleer_xlsx = new ExcelReader();
					context.addAll(Handleer_xlsx.getData(absFilePath, stdID));
					context.add("#");

				}
				zipFile_inner.close();

			}
			zipFile.close();
		} catch (IOException ex) {
			System.out.println(ex);
		}
		return context;
	}
	
	
	public static ArrayList<String> filesinzip(String zipFilePath) {
		

		String filepath = zipFilePath + "\\";
		ArrayList<String> context = new ArrayList<>();

		try {
			ZipFile zipFile = new ZipFile(zipFilePath);
			Enumeration<? extends ZipEntry> entries = zipFile.entries();

			while (entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				String name = entry.getName();
				String stdID = name.substring(0, name.indexOf("."));

				System.out.println(name);
				FileInputStream fis = new FileInputStream(filepath + name);
				BufferedInputStream bis = new BufferedInputStream(fis);
				ZipInputStream zipStream = new ZipInputStream(bis);

				//File dirFile = new File(filepath + name); ZipFile zipFile_inner = new
				ZipFile(filepath + name);
				Enumeration<? extends ZipEntry> inner_entries = zipFile_inner.entries();
				while (inner_entries.hasMoreElements()) {
					ZipEntry inner_entry = inner_entries.nextElement();
					String name_of_inner_zip_file = inner_entry.getName();

					File dirFile = new File(filepath + name + "\\" + name_of_inner_zip_file);

					String absFilePath = dirFile.getAbsolutePath();

					ExcelReader Handleer_xlsx = new ExcelReader();
					context.addAll(Handleer_xlsx.getData(absFilePath, stdID));
					context.add("#");

				}
				zipFile_inner.close();

			}
			zipFile.close();
		} catch (IOException ex) {
			System.out.println(ex);
		}
		return context;
	}
	
	
	-------------------------------------------------------------
	

	public static void lookupZip(InputStream fileInputStream) throws IOException {
		ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
		String entryName = "";
		ZipEntry entry = zipInputStream.getNextEntry();

		while (entry != null) {
			entryName = entry.getName();
			if (entryName.endsWith("zip")) {
				lookupZip(zipInputStream);
			}
			String stdID = entryName.substring(0, entryName.indexOf("."));

			// ExcelReader Handleer_xlsx = new ExcelReader();
			// innerContext.addAll(Handleer_xlsx.getData(absFilePath, stdID));
			// innerContext.add("#");

			entry = zipInputStream.getNextEntry();
		}
	}

	
	 

	public static void readZipFileRecursive(final Path zipFile) {
		try (final InputStream zipFileStream = Files.newInputStream(zipFile)) {
			readZipFileStream(zipFileStream);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static void readZipFileStream(final InputStream zipFileStream) {
		final ZipInputStream zipInputStream = new ZipInputStream(zipFileStream);
		ZipEntry zipEntry;
		try {
			while ((zipEntry = zipInputStream.getNextEntry()) != null) {
				System.out.println(zipEntry.getName());
				if (!zipEntry.isDirectory() && zipEntry.getName().endsWith(".zip")) {
					readZipFileStream(zipInputStream); // recursion
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
*/
}