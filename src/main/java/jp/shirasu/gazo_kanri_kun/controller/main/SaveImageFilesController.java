package jp.shirasu.gazo_kanri_kun.controller.main;

import org.springframework.stereotype.Controller;

import jp.shirasu.gazo_kanri_kun.controller.common.CommonController;
import jp.shirasu.gazo_kanri_kun.service.file.CreateFileService;
import lombok.RequiredArgsConstructor;

/**
 * CMA-0005
 * 画像保存コントローラ
 */
@Controller
@RequiredArgsConstructor
public class SaveImageFilesController extends CommonController {
	private final CreateFileService createFileService;

}
