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

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Jackson on 1/28/2015.
 *
 * This class gives some example usage of the
 * TableList
 */
public class ExampleActivity extends Activity {
	public static final String[][] values = new String[][] {
			new String[] {"Column 1", "Column 2", "Column 3"},
			new String[] {"Inputs", "Inputs", "inputs"}
	};

	@Override
	public void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);
		TableAdapter tableAdapter = new TableAdapter(this, values);
		TableList table = new TableList(this);
		setContentView(table);

		table.setColumnWidths(new int[] {100, 100, 100});
		tableAdapter.setColumnWidths(new int[] {100, 100, 100});
		tableAdapter.getView(0, null, null);
		table.setAdapter(tableAdapter);
	}
}
