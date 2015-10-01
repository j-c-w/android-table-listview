This is a simple adaptation of the standard Android ListView so that it can display tabulated data in a more efficient way than creating an excessively long TableLayout.

What exactly does this do?
-------------------
Well, imagine you have a really big CSV file you wanted to display. A TableLayout doesn't really work,
because it means loading in all the data at the start, making your application slow and clunky.
A ListView would solve this problem, but isn't really suited to displaying data in a tabular form.

The solution? Well, the one I came to is to use this TableListView.

Great! So how do I put it in my project?
----------------------
Since this project doesn't depend on anything in the res/ folder, all you have to do is copy and paste
the contents of the /src folder into your project. 

After that, to make the packages work, you will need to go into each of the files and change the package
to the new package name (so, if you put it in com/your/package/views), the package name at the top of each file
would then be `package com.your.package.views.com.jcw.TableListView`). 

That's it!

How do I get started quickly?
-------------------
Take a look at the ExampleActivity class, which contains a basic implementation of the TableListView

If you want to see a quick comparison of the different Adapter types, see the [The Adapters](#the-adapters) section

But what do all these methods do??
---------------
Here is a list of the important methods from each class:

**TableList**
	 	
This class is the one that is a view, and the one that you should be using in your layouts.
It extends ListView, so most of the methods are still accessible. The difference between this 
and a classic TableLayout is that because the content of all the columns is not known,
you have to specify these widths when you create the table.

	setColumnWidths(float[] widths)

As above, this sets the widths of the columns. Note that the widths are taken as **fractions of the total view width**
So, the sum of all the widths should be 1. 

As an example, for two equally spaced columns, you call `tableList.setColumnWidths(new float[] {0.5f, 0.5f})`

	setColumnWidths(int numberOfColumns)

As an alternative to setting the widths of each of the columns as fractions, you can have N equally spaced columns by
calling `tableList.setColumnWidths(N)`

So, to get the same effect as above, you would call `tableList.setColumnWidths(2)`

	setTableHeaders(String[] headers, int textColor, int background)

Sometimes you want headers on your table. This sets the headers of your table, with the strings passed representing
the text in the headers. The text color and background arguments deal with the text color and background color of the
header respectively. 

If you choose not to call this method (or the one below), the table will still work fine, just without a header.

	setTableHeaders(View view)
	
As an alternative to passing strings to table headers, you can make your own table header and pass it in as a view.
If you choose to do this, you probably want the width of your view set to MATCH_PARENT, and the height set to WRAP_CONTENT.

Note that a header set like this will not be visible if you scroll down (image it as an extra list item at the top of the list).
To get a view that stays when scrolling down, just add your header view above the TableList in your layout.

	setAdapter(Adapter adapter)
	
This sets the adapter of the TableList. Note that the adapter passed should be a subclass of TableAdapter (i.e. either a CellBasedTableAdapter, a RowBasedTableAdapter or a SimpleTableAdapter)


**TableAdapter**

If you are looking for a quick implementation of your table, I would recommend using SimpleTableAdapter instead.

The TableAdapter is a way of implementing the TableList. Simply implement the `getView(int position, View convertView, ViewGroup parent, int usableWidth);` method.
In this, you should return the row at position `position` in the list. I would recommend using one of the other adapters.

# The Adapters

**SimpleTableAdapter**

Using the SimpleTableAdapter is, as it's name suggests, simple.

You need to pass a two dimensional list of strings. You can think of each index in the outer array as a row, and each index in the inner array as a cell.
So, as an example:

	String[][] values = new String[] {
		new String[] {"Column 1", "Column 2", "Column 3"}, // This is the first row
		new String[] {"Hello", "World", "!"} // The second row
	}

	SimpleTableAdapter adapter = new SimpleTableAdapter(context, values);
	// This is done as 3 because we have 3 columns in the values array.
	tableList.setColumnWidths(3);
	tableList.setAdapter(adapter);
	// All done!
	
**What if I want more control?**

In that case, you either want the RowBasedTableAdapter or the CellBasedTableAdapter.

If you want to handle your data in rows, and want to pass back a list of views 
that represent a whole row, then you should use the RowBasedTableAdapter.

If you want to handle your data in cells, and want to pass each cell back individually,
then you should use the CellBasedTableAdapter.

**RowBasedTableAdapter**

To use the RowBasedTableAdapter, you should implement the `View[] getView(int position, View convertView, ViewGroup parent, int[] columnWidths)`
method.

In this, position represents which row you are returning (with the first row at position 0).

You should then return an array of views, with the view at the kth index representing the kth column in the row.

**CellBasedTableAdapter**

To use the CellBasedTableAdapter, you should implement the `View getCell(int x, int y)` method.

In this method, you should return the view that should be displayed in the column y and row x. 

