package edu.handong.csee.java;

import edu.handong.csee.java.utils.*;
import edu.handong.csee.java.parser.*;

import java.io.*;
import org.apache.commons.cli.*;
import java.util.*;


public class Handler {
	String input;
	String output;
    boolean help;
    ArrayList<String> context;

	Options options = createOptions();

	public void run(String[] args) {
		//
		if(parseOptions(options, args)){
			if (help){
				printHelp(options);
				System.exit(0);
			}
			context=ZipReader.readzip(input);

		}
		
	}
	
	
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();
		
		try {
			
			CommandLine cmd = parser.parse(options, args);
			
			input = cmd.getOptionValue("i"); 
			output = cmd.getOptionValue("o");
			help = cmd.hasOption("h");
			
		} catch (Exception e) {
			printHelp(options);
			return false;
			}
		
		return true;
	}
	
	private Options createOptions() {
		Options options = new Options();
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("i").longOpt("input")
				.desc("Set an input file path")
				.hasArg()
				.argName("Input path")
				.required()
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("o").longOpt("output")
				.desc("Set an output file path")
				.hasArg()
				.argName("Output path")
				.required()
				.build());
		//help
		options.addOption(Option.builder("h").longOpt("help")
		          .desc("Show a Help page")
		          .argName("Help")
		          .build());
		return options;
		}
	
	private void printHelp(Options options)
	{
		HelpFormatter helper = new HelpFormatter();
		String header = "JavaFinalProject";
		String footer ="";
		helper.printHelp("JavaFinalProject", header, options, footer, true);
	}
}