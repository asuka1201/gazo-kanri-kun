package jp.shirasu.gazo_kanri_kun.controller;

import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import jp.shirasu.gazo_kanri_kun.controller.main.SaveImageFilesSubController;
import jp.shirasu.gazo_kanri_kun.entity.controller.request.SaveImageFilesRequest;
import jp.shirasu.gazo_kanri_kun.entity.controller.response.SaveImageFilesResponse;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("/fxml/jp/shirasu/gazo_kanri_kun/gazoKanriKun.fxml")
@RequiredArgsConstructor
public class GazoKanriKunController {

	private final FxWeaver fxWeaver;
	private final SaveImageFilesSubController saveImageFilesSubController;
	
	@FXML
	public Button saveImageFiles;

	@FXML
	public void initialize() {
		System.out.println("呼び出し０");
		saveImageFiles.setOnAction(actionEvent -> {
			System.out.println("呼び出し１");
			SaveImageFilesRequest request = new SaveImageFilesRequest();
			SaveImageFilesResponse response = saveImageFilesSubController.saveImageFiles(request);
		});

	}
}
