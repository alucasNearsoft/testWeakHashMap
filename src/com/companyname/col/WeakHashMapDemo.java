package com.companyname.col;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

class LargeObject{
	private byte[] memory = new byte[1024*1024*50]; // 50 megabytes
}

class WeakHashMapDemo{
	/**
	 * Each loop iteration begins with a System.gc() method call, which may or may not cause a garbage
collection to take place (depending upon platform). To encourage a garbage collection, the iteration
then creates a LargeObject object and throws away its reference. This activity should eventually cause
the garbage collector to run and remove the map’s solitary entry.
	 */
	public static void main(String[] args){
		Map<LargeObject, String> map = new WeakHashMap<>();
		//Map<LargeObject, String> map = new HashMap<>();
		LargeObject lo = new LargeObject();
		map.put(lo, "Large Object");
		System.out.println(map);
		lo = null;
		while (!map.isEmpty()){
			System.gc();
			new LargeObject();
		}
		System.out.println(map);
	}
}