package bld.commons.yaml;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan(basePackages = "bld.commons")
@ConfigurationProperties
class UsePluginApplicationTests {
	
	private final static Log logger = LogFactory.getLog(UsePluginApplicationTests.class);

//	@Autowired
//	private ConvertYaml convertYaml;
//	
//	@Autowired
//	private GeneratorClass generatorClass;
	
	@Test
	void contextLoads() {
		
	}

	@Test
	public void generateClassTest() {
		try {
//			Container container=this.convertYaml.generateModelClass("/yaml-class/typological.yaml");
//			this.generatorClass.writeClass(container.getFeatureClass());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}
	
	
}
