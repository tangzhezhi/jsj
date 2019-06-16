/**
 * Copyright © 2018 organization baomidou
 * <pre>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <pre/>
 */
package org.tang.jsj.ds.aop;

import lombok.Setter;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.annotation.AnnotationUtils;
import org.tang.jsj.ds.DynamicDataSourceClassResolver;
import org.tang.jsj.ds.annotation.DS;
import org.tang.jsj.ds.annotation.DS_BASE;
import org.tang.jsj.ds.annotation.DS_BUSINESS;
import org.tang.jsj.ds.processor.DsProcessor;
import org.tang.jsj.ds.toolkit.DynamicDataSourceContextHolder;

import java.lang.reflect.Method;

/**
 * 动态数据源AOP核心拦截器
 *
 * @author TaoYu
 * @since 1.2.0
 */
public class MyDynamicDataSourceAnnotationInterceptor implements MethodInterceptor {

    /**
     * SPEL参数标识
     */
    private static final String DYNAMIC_PREFIX = "#";

    @Setter
    private DsProcessor dsProcessor;

    private DynamicDataSourceClassResolver dynamicDataSourceClassResolver = new DynamicDataSourceClassResolver();

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            DynamicDataSourceContextHolder.push(determineDatasource(invocation));
            return invocation.proceed();
        } finally {
            DynamicDataSourceContextHolder.poll();
        }
    }

    private String determineDatasource(MethodInvocation invocation) throws Throwable {
        String orgId = "";
        Method method = invocation.getMethod();
        Class<?> declaringClass = dynamicDataSourceClassResolver.targetClass(invocation);
        DS ds = method.isAnnotationPresent(DS.class) ? method.getAnnotation(DS.class)
                : AnnotationUtils.findAnnotation(declaringClass, DS.class);

        DS_BASE ds_base = method.isAnnotationPresent(DS_BASE.class) ? method.getAnnotation(DS_BASE.class)
                : AnnotationUtils.findAnnotation(declaringClass, DS_BASE.class);

        DS_BUSINESS ds_business = method.isAnnotationPresent(DS_BUSINESS.class) ? method.getAnnotation(DS_BUSINESS.class)
                : AnnotationUtils.findAnnotation(declaringClass, DS_BUSINESS.class);

        if(ds!=null){
            String key = ds.value();
            return (!key.isEmpty() && key.startsWith(DYNAMIC_PREFIX)) ? dsProcessor.determineDatasource(invocation, key) : key;
        }

        if(ds_base!=null || ds_business!=null){
            Object[] args = invocation.getArguments();
            if(args!=null && args.length > 0){
                orgId = (String)args[args.length-1];
            }

          return  dsProcessor.determineDatasource(invocation, orgId);
        }
        return null;
    }
}