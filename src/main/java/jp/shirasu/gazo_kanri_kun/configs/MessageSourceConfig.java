package jp.shirasu.gazo_kanri_kun.configs;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.StringUtils;

import jp.shirasu.gazo_kanri_kun.utils.YamlResourceBundle;

@Configuration
public class MessageSourceConfig {

	@Bean(name = "messagesProperties")
	@ConfigurationProperties(prefix = "spring.messages")
	MessageSourceProperties messageSourceProperties() {
		return new MessageSourceProperties();
	}

	@Bean(name = "messageSource")
	MessageSource messageSource() {
		YamlMessageSource yamlMessageSource = new YamlMessageSource();
		setYamlMessageSource(yamlMessageSource, messageSourceProperties());
		return yamlMessageSource;
	}

	@Bean(name = "itemsProperties")
	@ConfigurationProperties(prefix = "spring.items")
	MessageSourceProperties itemSourceProperties() {
		return new MessageSourceProperties();
	}

	@Bean("itemSource")
	MessageSource itemSource() {
		YamlMessageSource yamlMessageSource = new YamlMessageSource();
		setYamlMessageSource(yamlMessageSource, itemSourceProperties());
		return yamlMessageSource;
	}

	private void setYamlMessageSource(YamlMessageSource yamlMessageSource, MessageSourceProperties properties) {
		// basename
		if (StringUtils.hasText(properties.getBasename())) {
			yamlMessageSource
				.setBasenames(StringUtils
					.commaDelimitedListToStringArray(StringUtils.trimAllWhitespace(properties.getBasename())));
		}
		// encodeing
		if (ObjectUtils.isNotEmpty(properties.getEncoding())) {
			yamlMessageSource.setDefaultEncoding(properties.getEncoding().name());
		}
		// fallbackToSystemLocale
		yamlMessageSource.setFallbackToSystemLocale(properties.isFallbackToSystemLocale());
		if (ObjectUtils.isNotEmpty(properties.getCacheDuration())) {
			yamlMessageSource.setCacheMillis(properties.getCacheDuration().toMillis());
		}
		// alwaysUseMessageFormat
		yamlMessageSource.setAlwaysUseMessageFormat(properties.isAlwaysUseMessageFormat());
		// seCodeAsDefaultMessage
		yamlMessageSource.setUseCodeAsDefaultMessage(properties.isUseCodeAsDefaultMessage());
	}

	private static class YamlMessageSource extends ResourceBundleMessageSource {
		@Override
		protected ResourceBundle doGetBundle(String basename, Locale locale) throws MissingResourceException {
			return ResourceBundle.getBundle(basename, locale, YamlResourceBundle.YamlControl.FXML_CONTROL);
		}
	}

}
