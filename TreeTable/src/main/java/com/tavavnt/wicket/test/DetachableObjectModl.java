package com.tavavnt.wicket.test;

import org.apache.wicket.model.LoadableDetachableModel;

import com.tavavnt.wicket.WicketApplication;
import com.tavavnt.wicket.pullreport.PullThruReportVO;


public class DetachableObjectModl extends LoadableDetachableModel<PullThruReportVO>
{
    private final String id;

   
    /**
     * @param c
     */
    public DetachableObjectModl(PullThruReportVO c)
    {
        this(c.getId());
    }

    /**
     * @param id
     */
    public DetachableObjectModl(String id)
    {
        
        this.id = id;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        return Long.valueOf(id).hashCode();
    }

    /**
     * used for dataview with ReuseIfModelsEqualStrategy item reuse strategy
     * 
     * @see org.apache.wicket.markup.repeater.ReuseIfModelsEqualStrategy
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        else if (obj == null)
        {
            return false;
        }
        else if (obj instanceof DetachableObjectModl)
        {
        	DetachableObjectModl other = (DetachableObjectModl)obj;
            return other.id == id;
        }
        return false;
    }

    /**
     * @see org.apache.wicket.model.LoadableDetachableModel#load()
     */
    @Override
    protected PullThruReportVO load()
    {
        // loads contact from the database
        return WicketApplication.get().getReportVO(id);
    }
}