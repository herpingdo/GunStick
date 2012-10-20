package me.herp.derp.GunStick;

import java.util.ArrayList;
import java.util.List;

public class Array {
	//@SuppressWarnings("rawtypes")
	static List<String> list = new ArrayList<String>();
	public static void putList(String s)
	{
		if (!list.contains(s))
		{
		list.add(s);
		}
	}
	public static void removelist(String s)
	{
		if (list.contains(s))
		{
			list.remove(list.indexOf(s));
		}
	}

}
