package jp.shirasu.gazo_kanri_kun.configs.records;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "gazo-kanri-kun.application")
public record GazoKanriKunApplicationConfig(String title, Double defaultWidth, Double defaultHeight) {

	public GazoKanriKunApplicationConfig(String title, Double defaultWidth, Double defaultHeight) {
		this.title = title;
		this.defaultWidth = defaultWidth;
		this.defaultHeight = defaultHeight;
	}
}
