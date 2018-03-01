package com.tavavnt;

import com.tavant.wicket.model.DayLineVO;

public class test {
	public static void main(String[] args){
		DayLineVO vo = new DayLineVO("HE", "Monday", "Tuesday", "08","00","AM","12","30","PM", 0);
		DayLineVO vo1 = null;
		try {
			vo1 = (DayLineVO) vo.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		System.out.println(vo1.getCode() + " " + vo1.getDisplayOrder() + " " );
		System.out.println(" " +vo1.getStartDay()+ " " +vo1.getStartHour() + " "+vo1.getStartMin()+ " "+vo1.getStartAMPM());
		System.out.println(" " +vo1.getEndDay()+ " " +vo1.getEndHour() + " "+vo1.getEndMin()+ " "+vo1.getEndAMPM());
	}
}
