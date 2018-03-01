package com.tavant.wicket.common.component;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.tavant.wicket.model.DayLineVO;

public class TimeDisplayPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TimeDisplayPanel(String id, IModel<DayLineVO> model) {
		super(id, model);
		createDisplayPanel();
		
	}
	private void createDisplayPanel() {
			final DayLineVO dayLineVO = (DayLineVO) this.getDefaultModelObject();
			Label timeDisplayLabel = new Label("timeDisplayLabel",new Model<String>(){
				private static final long serialVersionUID = 1L;
				@Override
				public String getObject() {
					return getDisplayText(dayLineVO);
				}
			});
			timeDisplayLabel.setEscapeModelStrings(false);
			add(timeDisplayLabel);
	}
	protected String getDisplayText(DayLineVO dayLineVO) {
		if("Closed".equals(dayLineVO.getEndDay())){
			return getClosedDayText(dayLineVO);
		}else{
			return getStartAndEndDayText(dayLineVO);
		}
	}
	private String getStartAndEndDayText(DayLineVO dayLineVO) {
		StringBuffer sb = new StringBuffer();
		sb.append("<span class='wid80 mrgRight10'>"+ dayLineVO.getStartDay()+ "</span>");
		if(StringUtils.isNotEmpty(dayLineVO.getEndDay())){
			sb.append("-");
		}else{
			sb.append(" ");
		}
		sb.append("<span class='wid80 mrgLeft20'>"+ dayLineVO.getEndDay()+ "</span>")
		.append(" <span class='pull-right'>"+ getTimeLabel(dayLineVO.getStartHour(),dayLineVO.getStartMin(), dayLineVO.getStartAMPM()))
		.append(" until ")
		.append(getTimeLabel(dayLineVO.getEndHour(),dayLineVO.getEndMin(),dayLineVO.getEndAMPM()+"</span>"));
		return sb.toString();
	}

	private String getClosedDayText(DayLineVO dayLineVO) {
		StringBuffer sb = new StringBuffer();
		sb.append("Closed ").append(dayLineVO.getStartDay());
		return sb.toString();
	}

	private Object getTimeLabel(String startHour, String startMin,
			String startAMPM) {
		return startHour +":"+startMin + startAMPM;
	}
	
}
