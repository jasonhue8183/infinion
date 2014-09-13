package com.my.tdex.dnb.sccb2.audit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// RetentionPolicy.RUNTIME is make sure that the compiler does't throw away the annotation, loaded into the JVM at runtime. The target is define where to apply the annotation.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Audit {

	String value();
}
