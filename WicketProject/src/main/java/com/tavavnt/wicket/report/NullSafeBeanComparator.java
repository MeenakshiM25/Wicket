package com.tavavnt.wicket.report;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.comparators.ComparableComparator;

public class NullSafeBeanComparator extends BeanComparator {

	private static final long serialVersionUID = -5160372143871897079L;
	private boolean nullsAreHigh;

	public NullSafeBeanComparator() {
		this(null);
	}

	public NullSafeBeanComparator(String property) {
		this(property, ComparableComparator.getInstance());
	}
	public NullSafeBeanComparator(String property,boolean nullsAreHigh) {
		this(property, ComparableComparator.getInstance() , nullsAreHigh);
	}

	public NullSafeBeanComparator(String property, Comparator comparator) {
		this(property, comparator, true);
	}

	public NullSafeBeanComparator(String property, Comparator comparator, boolean nullsAreHigh) {
		super(property, comparator);
		this.nullsAreHigh = nullsAreHigh;
	}

	public int compare(Object o1, Object o2) {
		if (getProperty() == null) {
			// compare the actual objects
			return getComparator().compare(o1, o2);
		}

		try {
			Object value1 = PropertyUtils.getProperty(o1, getProperty());
			Object value2 = PropertyUtils.getProperty(o2, getProperty());
			if(value1 == value2) { return 0; }
	        if(value1 == null) { return (this.nullsAreHigh ? 1 : -1); }
	        if(value2 == null) { return (this.nullsAreHigh ? -1 : 1); }
			return getComparator().compare(value1, value2);
		} catch (IllegalAccessException iae) {
			throw new RuntimeException("IllegalAccessException: "
					+ iae.toString());
		} catch (InvocationTargetException ite) {
			throw new RuntimeException("InvocationTargetException: "
					+ ite.toString());
		} catch (NoSuchMethodException nsme) {
			throw new RuntimeException("NoSuchMethodException: "
					+ nsme.toString());
		}
	}

	public boolean isNullsAreHigh() {
		return nullsAreHigh;
	}

	public void setNullsAreHigh(boolean nullsAreHigh) {
		this.nullsAreHigh = nullsAreHigh;
	}
}