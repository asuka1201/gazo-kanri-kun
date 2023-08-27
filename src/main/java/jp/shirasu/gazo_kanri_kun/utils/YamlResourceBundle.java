package jp.shirasu.gazo_kanri_kun.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.yaml.snakeyaml.Yaml;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * YAMLリソースバンドル
 */
@Slf4j
public class YamlResourceBundle extends ResourceBundle {

	/** エントリー */
	private final Map<String, Object> entries;

	/**
	 * コンストラクタ
	 * 
	 * @param yaml YAML
	 */
	public YamlResourceBundle(@NonNull String yaml) {
		entries = new YamlParser().parseYAML(yaml);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param yaml YAML
	 */
	public YamlResourceBundle(@NonNull InputStream yaml) {
		entries = new YamlParser().parseYAML(yaml);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param yaml YAML
	 */
	public YamlResourceBundle(@NonNull Reader yaml) {
		entries = new YamlParser().parseYAML(yaml);
	}

	@Override
	protected Set<String> handleKeySet() {
		return entries.keySet();
	}

	@Override
	public Enumeration<String> getKeys() {
		return Collections.enumeration(keySet());
	}

	@Override
	protected Object handleGetObject(String key) {
		return entries.get(key);
	}

	/**
	 * YAMLコントロールクラス
	 */
	public static class YamlControl extends ResourceBundle.Control {

		/** インスタンス */
		public static final YamlControl FXML_CONTROL = new YamlControl();

		/**
		 * コンストラクタ
		 */
		protected YamlControl() {}

		@Override
		public List<String> getFormats(@NonNull String baseName) {
			return List.of("yaml", "yml");
		}

		@Override
		public ResourceBundle newBundle(
			@NonNull String baseName, @NonNull Locale locale, @NonNull String format, @NonNull ClassLoader loader,
			boolean reload
		) throws IllegalAccessException, InstantiationException, IOException {
			ResourceBundle bundle = null;
			if (getFormats(baseName).contains(format)) {
				String bundleName = toBundleName(baseName, locale);
				String resourceName = toResourceName(bundleName, format);
				InputStream is = null;
				try {
					if (reload) {
						URL url = loader.getResource(resourceName);
						if (ObjectUtils.isNotEmpty(url)) {
							URLConnection con = url.openConnection();
							if (ObjectUtils.isNotEmpty(con)) {
								con.setUseCaches(false);
								is = con.getInputStream();
							}
						}
					} else {
						is = loader.getResourceAsStream(resourceName);
					}
					if (ObjectUtils.isNotEmpty(is)) {
						bundle = new YamlResourceBundle(is);
					}
				} catch (Exception e) {
					log.error(e.getMessage(), e);
					throw e;
				} finally {
					if (ObjectUtils.isNotEmpty(is)) {
						is.close();
					}
				}
			}

			return bundle;
		}
	}

	/**
	 * YAML解析クラス
	 */
	public static class YamlParser {
		/**
		 * YAML解析
		 * 
		 * @param yaml YAML
		 * @return YAML解析結果
		 */
		public Map<String, Object> parseYAML(String yaml) {
			return parseYAML(new Yaml().loadAll(yaml));
		}

		/**
		 * YAML解析
		 * 
		 * @param yaml YAML
		 * @return YAML解析結果
		 */
		public Map<String, Object> parseYAML(InputStream yaml) {
			return parseYAML(new Yaml().loadAll(yaml));
		}

		/**
		 * YAML解析
		 * 
		 * @param yaml YAML
		 * @return YAML解析結果
		 */
		public Map<String, Object> parseYAML(Reader yaml) {
			return parseYAML(new Yaml().loadAll(yaml));
		}

		/**
		 * YAML解析
		 * 
		 * @param yaml YAML
		 * @return YAML解析結果
		 */
		private Map<String, Object> parseYAML(Iterable<Object> yaml) {
			Iterator<?> it = yaml.iterator();
			if (!it.hasNext()) return null;
			Stream<Entry<String, Object>> stream = Stream.empty();
			while (it.hasNext()) {
				Stream<Entry<String, Object>> addStream = parseNode(it.next());
				if (ObjectUtils.isNotEmpty(addStream)) stream = Stream.concat(stream, addStream);
			}
			if (ObjectUtils.isNotEmpty(stream)) {
				Map<String, Object> map = stream
					.filter(e -> ObjectUtils.isNotEmpty(e)
									&& StringUtils.isNotBlank(e.getKey())
									&& ObjectUtils.isNotEmpty(e.getValue()))
					.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (oldVal, newVal) -> newVal));
				return map;
			}
			return null;
		}

		/**
		 * Nodeの解析
		 * 
		 * @param yaml YAML
		 * @return Nodeの解析結果
		 */
		private Stream<Entry<String, Object>> parseNode(Object yaml) {
			if (isYamlMap(yaml)) {
				Map<?, ?> map = (Map<?, ?>) yaml;
				return map.entrySet().stream().flatMap(e -> parseNode((String) e.getKey(), e.getValue()));
			}
			return null;
		}

		/**
		 * Nodeの解析
		 * 
		 * @param key   キー
		 * @param value バリュー
		 * @return Nodeの解析結果
		 */
		private Stream<Entry<String, Object>> parseNode(String key, Object value) {
			if (value instanceof List<?>) return parseListNode(key, (List<?>) value);
			if (value instanceof Map<?, ?>) return parseNodeMap(key, (Map<?, ?>) value);
			return Stream.of(new SimpleImmutableEntry<>(key, value));
		}

		/**
		 * ListNodeの解析
		 * 
		 * @param key   キー
		 * @param value バリュー
		 * @return ListNodeの解析結果
		 */
		private Stream<Entry<String, Object>> parseListNode(String key, List<?> value) {
			AtomicInteger index = new AtomicInteger();
			Stream<Entry<String, Object>> stream;
			if (value.stream().filter(v -> v instanceof List<?> || v instanceof Map<?, ?>).count() > 0) {
				stream = Stream.of(new SimpleImmutableEntry<>(key, value));
			} else {
				stream = Stream
					.of(new SimpleImmutableEntry<>(key,
						value.stream().map(v -> String.valueOf(v)).toArray(String[]::new)));
			}

			return Stream
				.concat(stream,
					value
						.stream().flatMap(
							v -> parseNode(
								new StringBuilder()
									.append(key).append("[").append(index.getAndIncrement()).append("]").toString(),
								v)));

		}

		/**
		 * MapNodeの解析
		 * 
		 * @param key   キー
		 * @param value バリュー
		 * @return MapNodeの解析結果
		 */
		private Stream<Entry<String, Object>> parseNodeMap(String key, Map<?, ?> value) {
			return value
				.entrySet().stream()
				.flatMap(e -> parseNode(new StringBuilder().append(key).append(".").append(e.getKey()).toString(),
					e.getValue()));
		}

		/**
		 * yaml形式のMap（Map<String, Object>）かどうかの判定
		 * 
		 * @param yaml YAML
		 * @return yaml形式のMapの場合true、それ以外はfalse
		 */
		private boolean isYamlMap(Object yaml) {
			if (!(yaml instanceof Map<?, ?>)) return false;
			Map<?, ?> yamlMap = (Map<?, ?>) yaml;
			if (yamlMap.entrySet().stream().filter(e -> !(e.getKey() instanceof String)).count() > 0) return false;
			return true;
		}
	}
}
