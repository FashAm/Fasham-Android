package com.example.ourfirst;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA. User: The0s Date: 04/07/12 Time: 04:00 To change
 * this template use File | Settings | File Templates.
 */
public class ItemBO {
	private String name;
	private String description;
	private String description2;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription2() {
		return description2;
	}

	public void setDescription2(String description) {
		this.description2 = description;
	}

	// / --------------------
	public static ArrayList getItems() {
		ArrayList list = new ArrayList();
		ItemBO item;

		item = new ItemBO();
		item.setName("George");
		item.setDescription("red shirt: 50 votes");
		item.setDescription2("pink dress: 29 votes");
		list.add(item);

		item = new ItemBO();
		item.setName("item 2");
		item.setDescription2("0 votes");
		item.setDescription("desc 2");
		list.add(item);

		item = new ItemBO();
		item.setName("item 3");
		item.setDescription2("????:???");
		item.setDescription("desc 3");
		list.add(item);

		item = new ItemBO();
		item.setName("item 4");
		item.setDescription2("?????");
		item.setDescription("desc 4");
		list.add(item);

		return list;
	}

}
