package jp.shirasu.gazo_kanri_kun.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.AliasFor;

import jp.shirasu.gazo_kanri_kun.factorys.YamlPropertySourceFactory;

/**
 * Yaml用プロパティソース
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@PropertySource(value = "", factory = YamlPropertySourceFactory.class)
public @interface YamlPropertySource {

	@AliasFor(annotation = PropertySource.class, attribute = "name")
	String name() default "";

	@AliasFor(annotation = PropertySource.class, attribute = "value")
	String[] value();

	@AliasFor(annotation = PropertySource.class, attribute = "ignoreResourceNotFound")
	boolean ignoreResourceNotFound() default false;

	@AliasFor(annotation = PropertySource.class, attribute = "encoding")
	String encoding() default "";
}
