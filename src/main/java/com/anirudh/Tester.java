package com.anirudh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class Tester {

	
	private static String app1="~prdapp1 mdccrmprdapp01 mdccrmprdapp02 mdccrmprdapp03 mdccrmprdapp04 mdccrmprdapp05 mdccrmprdapp07 mdccrmprdapp08 mdccrmprdapp09 mdccrmprdapp10";
	private static String app2="~prdapp2 mdccrmprdapp11 mdccrmprdapp12 mdccrmprdapp13 mdccrmprdapp14 mdccrmprdapp15 mdccrmprdapp17 mdccrmprdapp18 mdccrmprdapp19 mdccrmprdapp20";
	private static String app3="~prdapp3 mdccrmprdapp21 mdccrmprdapp22 mdccrmprdapp23 mdccrmprdapp24 mdccrmprdapp25 mdccrmprdapp27 mdccrmprdapp28 mdccrmprdapp29 mdccrmprdapp30";
	
	private static String igw1="~prdigw1 mdccrmprdigp01 mdccrmprdigp02 mdccrmprdigp03 mdccrmprdigp04 mdccrmprdigp05 mdccrmprdigp06 mdccrmprdigp07 mdccrmprdigp08 mdccrmprdigp09 mdccrmprdigp10 mdccrmprdigp11 mdccrmprdigp12";
	private static String igw2="~prdigw2 mdccrmprdigp13 mdccrmprdigp14 mdccrmprdigp15 mdccrmprdigp16 mdccrmprdigp17 mdccrmprdigp18 mdccrmprdigp19 mdccrmprdigp20 mdccrmprdigp21 mdccrmprdigp22 mdccrmprdigp23 mdccrmprdigp24";
	private static String igw3="~prdigw3 mdccrmprdigp25 mdccrmprdigp26 mdccrmprdigp27 mdccrmprdigp28 mdccrmprdigp29 mdccrmprdigp30 mdccrmprdigp31 mdccrmprdigp32 mdccrmprdigp33 mdccrmprdigp34 mdccrmprdigp34 mdccrmprdigp36";
	
	private static String ivractv="~prdivra mdccrmprdivp01 mdccrmprdivp02 mdccrmprdivp03 mdccrmprdivp04 mdccrmprdivp05 mdccrmprdivp06";
	private static String ivrpsv="~prdivrp mdccrmprdivp01 mdccrmprdivp02 mdccrmprdivp03 mdccrmprdivp04 mdccrmprdivp05 mdccrmprdivp06";
	
	private static String cih="~prdcih mdccrmprdcip01 mdccrmprdcip02 mdccrmprdcip03";
	
	private static String jms="~prdjms1 mdccrmprdjmp01 mdccrmprdjmp02 mdccrmprdjmp03";
	
	private static String bap1="~prdbap1 mdccrmprdbpp01";
	private static String bap2="~prdbap2 mdccrmprdbpp01";
	private static String bap3="~prdbap3 mdccrmprdbpp01";
	private static String bap4="~prdbap4 mdccrmprdbpp02";
	private static String bap5="~prdbap5 mdccrmprdbpp02";
	private static String bap6="~prdbap6 mdccrmprdbpp02";
	
	
	
	private static String user="ffukushi";
	private static String ppk="c:\\prod_key\\ssh_key.ppk";
	private static String plink_path="C:\\Program Files (x86)\\PuTTY\\plink.exe";
	
	private static HashMap checkMap=new HashMap();
	
	private static File outFile=null;
	private static FileWriter fsw=null;
	
	public static void main(String[] args) throws Exception {
	
		
		
//		Process process=Runtime.getRuntime().exec(
//				"C:\\Program Files (x86)\\PuTTY\\plink.exe -i c:\\prod_key\\ssh_key.ppk  mdccrmprdivp01 -l ffukushi \"cksum ~prdivra/JEE/CRMProduct/applications/ShareApplication/*.*\"");
//        BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
//        String line;
//        while (true) {
//            line = r.readLine();
//            if (line == null) { break; }
//            System.out.println(line);
//        }
		outFile=new File("c:/temp/cksum/exec_log_conf_7_22.log");
		fsw=new FileWriter(outFile);
		loadMap("c:\\temp\\cksum\\master_conf.txt");		
		ArrayList domainLst=new ArrayList();
		domainLst.add(app1);
		domainLst.add(app2);
		domainLst.add(app3);
		domainLst.add(igw1);
		domainLst.add(igw2);
		domainLst.add(igw3);
		domainLst.add(ivractv);
		domainLst.add(ivrpsv);
		domainLst.add(cih);
		domainLst.add(jms);
		domainLst.add(bap1);
		domainLst.add(bap2);
		domainLst.add(bap3);
		domainLst.add(bap4);
		domainLst.add(bap5);
		domainLst.add(bap6);
		
		domainLst.forEach(S ->{
			try {				
				execFor(S.toString(),"cd ","/JEE/CRMProduct/applications/ShareApplication && cksum *.ear | awk '{print $1,$3}'");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		
		
		
		
		
		fsw.close();
		
	}
	
	
	private static void execFor(String clsuter,String command,String cmdParam) throws IOException{
		String servers[]= clsuter.split(" ");
		Process process;
		for (int i = 1; i < servers.length; i++) {
			System.out.println("Processing " + servers[i]);
			process=Runtime.getRuntime().exec(
			new StringBuffer().append(plink_path).append(" -batch  -i ").append(ppk).append(" ").append(servers[i]).append(" -l ").append(user).
			append(" ").append(command).append(servers[0]).append(cmdParam).toString()
			);
			 String line;
			  BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
		      int lines=0;  		      
			  while (true) {		        	
		            line = r.readLine();
		            System.out.println("Command output=" + line);
		            if (line == null) { break; }
		            System.out.println("Command output=" + line);
		            if(checkMap.containsKey(line.split(" ")[1]) && checkMap.containsValue(line.split(" " )[0])){
		            	//System.out.println( "Lines " + (lines++)+ " " +servers[i]  + "- Matched " + line.split(" ")[1] + " - " + line.split(" ")[0]);
		            	fsw.write("\n Lines " + (lines++)+ " ::" +servers[i]  + "- Matched " + line.split(" ")[1] + " - " + line.split(" ")[0]);
		            }else{
		            	fsw.write("\n Lines " + (lines++)+ " ::" +servers[i]  + "- ENTRY NOT FOUND OR MATCHED " + line.split(" ")[1] + " - " + line.split(" ")[0]);
		            }
		        }
			  fsw.write("\n Total lines in master=" +checkMap.size() + " Compared " + i  );
			  fsw.write("********************************************************************" );
		}
	}
	
	private static void loadMap(String fileName)throws Exception{
//		String fileName = "c://lines.txt";
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line;
		while((line = reader.readLine()) != null){
			checkMap.put(line.split(" ")[1], line.split(" ")[0]);
		}
	}
	
}
