package com.jcw.TableListView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Jackson on 1/29/2015.
 *
 * This is a cell based adapter, meaning that a
 * method is called for each (i, j) cell.
 *
 * For an adapter that requests a single view per row,
 * use the RowBasedTableAdapter.
 */
public abstract class CellBasedAdapter<T> extends TableAdapter<T> {
	public CellBasedAdapter(Context context, T[] values) {
		super(context, values);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent, int usableWidth) {
		LinearLayout row = new LinearLayout(getContext());
		row.setOrientation(LinearLayout.HORIZONTAL);

		for (int i = 0; i < columnWidths.length; i ++) {
			if (i != 0) {
				View rowSeparator = getRowSeparator();

				row.addView(rowSeparator);
			}

			ViewGroup.LayoutParams contentsParams = new ViewGroup.LayoutParams(
					(int) (columnWidths[i] * usableWidth), ViewGroup.LayoutParams.MATCH_PARENT
			);

			row.addView(getCell(i, position), contentsParams);
		}
		return row;
	}

	/*
	 * Note when implementing this, (0, 0) is the top
	 * left corner.
	 *
	 *
	 */
	public abstract View getCell(int x, int y);
}
