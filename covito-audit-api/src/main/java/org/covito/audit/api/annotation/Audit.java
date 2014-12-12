package org.covito.audit.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法调用会被审计
 * 
 * @author Brent.Shen
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Audit {

	/**
	 * 应用代码
	 * @return
	 */
	String appCode() default "";

	/**
	 * 动作名称
	 * @return
	 */
	String action();

    /**
     * 操作资源解析器名称
     * @return
     */
    String resResolver();

    /**
     * 动作解析品名称
     * @return
     */
    String actResolver();
}
