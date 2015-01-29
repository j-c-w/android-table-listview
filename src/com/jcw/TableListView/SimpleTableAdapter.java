package com.jcw.TableListView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Jackson on 1/29/2015.
 *
 * This is a table adapter that just puts strings into cells.
 *
 * For more complex views, you have to implement the ComplexTableAdapter
 */
public class SimpleTableAdapter extends TableAdapter<CharSequence[]> {
	public SimpleTableAdapter(Context context, CharSequence[][] values) {
		super(context, values);
	}

	@Override
	public LinearLayout getView(int position, View convertView, ViewGroup parent, int usableWidth) {
		CharSequence[] contents = getItem(position);

		LinearLayout itemContainer = new LinearLayout(getContext());

		for (int i = 0; i < Math.min(contents.length, columnWidths.length); i ++) {
			if (i != 0) {
				View columnDivider = new View(getContext());
				LinearLayout.LayoutParams dividerParams = new LinearLayout.LayoutParams(
						columnSpacing, ViewGroup.LayoutParams.MATCH_PARENT
				);
				columnDivider.setBackgroundColor(spaceColor);
				itemContainer.addView(columnDivider, dividerParams);
			}
			TextView cell = new TextView(getContext());
			LinearLayout.LayoutParams cellParams = new LinearLayout.LayoutParams(
					(int) (columnWidths[i] * usableWidth), ViewGroup.LayoutParams.MATCH_PARENT
			);
			cell.setBackgroundColor(cellBackgroundColor);
			cell.setText(contents[i]);
			itemContainer.addView(cell, cellParams);
		}

		return itemContainer;
	}
}
