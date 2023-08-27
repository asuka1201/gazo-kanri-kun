package jp.shirasu.gazo_kanri_kun.controller;

import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("/fxml/jp/shirasu/gazo_kanri_kun/gazoKanriKun.fxml")
@RequiredArgsConstructor
public class GazoKanriKunController {

	private final FxWeaver fxWeaver;

	@FXML
	public void initialize() {

	}
}
