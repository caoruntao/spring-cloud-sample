package com.crt.jpapostgresql.plus;

import org.hibernate.dialect.PostgreSQL94Dialect;

import java.sql.Types;

/**
 * @author Caort
 * @date 2020/11/2 20:50
 */
public class JsonbPostgresDialect extends PostgreSQL94Dialect {
    public JsonbPostgresDialect() {
        this.registerColumnType(Types.JAVA_OBJECT,"jsonb");
    }
}
