package jp.shirasu.gazo_kanri_kun.factorys;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

/**
 * Yaml用プロパティソースファクトリー
 */
public class YamlPropertySourceFactory implements PropertySourceFactory {

	@Override
	public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
		try {
			return name == null
				? new YamlResourcePropertySource(resource)
				: new YamlResourcePropertySource(name, resource);
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	/**
	 * Yaml用プロパティソース
	 */
	public static class YamlResourcePropertySource extends PropertiesPropertySource {

		public YamlResourcePropertySource(EncodedResource resource) throws IOException, Exception {
			this(getNameForResource(resource.getResource()), resource);
		}

		public YamlResourcePropertySource(String name, EncodedResource resource) throws IOException, Exception {
			super(name, loadYamlProperties(resource));
		}

		private static String getNameForResource(Resource resource) {
			String name = resource.getDescription();
			if (StringUtils.isBlank(name)) {
				name = resource.getClass().getSimpleName() + "@" + System.identityHashCode(resource);
			}
			return name;
		}

		private static Properties loadYamlProperties(EncodedResource resource) throws FileNotFoundException, Exception {
			YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
			factory.setResources(resource.getResource());
			try {
				return factory.getObject();
			} catch (Exception e) {
				Throwable cause = e.getCause();
				throw cause instanceof FileNotFoundException ? (FileNotFoundException) cause : e;
			}
		}
	}

}
