package com.yys.test.service.impl;

import com.yys.test.entity.Product;
import com.yys.test.mapper.ProductEntity;
import com.yys.test.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 杨永生
 * @since 2018-07-22
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductEntity, Product> implements ProductService {

}
