/**
 * 
 */
package com.xy.yiyao.util;

import java.util.UUID;

/**
 * @author Administrator
 *
 */
public class IDGenerator {

	
	public static String generatorID() {
		return UUID.randomUUID().toString();
	}
}
