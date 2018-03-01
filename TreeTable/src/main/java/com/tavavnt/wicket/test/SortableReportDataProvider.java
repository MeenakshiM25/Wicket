package com.tavavnt.wicket.test;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableTreeProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import com.tavavnt.wicket.WicketApplication;
import com.tavavnt.wicket.pullreport.PullThruReportVO;

public class SortableReportDataProvider extends
		SortableTreeProvider<PullThruReportVO, String> {
	public SortableReportDataProvider() {
		setSort("tree", true);
	}

	@Override
	public Iterator<? extends PullThruReportVO> getChildren(
			PullThruReportVO node) {
		return node.getHierarchicalInfo().iterator();
	}

	@Override
	public Iterator<? extends PullThruReportVO> getRoots() {
		Collections.sort(WicketApplication.get().reportVOs,
				new Comparator<PullThruReportVO>() {
					public int compare(PullThruReportVO o1, PullThruReportVO o2) {
						int dir = getSort().isAscending() ? 1 : -1;
						if ("id".equals(getSort().getProperty())) {
							return dir * (o1.getId().compareTo(o2.getId()));
						} else if ("userId".equals(getSort().getProperty())) {
							return dir * (o1.getUserId().compareTo(o2.getUserId()));
						} else if ("userName".equals(getSort().getProperty())) {
							return dir * (o1.getUserName().compareTo(o2.getUserName()));
						}
						return dir * (o1.getId().compareTo(o2.getId()));
					}
				});

		return WicketApplication.get().reportVOs.iterator();
	}

	@Override
	public boolean hasChildren(PullThruReportVO node) {
//		return node.getParent() == null|| 
		return !node.getHierarchicalInfo().isEmpty();
	}

	@Override
	public IModel<PullThruReportVO> model(PullThruReportVO object) {
		return new PullThruReportModel(object);
	}

	private static class PullThruReportModel extends
			LoadableDetachableModel<PullThruReportVO> {
		private static final long serialVersionUID = 1L;

		private final String id;

		public PullThruReportModel(PullThruReportVO foo) {
			super(foo);

			id = foo.getId();
		}

		@Override
		protected PullThruReportVO load() {
			return WicketApplication.get().getReportVO(id);
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof PullThruReportModel) {
				return ((PullThruReportModel) obj).id.equals(id);
			}
			return false;
		}

		@Override
		public int hashCode() {
			return id.hashCode();
		}
	}

}
