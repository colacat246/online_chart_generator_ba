package org.cv.ocb.utils;


import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SqlConfig(commentPrefix = "#")
@Sql(scripts = {"/scripts/prepareUser.sql", "/scripts/prepareGraph.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/scripts/deleteAll.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public @interface InjectSql {
}
