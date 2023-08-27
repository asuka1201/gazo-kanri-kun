package jp.shirasu.gazo_kanri_kun;

import java.util.Locale;

import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javafx.scene.Scene;
import javafx.stage.Stage;
import jp.shirasu.gazo_kanri_kun.GazoKanriKunFXMLApplication.GazoKanriKunStageReadyEvent;
import jp.shirasu.gazo_kanri_kun.configs.records.GazoKanriKunApplicationConfig;
import jp.shirasu.gazo_kanri_kun.controller.GazoKanriKunController;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxWeaver;

/**
 * がぞうかんり君
 * ステージ初期化
 */
@Component
@RequiredArgsConstructor
public class GazoKanriKunStageInitializer implements ApplicationListener<GazoKanriKunStageReadyEvent> {
	private final FxWeaver fxWeaver;
	private final GazoKanriKunApplicationConfig kazoKanriKunApplicationConfig;
	private final MessageSource messageSource;
	private final MessageSource itemSource;

	@Override
	public void onApplicationEvent(GazoKanriKunStageReadyEvent event) {

		// TODO メッセージ表示確認 後で消す。
		System.out.println(messageSource.getMessage("test", null, Locale.JAPANESE));
		System.out.println(itemSource.getMessage("file", null, Locale.JAPANESE));

		// 前回のサイズ指定が存在する場合、そのサイズを使用。
		Double width;
		Double height;
		// if() {
		// } else {
		width = kazoKanriKunApplicationConfig.defaultWidth();
		height = kazoKanriKunApplicationConfig.defaultHeight();
		// }

		Stage stage = event.getStage();
		Scene scene = new Scene(fxWeaver.loadView(GazoKanriKunController.class), width, height);
		stage.setScene(scene);
		stage.setTitle(kazoKanriKunApplicationConfig.title());
		stage.show();
	}
}
