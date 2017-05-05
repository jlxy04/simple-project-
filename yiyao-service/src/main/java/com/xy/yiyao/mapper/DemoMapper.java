/**
 * 
 */
package com.xy.yiyao.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.xy.yiyao.api.model.DemoModel;
import com.xy.yiyao.dal.entity.DemoEntity;

/**
 * 效率较高的属性转换器
 * @author Administrator
 *
 */
@Mapper
public interface DemoMapper {

	DemoMapper INSTANCE = Mappers.getMapper(DemoMapper.class);
	
	DemoModel demoEntity2Model(DemoEntity demoEntity);
	
	DemoEntity demoModel2Entity(DemoModel demoModel);
}
