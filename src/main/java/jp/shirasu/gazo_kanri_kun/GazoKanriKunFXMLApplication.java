package jp.shirasu.gazo_kanri_kun;

import java.io.IOException;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * がぞうかんり君
 * FXML起動用アプリケーション
 */
public class GazoKanriKunFXMLApplication extends Application {

	private ConfigurableApplicationContext applicationContext;

	@Override
	public void init() {
		applicationContext = new SpringApplicationBuilder(GazoKanriKunSpringApplication.class).run();
	}

	@Override
	public void start(Stage stage) throws IOException {
		applicationContext.publishEvent(new GazoKanriKunStageReadyEvent(stage));
	}

	@Override
	public void stop() throws Exception {
		applicationContext.close();
		Platform.exit();
	}

	/**
	 * がぞうかんり君
	 * ステージ準備イベント
	 */
	public class GazoKanriKunStageReadyEvent extends ApplicationEvent {
		private static final long serialVersionUID = 5278696972610235078L;

		public GazoKanriKunStageReadyEvent(Stage stage) {
			super(stage);
		}

		public Stage getStage() {
			return (Stage) getSource();
		}
	}
}