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
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jackson on 1/29/2015.
 *
 * This class does little but interface to the TableAdapter.
 * It really just renames the methods of the TableAdapter class
 * to names that make more sense.
 *
 * It differs from the Cell based table adapter in that
 * it requests a row at a time, rather than a cell at a time.
 */
public abstract class RowBasedTableAdapter<T> extends TableAdapter<T> {
	public RowBasedTableAdapter(Context context, T[] values) {
		super(context, values);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent, int usableWidth) {
		int[] fixedColumnWidths = new int[columnWidths.length];

		for (int i = 0; i < columnWidths.length; i ++) {
			fixedColumnWidths[i] = (int) (columnWidths[i] * usableWidth);
		}
		return getRow(position, convertView, parent, fixedColumnWidths, rowSpacing);
	}

	public abstract View getRow(int position, View convertView, ViewGroup parent,
	                            int[] columnWidths, int rowSpaces);
}
