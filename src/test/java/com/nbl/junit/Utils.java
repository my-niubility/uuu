/**
 * @author gcs
 * @createdate 2016年8月17日	
 * @version 1.0
 * 
 */
package com.nbl.junit;

import java.util.HashSet;
import java.util.Set;

public class Utils {
	public static void main(String[] args) {
		Set<String> set = new HashSet<String>();
//		String a = "1";
//		String b = "2";
//		set.add(a);
//		set.add(b);
		String[] list = new String[]{"1","2","3"};
		for (String string : list) {
			boolean falg = set.contains(string);
			if(!falg){
				System.out.println("---String---"+ string);
			}
		}
	}
}
