package com.tavavnt.wicket;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigationToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NoRecordsToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.export.CSVDataExporter;
import org.apache.wicket.extensions.markup.html.repeater.data.table.export.ExportToolbar;
import org.apache.wicket.extensions.markup.html.repeater.tree.AbstractTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.TableTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.Folder;
import org.apache.wicket.extensions.markup.html.repeater.tree.table.TreeColumn;
import org.apache.wicket.extensions.markup.html.repeater.tree.theme.WindowsTheme;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.OddEvenItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import com.tavavnt.wicket.pullreport.PullThruReportVO;
import com.tavavnt.wicket.test.SortableReportDataProvider;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;
	private AbstractTree<PullThruReportVO> tree;
	private Behavior theme;
	private SortableReportDataProvider provider = new SortableReportDataProvider();

	public HomePage(final PageParameters parameters) {
		super(parameters);

		theme = new WindowsTheme();
		tree = createTree(provider);
		tree.add(new Behavior() {
			private static final long serialVersionUID = 1L;

			public void onComponentTag(Component component, ComponentTag tag) {
				theme.onComponentTag(component, tag);
			}

			public void renderHead(Component component, IHeaderResponse response) {
				theme.renderHead(component, response);
			}
		});
		add(tree);
		
	}

	private List<IColumn<PullThruReportVO, String>> createColumns() {
		List<IColumn<PullThruReportVO, String>> columns = new ArrayList<IColumn<PullThruReportVO, String>>();

		columns.add(new PropertyColumn<PullThruReportVO, String>(
				Model.of("ID"), "id"));

		columns.add(new TreeColumn<PullThruReportVO, String>(Model.of("id"),
				"id"));

		columns.add(new PropertyColumn<PullThruReportVO, String>(Model
				.of("User ID"), "userId","userId"));
		columns.add(new PropertyColumn<PullThruReportVO, String>(Model
				.of("User Name"), "userName","userName"));

		return columns;
	}


	protected AbstractTree<PullThruReportVO> createTree(
			SortableReportDataProvider provider) {
		List<IColumn<PullThruReportVO, String>> columns = createColumns();

		final TableTree<PullThruReportVO, String> tree = new TableTree<PullThruReportVO, String>(
				"tree", columns, provider, 3) {
			private static final long serialVersionUID = 1L;

			@Override
			protected Component newContentComponent(String id,
					IModel<PullThruReportVO> model) {
				return new Folder<PullThruReportVO>(id, this, model);
			}

			@Override
			protected Item<PullThruReportVO> newRowItem(String id, int index,
					IModel<PullThruReportVO> model) {
				return new OddEvenItem<PullThruReportVO>(id, index, model);
			}
		};
		tree.setOutputMarkupId(true);
		tree.getTable().addTopToolbar(new ExportToolbar(tree.getTable()).addDataExporter(new CSVDataExporter()));
		tree.getTable().addTopToolbar(
				new HeadersToolbar<String>(tree.getTable(), provider));
		tree.getTable().addBottomToolbar(new NoRecordsToolbar(tree.getTable()));
		tree.getTable().addBottomToolbar(new NavigationToolbar(tree.getTable()));
		return tree;
	}
	

}
