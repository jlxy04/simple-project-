/**
 * 
 */
package com.xy.yiyao.dal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xy.yiyao.dal.entity.DemoEntity;

/**
 * @author Administrator
 *
 */
public interface DemoDao {

	public DemoEntity findById(@Param("id")String id);
	
	public int updateById(DemoEntity demoEntity);
	
	public List<DemoEntity> findAll();
	
	public int addDemo(DemoEntity demoEntity);
}
