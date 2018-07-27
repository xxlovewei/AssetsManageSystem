package com.dev;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * Created by ehomeud on 2017/4/26.
 */

public class CustomGenerator {
	public static void main(String[] args) throws InterruptedException {

		GlobalConfig g = new GlobalConfig();

		AutoGenerator mpg = new AutoGenerator();
		String dir = "/Users/algernonking/git/dt2/src/main";
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir(dir + "/java/");
		gc.setFileOverride(true);
		gc.setActiveRecord(true);
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(true);// XML ResultMap
		gc.setBaseColumnList(false);// XML columList
		gc.setAuthor("algernonking");
		gc.setDateType(DateType.ONLY_DATE);

		// 自定义文件命名，注意 %s 会自动填充表实体属性！

		gc.setMapperName("%sMapper");
		// gc.setXmlName("%sMapper");

		gc.setServiceName("I%sService");
		gc.setServiceImplName("%sServiceImpl");
		gc.setControllerName("%sController");
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();

		dsc.setDbType(DbType.ORACLE);
		dsc.setTypeConvert(new MySqlTypeConvert() {
			// 自定义数据库表字段类型转换【可选】
			public DbColumnType processTypeConvert(String fieldType) {
				System.out.println("转换类型：" + fieldType);
				return processTypeConvert(fieldType);
			}
		});

		dsc.setDriverName("oracle.jdbc.OracleDriver");
		dsc.setUrl("jdbc:oracle:thin:@//121.43.168.125:55521/db");
		dsc.setUsername("dt");
		dsc.setPassword("oracle123oracle");
		mpg.setDataSource(dsc);

		// 策略配置
		// 公共字段
		List<TableFill> tableFillList = new ArrayList<>();
		tableFillList.add(new TableFill("DR", FieldFill.INSERT));
		tableFillList.add(new TableFill("CREATE_BY", FieldFill.INSERT));
		tableFillList.add(new TableFill("CREATE_TIME", FieldFill.INSERT));
		tableFillList.add(new TableFill("UPDATE_BY", FieldFill.INSERT_UPDATE));
		tableFillList.add(new TableFill("UPDATE_TIME", FieldFill.INSERT_UPDATE));


		StrategyConfig strategy = new StrategyConfig();
		strategy.setLogicDeleteFieldName("DR");
		 
		strategy.setSuperControllerClass("com.dt.core.common.base.BaseController");
		
		strategy.setCapitalMode(false);// 全局大写命名 ORACLE 注意
		strategy.entityTableFieldAnnotationEnable(true);
		// strategy.setTablePrefix(new String[] { "tlog_", "tsys_" });// 此处可以修改为您的表前缀
		strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
		//"","sys_qud_qux"
		strategy.setInclude(new String[] { "sys_session" }); // 需要生成的表

		strategy.setTableFillList(tableFillList);
		
		 
		strategy.setSuperEntityClass("com.dt.core.common.base.BaseModel");
		strategy.setSuperEntityColumns("DR", "CREATE_BY", "CREATE_TIME", "UPDATE_BY", "UPDATE_TIME");
	        
		// strategy.setExclude(new String[]{"test"}); // 排除生成的表

		mpg.setStrategy(strategy);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent("com.dt.module");
		pc.setModuleName("base");
		pc.setXml(null);

		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
				map.put("dt_api", "/api");
				this.setMap(map);
			}
		};

		List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
		// 调整 xml 生成目录演示
		focList.add(new FileOutConfig("template/mapper.xml.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return dir + "/resources/mybatis/system/" + tableInfo.getMapperName() + ".xml";
			}
		});
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);

		mpg.setPackageInfo(pc);

		// 关闭原来的mapper文件
		TemplateConfig tc = new TemplateConfig();
		tc.setXml(null);
		tc.setController("/template/controller.java.vm");
		tc.setEntity("/template/entity.java.vm");
		tc.setMapper("/template/mapper.java.vm");
		tc.setService("/template/service.java.vm");
		tc.setServiceImpl("/template/serviceImpl.java.vm");

		mpg.setTemplate(tc);
		// 执行生成
		mpg.execute();
		System.err.println(mpg.getCfg().getMap().get("abc"));  
	}

}