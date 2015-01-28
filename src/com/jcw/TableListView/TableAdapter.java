/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Jackson Woodruff
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

package com.jcw.TableListView;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Jackson on 1/28/2015.
 *
 * This is an adapter for use with the TableList.
 *
 * It is especially designed to use String arrays
 * in such a ay that it can take the width of each
 * column from the TableList after it has been
 * added to it (as opposed to someone having to
 * specify the size of each column).
 */
public class TableAdapter extends ArrayAdapter<CharSequence[]> {
	int[] columnWidths;

	// These are some numbers used for storing various settings
	// to do with the appearence of the list
	int spaceColor = Color.TRANSPARENT;
	int columnSpacing = 0;
	int rowSpacing = 0;

	int cellBackgroundColor = Color.TRANSPARENT;
	
	public TableAdapter(Context context, CharSequence[][] values) {
		// 0 is okay to pass here because we are going to the the the 
		super(context, 0, values);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CharSequence[] contents = getItem(position);

		if (columnWidths == null) {
			throw new NullPointerException("You must call setColumnWidths before" +
					" trying to draw the table");
		}

		// This layout is necessary in case we need to put the
		// line in above the row.
		LinearLayout tableRow = new LinearLayout(getContext());
		tableRow.setOrientation(LinearLayout.VERTICAL);

		if (position != 0) {
			View rowDivider = new View(getContext());
			LinearLayout.LayoutParams dividerParams = new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT, rowSpacing
			);
			rowDivider.setBackgroundColor(spaceColor);
			tableRow.addView(rowDivider, dividerParams);
		}

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
					columnWidths[i], ViewGroup.LayoutParams.MATCH_PARENT
			);
			cell.setBackgroundColor(cellBackgroundColor);
			cell.setText(contents[i]);
			itemContainer.addView(cell, cellParams);
		}

		tableRow.addView(itemContainer);

		return tableRow;
	}

	public void setCellBackgroundColor(int color) {
		this.cellBackgroundColor = color;
	}

	public void setSpaceColor(int color) {
		this.spaceColor = color;
	}

	public void setColumnSpacing(int spacing) {
		this.columnSpacing = spacing;
	}

	public void setRowSpacing(int spacing) {
		this.rowSpacing = spacing;
	}
	
	public void setColumnWidths(int[] widths) {
		this.columnWidths = widths;
	}
}
