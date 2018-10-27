package com.anirudh;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import com.logscanner.config.ConfigReader;

public class TestSCanner {
	public static void main1(String[] args) throws Exception {
		ScanLogs sl=new ScanLogs();
		sl.setKeyword("createBAN");
		ArrayList lst=new ArrayList();
		HashMap m=ConfigReader.readConfig("c:\\temp\\serverscan.json");
		lst.add(m.get("CRMAPP1"));
		lst.add(m.get("CRMAPP2"));
		lst.add(m.get("CRMAPP3"));
		
		sl.scanComponent(lst);
	}

	public static void main(String[] args)throws Exception {
		
		ScanLogs sl=new ScanLogs();		
		ArrayList lst=new ArrayList();
		HashMap m=ConfigReader.readConfig("c:\\temp\\serverscan.json");
		lst.add(m.get("CRMAPP1"));
		lst.add(m.get("CRMAPP2"));
		lst.add(m.get("CRMAPP3"));
		//sl.exexCmd4Grp(lst,"df -h");
		sl.setKeyword("V21");
		sl.scanComponent(lst);
		System.out.println(Files.readAllLines(Paths.get("c:/temp/cksum/scan_log.txt")));
	}
	
}
