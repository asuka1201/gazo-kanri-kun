package jp.shirasu.gazo_kanri_kun.configs.maps;

import java.text.MessageFormat;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import jp.shirasu.gazo_kanri_kun.annotations.YamlPropertySource;

@Component
@ConfigurationProperties(prefix = "prop.message")
@YamlPropertySource("classpath:messages.yml")
public class MessagesConfig {

	private final Map<String, String> normal;
	private final Map<String, String> confirm;
	private final Map<String, String> error;

	public MessagesConfig(Map<String, String> normal, Map<String, String> confirm, Map<String, String> error) {
		this.normal = normal;
		this.confirm = confirm;
		this.error = error;
	}

	public String getNormalMessage(String key) {
		return normal.get(key);
	}

	public String getNormalMessage(String key, Object... params) {
		return MessageFormat.format(normal.get(key), params);
	}

	public String getConfirmMessage(String key) {
		return confirm.get(key);
	}

	public String getConfirmMessage(String key, Object... params) {
		return MessageFormat.format(confirm.get(key), params);
	}

	public String getErrorMessage(String key) {
		return error.get(key);
	}

	public String getErrorMessage(String key, Object... params) {
		return MessageFormat.format(error.get(key), params);
	}
}
