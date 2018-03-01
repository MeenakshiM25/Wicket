package com.tavavnt.wicket;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IStyledColumn;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.Strings;

public class CustomToolBar extends AbstractToolbar{

	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * 
	 * @param <T>
	 *            the column data type
	 * @param <S>
	 * @param table
	 *            data table this toolbar will be attached to
	 * @param stateLocator
	 *            locator for the ISortState implementation used by sortable headers
	 */
	public <T, S> CustomToolBar(final DataTable<T, S> table, final ISortStateLocator<S> stateLocator)
	{
		super(table);

		RefreshingView<IColumn<T, S>> headers = new RefreshingView<IColumn<T, S>>("headers")
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected Iterator<IModel<IColumn<T, S>>> getItemModels()
			{
				List<IModel<IColumn<T, S>>> columnsModels = new LinkedList<IModel<IColumn<T, S>>>();

				for (IColumn<T, S> column : table.getColumns())
				{
					columnsModels.add(Model.of(column));
				}

				return columnsModels.iterator();
			}

			@Override
			protected void populateItem(Item<IColumn<T, S>> item)
			{
				final IColumn<T, S> column = item.getModelObject();

				WebMarkupContainer header = null;

				if (column.isSortable())
				{
					header = newSortableHeader("header", column.getSortProperty(), stateLocator);
				}
				else
				{
					header = new WebMarkupContainer("header");
				}

				if (column instanceof IStyledColumn)
				{
					
					Behavior cssAttributeBehavior = new Behavior()
					{
						private static final long serialVersionUID = 1L;

						protected String getCssClass()
						{
							return ((IStyledColumn<?, S>)column).getCssClass();
						}
						@Override
						public void onComponentTag(final Component component, final ComponentTag tag)
						{
							String className = getCssClass();
							if (!Strings.isEmpty(className))
							{
								tag.append("class", className, " ");
							}
						}
					};

					header.add(cssAttributeBehavior);
				}

				item.add(header);
				item.setRenderBodyOnly(true);
				header.add(column.getHeader("label"));
			}
		};
		add(headers);
	}

	
	protected <S> WebMarkupContainer newSortableHeader(final String headerId, final S property,
		final ISortStateLocator<S> locator)
	{
		return new OrderByBorder<S>(headerId, property, locator)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSortChanged()
			{
				getTable().setCurrentPage(0);
			}
		};
	}
}
