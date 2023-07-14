package com.tour.kuma.global.util;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class FileIdGenerator implements IdentifierGenerator, Configurable {

    public static final String _PREFIX = "file_";

    private String method;

    @Override
    public void configure(Type type, Properties parameters, ServiceRegistry serviceRegistry) {
        String customMethod = parameters.getProperty("custom.generator.method"); // 설정된 속성 값 가져오기
        if (method != null) {
            this.method = customMethod;
        } else {
            // 기본값 설정
            this.method = "defaultMethod";
        }
    }

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {

        String prefix = "file_";
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String customId = prefix + method + "_" + date; // 원하는 로직으로 ID를 생성
        return customId;
    }
}
