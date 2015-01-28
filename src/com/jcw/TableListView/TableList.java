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
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.Arrays;

/**
 * Created by Jackson on 1/28/2015.
 */
public class TableList extends RelativeLayout {
	ListView table;
	LinearLayout tableHeaders;

	int[] columnWidths;

	public TableList(Context context) {
		super(context);
		init();
	}
	
	public TableList(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public TableList(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	/*
	 * The length of the array is the number of columns
	 * that will be used. The contents of every slot in
	 * this array is the size of each respective column
	 */
	public void setColumnWidths(int[] widths) {
		this.columnWidths = widths;
	}

	/*
	 * This is a helper method for setting the column
	 * widths. It is useful when you want the columns
	 * to have equal widths.
	 *
	 * Note that this uses the View.getWidth() method
	 * and so must be called after the view has been
	 * added to a layout
	 */
	public void setColumnWdiths(int numberOfColumns) {
		if (this.getWidth() == 0) {
			throw new NullPointerException("To automatically generate the sizes of the columns" +
					" the TableList must already be rendered. Use setColumnWidths(int[] widths) instead");
		}

		int totalWidth = this.getWidth();
		columnWidths = new int[numberOfColumns];
		Arrays.fill(columnWidths, totalWidth / numberOfColumns);
	}

	/*
	 * This is for setting the headers of the table.
	 * It will remove all previously added headers
	 */
	public void setTableHeaders(View view) {
		tableHeaders.removeAllViewsInLayout();
		tableHeaders.addView(view);
	}

	public void setTableHeaders(String[] headers) {
		if (columnWidths == null) {
			throw new NullPointerException("You must call setColumnWidths before trying to add headers");
		}
	}

	public void setAdapter(TableAdapter adapter) {
		this.tableAdapter = adapter;
		table.setAdapter(adapter);
	}
}