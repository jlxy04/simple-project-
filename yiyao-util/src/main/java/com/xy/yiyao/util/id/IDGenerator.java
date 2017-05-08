/**
 * 
 */
package com.xy.yiyao.util.id;

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
