package com.tavavnt.wicket.report;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;


public class ReportDataProvider extends SortableDataProvider<Report, String> {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormater = new SimpleDateFormat("MM/dd/yyyy");
	SimpleDateFormat timeFormater = new SimpleDateFormat("HH:mm:ss");
	private List<Report>  reports = new ArrayList<Report>();
	public ReportDataProvider() {
	//	setSort("firstName", SortOrder.ASCENDING);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<? extends Report> iterator(long first, long count) {
		/*List<Report> reports = new ArrayList<Report>(WicketApplication.get()
				.getReports());*/
		if(null != getSort() && null != getSort().getProperty()){
			if(getSort().getProperty().endsWith("Date")){
				 Collections.sort(reports, getDateTimeComparator(getSort().getProperty(), dateFormater));
			}else if(getSort().getProperty().endsWith("Time")){
				 Collections.sort(reports, getDateTimeComparator(getSort().getProperty(), timeFormater));
			}else if(getSort().getProperty().equals("appid")){
				Collections.sort(reports, new Comparator<Report>() {
					@Override
					public int compare(Report o1, Report o2) {
						int sortOrder = getSort().isAscending() ? 1 : -1;
									return sortOrder
								* ObjectUtils.compare(parseStringToInteger(o1.getAppid()),parseStringToInteger(o2.getAppid()));
					}
				});
			}else if (getSort().getProperty().equals("appraisalValue")){
				Collections.sort(reports, new Comparator<Report>() {
					@Override
					public int compare(Report o1, Report o2) {
						int sortOrder = getSort().isAscending() ? 1 : -1;
						return sortOrder
								* ObjectUtils.compare(parseStringToBigDecimal(o1.getAppraisalValue()),parseStringToBigDecimal(o2.getAppraisalValue()));
					}
				});
			}else{
				if(!getSort().isAscending()){
					Collections.sort(reports,Collections.reverseOrder(new NullSafeBeanComparator(getSort().getProperty(),false)));
				}else{
					Collections.sort(reports,new NullSafeBeanComparator(getSort().getProperty(),false));
				}
				
			}
	   }
		return reports.subList((int) first,
				((int) Math.min(first + count, reports.size()))).iterator();
	}

	@Override
	public IModel<Report> model(Report object) {
		return new Model<Report>(object);
	}

	@Override
	public long size() {
		/*return WicketApplication.get().getReports().size();*/
		return reports.size();
	}
	private Comparator<Report> getDateTimeComparator(final String field,final SimpleDateFormat df){
		Comparator<Report> dateComparator = new Comparator<Report>(){
			int sortOrder = getSort().isAscending() ? 1 : -1;
			@Override
			public int compare(Report report0, Report report1) {
				String report0dateStr = (String) getPropertyFromName(report0, field);
				String report1dateStr = (String) getPropertyFromName(report1, field);
				Date report0date = parseDateString(report0dateStr,df);
				Date report1date = parseDateString(report1dateStr,df);
				if(null == report0date && null == report1date){
					return 0;
				}else if(null == report0date){
					return sortOrder*-1;
				}else if(null == report1date){
					return sortOrder*1;
				}else{
					return sortOrder*report0date.compareTo(report1date);
				}
			}
			
		};
		return dateComparator;
		
	}

	
	private Object getPropertyFromName(Report report,String name){
		Object value = null;
		try {
			value = PropertyUtils.getProperty(report, name);
		} catch (IllegalAccessException e) {
			System.out.println("Illegal access exception while retrieving property from field name ");
		} catch (InvocationTargetException e) {
			System.out.println("Invocation target exception while retrieving property from field name");
		} catch (NoSuchMethodException e) {
			System.out.println("No Such Method Exception while retrieving property from field name");
		}
		return value;
		
	}
	private Date parseDateString(String dateString , SimpleDateFormat df){
		Date result = null;
		try {
			if(StringUtils.isNotEmpty(dateString))
				result = df.parse(dateString);
		} catch (ParseException e) {
			System.out.println("Parse Error while parsing dates");
		}
		return result;
	}
	private Integer parseStringToInteger(String value){
		Integer intValue = null;
		if(StringUtils.isNotEmpty(value)){
			try{
			intValue = Integer.parseInt(value);
			}catch(NumberFormatException e){
				System.out.println("number format exception while parsing string value");
			}
		}
		return intValue;
	}
	private BigDecimal parseStringToBigDecimal(String value){
		BigDecimal bgvalue = null;
		if(StringUtils.isNotEmpty(value)){
			try{
				bgvalue = new BigDecimal(value);
			}catch(Exception e){
				System.out.println("number format exception while parsing string value");
			}
		}
		return bgvalue;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public List<Report> getReports() {
		return reports;
	}

}
