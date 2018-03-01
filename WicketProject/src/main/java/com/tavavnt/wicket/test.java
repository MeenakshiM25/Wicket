package com.tavavnt.wicket;

import java.text.MessageFormat;

public class test {
	
	public static void main(String[] args){
//		String tempPassword = "";
//		String tempStr = "Tavant122";
//		for (int i = 0; i < tempStr.length() && tempPassword.length() < 8; i++) {
//			if (Pattern.matches("[0-9a-zA-Z]", tempStr.charAt(i) + "")) {
//				tempPassword += (tempStr.charAt(i));
//			}
//		}
//		System.out.println("temperory password"+tempPassword);
		
		String phNoConf = "1-866-601-6543";
/*		ArrayList<Report> reports = new ArrayList<Report>();
		reports.add(new Report("John", "Homeowner", 1231, "11",234233L,"01/25/2014","01:23:11","01/25/2014","01:23:11",new BigDecimal("12.22"),"1.11"));
		reports.add(new Report("Andy", "America", 2322,"", 312312L,"01/25/2015",null,"01/25/2013","01:22:11",new BigDecimal("11.22"),"0"));
		reports.add(new Report("Suzi", "Builder", 5432,null, 78655L,null,"01:13:11","01/21/2014","01:21:11",new BigDecimal("2.22"),"765.3"));
		reports.add(new Report("Ami", "America", 7434,"233", null,"01/05/2014","01:03:11","03/25/2014","01:13:11",new BigDecimal("12.222"),"23.222"));
		reports.add(new Report("A", null, 3122, "098",134233L,"04/25/2014","01:23:17","01/25/2015","03:23:11",null,"344.11"));
		reports.add(new Report("Wicket", "America", null,"985", 512312L,"01/25/2012","01:23:21","01/25/2011","05:23:11",new BigDecimal("232.22"),""));
		reports.add(new Report("Man", "Builder", 5432, "53455",88655L,"01/25/2011","01:13:31",null,"01:13:01",new BigDecimal("15.22"),null));
		reports.add(new Report("Jetty", "JJ", 6434,"74666",97324L,"01/06/2014","01:23:41","06/15/2014",null,new BigDecimal("16.22"),"4.098"));
		System.out.println("Output:"+phNoConf.substring(2,14));
		Comparator<Report> assignedToComparator = new Comparator<Report>(){
			public int compare(Report result1, Report result2) {
					String value1 = result1.getLastName() ;
					String value2 = result2.getLastName() ;
					
					if (value1 == null) {
						return  1;
					}
					if(value1.equals("JJ")){
						return -1;
					}
					if (value2 == null) {
						return -1;
					}
					if(value2.equals("JJ")){
						return  1;
					}
					
					return value1.compareTo(value2);
				
			};
		};
		Collections.sort(reports, assignedToComparator);
		
		for(Report report:reports){
			System.out.println("Report    :"+report.getLastName());
		}*/
		Long num = Long.parseLong("0440157188");
		String msg = "Loan ID {0} Loan Number {1} will be submitted to the Disclosure Team";
//		System.out.println(MessageFormat.format("Loan ID {0} with Los Application Number {1} will be submitted to the Disclosure Team","12345", num));
/*		String msg1= "Disclosure Submit Warning for Loan ID {0} Loan Number {1}";
	    String msg = "Loan ID {0} Loan Number {1} will be submitted to the Disclosure Team";
	    String msg2= "Loan ID {0} Loan Number {1} will be auto-submitted to UniFi";
	    System.out.println(msg.replace("Loan Number {1}", ""));
	    System.out.println(msg1.replace("Loan Number {1}", ""));
	    System.out.println(msg2.replace("Loan Number {1}", ""));*/
		System.out.println(MessageFormat.format(msg,new Object[]{"12345","< >"}));
	}
}
