package com.tavavnt.wicket;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

import com.tavavnt.wicket.pullreport.PullThruReportVO;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see com.tavavnt.wicket.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	  public List<PullThruReportVO> reportVOs = new ArrayList<PullThruReportVO>();

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

		// add your configuration here
	}
	
	public  WicketApplication(){
		 PullThruReportVO fooA = new PullThruReportVO("A");
	        {
	        	PullThruReportVO fooAA = new PullThruReportVO(fooA, "AA");
	            {
	            	new PullThruReportVO(fooAA, "AAB");
	                new PullThruReportVO(fooAA, "AAA");
	               
	            }
	            PullThruReportVO fooAB = new PullThruReportVO(fooA, "AB");
	            {
	                new PullThruReportVO(fooAB, "ABA");
	                PullThruReportVO fooABB = new PullThruReportVO(fooAB, "ABB");
	                {
	                    new PullThruReportVO(fooABB, "ABBA");
	                    PullThruReportVO fooABBB = new PullThruReportVO(fooABB, "ABBB");
	                    {
	                        new PullThruReportVO(fooABBB, "ABBBA");
	                    }
	                }
	                new PullThruReportVO(fooAB, "ABD");
	                new PullThruReportVO(fooAB, "ABC");
	            }
	            PullThruReportVO fooAC = new PullThruReportVO(fooA, "AC");
	            {
	               
	                new PullThruReportVO(fooAC, "ACB");
	                new PullThruReportVO(fooAC, "ACA");
	            }
	        }
	        

	        PullThruReportVO fooB = new PullThruReportVO("B");
	        {
	           
	            new PullThruReportVO(fooB, "BB");
	            new PullThruReportVO(fooB, "BA");
	        }
	      

	        PullThruReportVO fooC = new PullThruReportVO("C");
	        reportVOs.add(fooC);
	        reportVOs.add(fooB);
	        reportVOs.add(fooA);
	}
	 public PullThruReportVO getReportVO(String id)
	    {
	        return findReportVO(reportVOs, id);
	    }

	    private static PullThruReportVO findReportVO(List<PullThruReportVO> foos, String id)
	    {
	        for (PullThruReportVO foo : foos)
	        {
	            if (foo.getId().equals(id))
	            {
	                return foo;
	            }

	            PullThruReportVO temp = findReportVO(foo.getHierarchicalInfo(), id);
	            if (temp != null)
	            {
	                return temp;
	            }
	        }

	        return null;
	    }
	    public static WicketApplication get()
	    {
	        return (WicketApplication)WebApplication.get();
	    }
}
