package org.cv.ocb.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("org.cv.ocb.mapper")
public class MyBatisConf {
}
