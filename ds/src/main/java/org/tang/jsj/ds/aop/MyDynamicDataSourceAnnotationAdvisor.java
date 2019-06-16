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

import lombok.NonNull;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.tang.jsj.ds.annotation.DS;
import org.tang.jsj.ds.annotation.DS_BASE;
import org.tang.jsj.ds.annotation.DS_BUSINESS;

/**
 * 动态数据源AOP织入
 *
 * @author TaoYu
 * @since 1.2.0
 */
public class MyDynamicDataSourceAnnotationAdvisor extends AbstractPointcutAdvisor implements BeanFactoryAware {

    private Advice advice;

    private Pointcut pointcut;

    public MyDynamicDataSourceAnnotationAdvisor(@NonNull MyDynamicDataSourceAnnotationInterceptor dynamicDataSourceAnnotationInterceptor) {
        this.advice = dynamicDataSourceAnnotationInterceptor;
        this.pointcut = buildPointcut();
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (this.advice instanceof BeanFactoryAware) {
            ((BeanFactoryAware) this.advice).setBeanFactory(beanFactory);
        }
    }

    private Pointcut buildPointcut() {
        Pointcut base_cpc = new AnnotationMatchingPointcut(DS_BASE.class, true);
        Pointcut buisness_cpc = new AnnotationMatchingPointcut(DS_BUSINESS.class, true);
        Pointcut base_mpc = AnnotationMatchingPointcut.forMethodAnnotation(DS_BASE.class);
        Pointcut business_mpc = AnnotationMatchingPointcut.forMethodAnnotation(DS_BUSINESS.class);
        return new ComposablePointcut(base_cpc).union(buisness_cpc).union(base_mpc).union(business_mpc);
    }
}
