package com.dt.module.base.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dt.module.base.entity.SysDictItem;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author algernonking
 * @since 2018-07-24
 */
public interface SysDictItemMapper extends BaseMapper<SysDictItem> {

	List<SysDictItem> selectDictItemByDict(String dictId);
	
}
