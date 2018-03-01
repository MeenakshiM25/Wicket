package com.tavavnt.wicket;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigationToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NoRecordsToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.model.AbstractCheckBoxModel;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.JavaScriptUrlReferenceHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import com.tavavnt.wicket.modalwindow.ClickToCallPOPUP;
import com.tavavnt.wicket.modalwindow.CustomModalWindow;
import com.tavavnt.wicket.report.CheckBoxColumn;
import com.tavavnt.wicket.report.RadioButtonColumn;
import com.tavavnt.wicket.report.Report;
import com.tavavnt.wicket.report.ReportDataProvider;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;
	private ReportDataProvider provider = new ReportDataProvider();
	private List<Report> selectedReports = new ArrayList<Report>();
	Form<?> form;
	TextField<String> textfield;
	RadioGroup<Report> radioGroup;
	private String labeltext,rawtext,searchText;
	private Report selectedReport;
	private DataTable<Report, String> dataTable;
    private CustomModalWindow modalWindow;
    private CharSequence url;
	private Label digitalAnalyticsHeadLabel,digitalAnalyticsBodyLabel;
	private static  String param1;
	private static  String param2;
	private AbstractDefaultAjaxBehavior behavior;
	private WebMarkupContainer cilckToCallErrorContainer;
	private Label errorMessage;
	public HomePage() {
		
		addForm();
		addFeedbackPanel();
		addModalWindow();
		addTextField();
		addRequestACallLink();
		addSearchComponents();
		digitalAnalyticsLabels();
		addReportingDataTable();		
		behavior = new AbstractDefaultAjaxBehavior() {
			private static final long serialVersionUID = 1L;

			@Override
	        protected void respond(AjaxRequestTarget target) {
	            //calculate hours + add list or whatever to the target
	        	System.out.println("inside respond ");
	        }
//
//	        @Override
//			public CharSequence getCallbackScript() {
//	            StringBuilder result = new StringBuilder();
//
////	            result.append("function resetHours() {\n");
//	            result.append("wicketAjaxPost('" + this.getCallbackUrl() + "', null, function(){}, function(){});" + "\n");
////	            result.append("}\n");
//	            return result;
//	        }
	       /* @Override
	        public void renderHead(Component component, IHeaderResponse response) {
	        	super.renderHead(component, response);
	        	 response.render(OnDomReadyHeaderItem.forScript(getCallbackScript()));
	        }*/
	    };
		this.add(behavior);
//		radioGroup.setOutputMarkupPlaceholderTag(true);
	}
	private void addRequestACallLink() {
		AjaxLink<HomePage> requestACall = new AjaxLink<HomePage>("requestACall") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				modalWindow.setCookieName("modalCK");
				modalWindow.setTitle("Click To Call");
				ClickToCallPOPUP click2CallPopup = new ClickToCallPOPUP(modalWindow.getContentId(), modalWindow){
					
					private static final long serialVersionUID = 1L;

					@Override
					protected void onClose(AjaxRequestTarget target) {
						super.onClose(target);
					}
				};
				modalWindow.setContent(click2CallPopup);
				modalWindow.show(target);
			}
		};
		form.add(requestACall);
		
	}

	private void addTextField() {
		textfield = new TextField<String>("text1",new PropertyModel<String>(this,"labeltext")){
			private static final long serialVersionUID = 1L;

			@Override
			protected void convertInput() {
				rawtext = getValue();
				rawtext = getRawInput();
				super.convertInput();
			}
		};
		
		textfield.setEscapeModelStrings(false);
		textfield.setOutputMarkupPlaceholderTag(true);
		textfield.setMarkupId("text1");
		form.add(textfield);
		textfield.setRequired(true);
//	    
//	    public static class SampleCallbackBehavior extends AbstractDefaultAjaxBehavior {        
//	        @Override
//	        public void renderHead(Component component, IHeaderResponse response) {
//	            super.renderHead(component, response);
//	            response.render(OnDomReadyHeaderItem.forScript("var myfunction : " + getCallbackFunction(CallbackParameter.explicit(MY_PARAM))));
//	        }
//	        @Override
//	        protected void respond(AjaxRequestTarget target) {
//	            StringValue paramValue = getComponent().getRequest().getRequestParameters().getParameterValue(MY_PARAM);
//	            //TODO handle callback
//	        }       
//	    }
//	    url = behavior.getCallbackUrl();
	   
	}
	private void addFeedbackPanel() {
		cilckToCallErrorContainer = new WebMarkupContainer("errorContainer");
		cilckToCallErrorContainer.setMarkupId("errorContainer");
		cilckToCallErrorContainer.setOutputMarkupPlaceholderTag(true);
//		cilckToCallErrorContainer.setVisible(false);
		cilckToCallErrorContainer.add(new FeedbackPanel("feedbackPanel"));
		add(cilckToCallErrorContainer);
		errorMessage = new Label("reviewErrorLabel", "Please address the fields highlighted in red below.");
		errorMessage.setMarkupId("reviewErrorLabel");
		errorMessage.setOutputMarkupId(true);
		cilckToCallErrorContainer.add(errorMessage);
	}
	private void addForm() {
		form = new Form("form") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				info("Selected ");
				info("No of checkboxes checked" + selectedReports.size());
				info("value of label:"+ labeltext);
				info("raw input" +rawtext);
				if(null != selectedReport)
				info("report value" +selectedReport.getLeadNo() + selectedReport.isRadioSelected());
			
			}
			
		};
		add(form);
	}


	private void addSearchComponents() {
		final TextField<String> searchTextField = new TextField<String>("searchTextField", new PropertyModel<String>(this, "searchText"));
		searchTextField.setOutputMarkupId(true);
		searchTextField.add(new AjaxFormComponentUpdatingBehavior("onchange") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				System.out.println(searchText);
				System.out.println(searchTextField.getInput());
			System.out.println(searchTextField.getModelObject());
			System.out.println(searchTextField.getDefaultModelObject());
			}
		});
		form.addOrReplace(searchTextField);
		AjaxLink<HomePage> searchLink = new AjaxLink<HomePage>("searchLink") {
			
			private static final long serialVersionUID = 1L;

		
			@Override
			public void onClick(AjaxRequestTarget target) {
				System.out.println("Search Text:"+ searchText);
				selectedReport = WicketApplication.get().getReports().get(0);
				provider.setReports(WicketApplication.get().getReports());
				dataTable.setVisible(true);
				target.add(form);
			}
		};
		searchLink.setOutputMarkupId(true);
		
		form.add(searchLink);
	}
	
	private void addReportingDataTable() {
		radioGroup = new RadioGroup<Report>("group",new PropertyModel<Report>(this,"selectedReport"));
		form.add(radioGroup);
		/*DefaultDataTable<Report, String> dataTable = new DefaultDataTable<Report, String>(
				"ReportTable", createColumns(), provider, 4);*/
		dataTable = new DataTable<Report, String>("ReportTable", createColumns(), provider, 4);
		//dataTable.addTopToolbar(new NavigationToolbar(dataTable));
		dataTable.addTopToolbar(new ReportTableToolBar(dataTable, provider));
		dataTable.addBottomToolbar(new NoRecordsToolbar(dataTable));
		dataTable.setOutputMarkupPlaceholderTag(true);
		dataTable.setVisible(false);	
		radioGroup.add(dataTable);
		radioGroup.add(new AjaxFormChoiceComponentUpdatingBehavior(){
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				if(null != getDefaultModelObject()){
					System.out.println(getDefaultModelObject());
				}
				target.add(form);
			}
			
		});
		
	}
	

	private void addModalWindow() {
		modalWindow = new CustomModalWindow("modalAd");
		form.add(modalWindow);
	}
	private List<IColumn<Report, String>> createColumns() {

		List<IColumn<Report, String>> columns = new ArrayList<IColumn<Report, String>>();
		columns.add(new CheckBoxColumn() {

			
			private static final long serialVersionUID = 1L;

			@Override
			protected IModel<Boolean> newCheckBoxModel(
					final IModel<Report> rowModel) {
				return new AbstractCheckBoxModel() {

					private static final long serialVersionUID = 1L;

					@Override
					public boolean isSelected() {
						return selectedReports.contains(rowModel.getObject());
					}

					@Override
					public void select() {
						selectedReports.add(rowModel.getObject());
					}

					@Override
					public void unselect() {
						selectedReports.remove(rowModel.getObject());
					}

					@Override
					public void detach() {
						rowModel.detach();
					}

				};
			}

		});
		columns.add(new RadioButtonColumn(){
			private static final long serialVersionUID = 1L;

			@Override
			protected void updateRadioGroup(AjaxRequestTarget target,Object object) {
				selectedReport = (Report) object;
				target.add(form);
			}
			
		});
		columns.add(new PropertyColumn<Report, String>(Model.of("FIRST NAME"),
				"firstName", "firstName"));

		columns.add(new PropertyColumn<Report, String>(Model.of("Last Name"),
				"lastName", "lastName") {
			private static final long serialVersionUID = 1L;

			public String getCssClass() {
				return "last-name";
			};
		});

		columns.add(new PropertyColumn<Report, String>(Model.of("Lead #"),
				"leadNo", "leadNo") {
			private static final long serialVersionUID = 1L;

			public String getCssClass() {
				return "numeric";
			};
		});
		columns.add(new PropertyColumn<Report, String>(Model.of("HC #"),
				"hcNo", "hcNo"));
		columns.add(new PropertyColumn<Report, String>(Model.of("Created date"),
				"createdDate", "createdDate"));
		columns.add(new PropertyColumn<Report, String>(Model.of("Updated date"),
				"updatedDate", "updatedDate"));
		columns.add(new PropertyColumn<Report, String>(Model.of("Created time"),
				"createdTime", "createdTime"));
		columns.add(new PropertyColumn<Report, String>(Model.of("Updated time"),
				"updatedTime", "updatedTime"));
		columns.add(new PropertyColumn<Report, String>(Model.of("LTV"),
				"ltv", "ltv"));
		columns.add(new PropertyColumn<Report, String>(Model.of("Application Id"),
				"appid", "appid"));
		columns.add(new PropertyColumn<Report, String>(Model.of("Appraisal Value"),
				"appraisalValue", "appraisalValue"));
		
		return columns;
	}

	
  private void digitalAnalyticsLabels () { 
	  digitalAnalyticsHeadLabel = new Label("digitalAnalyticsHeadLabel", new Model<String>()); 
	  digitalAnalyticsHeadLabel.setEscapeModelStrings(false);
	  digitalAnalyticsHeadLabel.setMarkupId("requestacallheadtag"); 
	  digitalAnalyticsHeadLabel.setDefaultModelObject(executeDigitalAnalyticsTags("https://www-qa.cb.info53.com/apps/static/mb2c/meta-about-your-finances.html"));
	  addOrReplace(digitalAnalyticsHeadLabel);
	  
	  digitalAnalyticsBodyLabel = new Label("digitalAnalyticsBodyLabel", new  Model<String>()); 
	  digitalAnalyticsBodyLabel.setEscapeModelStrings(false);
	  digitalAnalyticsBodyLabel.setMarkupId("requestacallbodytag");
	  digitalAnalyticsBodyLabel.setDefaultModelObject(executeDigitalAnalyticsTags("https://www-qa.cb.info53.com/apps/static/mb2c/html-about-your-finances.html"));
	  addOrReplace(digitalAnalyticsBodyLabel);
	  
	   }
	 
	/**
	 * This method is invoked from all the pages
	 * 
	 * @param url
	 */
	public String executeDigitalAnalyticsTags(String urlArray) {
		if (null != urlArray) {
			StringBuilder stringBuilder = new StringBuilder();
			String[] urls = urlArray.split(",");
			for (String url : urls) {
				String textData = null;
				HttpClient httpClient = new HttpClient();
				GetMethod get = new GetMethod(url);
				try {
					if (hasValidContent(url)) {
						int statusCode = httpClient.executeMethod(get);
						if (statusCode == HttpStatus.SC_OK) {
							// logger.info("Tag executed successfully");
							textData = get.getResponseBodyAsString();
							if (null != textData) {
								stringBuilder.append(textData);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					get.releaseConnection();
				}
			}
			if (!stringBuilder.toString().isEmpty()) {
				return stringBuilder.toString();
			}
		}
		return null;
	}

	/*@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(OnLoadHeaderItem.forScript("setDigitalAnalyticsHtml();"));
	}*/
	public static boolean hasValidContent(String str) {
		if (null != str && !"".equals(str.trim()))
			return true;
		else
			return false;
	}
	public class ReportTableToolBar extends CustomToolBar{
		private static final long serialVersionUID = 1L;
		public ReportTableToolBar(DataTable<Report, String> table,
					ISortStateLocator<String> stateLocator) {
			super(table, stateLocator);
		}
	}
	
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(CssHeaderItem.forReference(new CssResourceReference(WicketApplication.class, "bootstrap.css")));
		response.render(CssHeaderItem.forReference(new CssResourceReference(WicketApplication.class, "bootstrap-theme.min.css")));
//		response.render(CssHeaderItem.forReference(new CssResourceReference(WicketApplication.class, "bootstrap-theme.css.map")));
//		response.render(CssHeaderItem.forReference(new CssResourceReference(WicketApplication.class, "bootstrap.css.map")));
		response.render(CssHeaderItem.forReference(new CssResourceReference(WicketApplication.class, "style.css")));
		response.render(JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(WicketApplication.class, "jq.min.js")));
		response.render(JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(WicketApplication.class, "bootstrap.js")));
		response.render(JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(WicketApplication.class, "custom.js")));
		response.render(JavaScriptUrlReferenceHeaderItem.forUrl("https://secure-development.53.com/mortgage/dtm/init.js"));
		response.render(OnDomReadyHeaderItem.forScript("var callScript ="+behavior.getCallbackScript()));
		

	}
	
	
}
