/**
 * 
 */
package com.xy.yiyao.api.service;

import java.util.List;

import com.xy.yiyao.api.model.DemoModel;

/**
 * @author Administrator
 *
 */
public interface DemoService {
	
	/**
	 * 新增
	 * @param demoModel
	 * @return
	 */
	public DemoModel addUser(DemoModel demoModel);

	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public String deleteById(String id);

	/**
	 * 查询所有
	 * @return
	 */
	public List<DemoModel> findAll();
}
