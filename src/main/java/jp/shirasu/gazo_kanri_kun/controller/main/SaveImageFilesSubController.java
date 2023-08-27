package jp.shirasu.gazo_kanri_kun.controller.main;

import org.springframework.stereotype.Controller;

import jp.shirasu.gazo_kanri_kun.entity.controller.request.SaveImageFilesRequest;
import jp.shirasu.gazo_kanri_kun.entity.controller.response.SaveImageFilesResponse;
import jp.shirasu.gazo_kanri_kun.service.file.CreateFileService;
import lombok.RequiredArgsConstructor;

/**
 * CMA-0005
 * 画像保存コントローラ
 */
@Controller
@RequiredArgsConstructor
public class SaveImageFilesSubController {
	private final CreateFileService createFileService;
	
	public SaveImageFilesResponse saveImageFiles(SaveImageFilesRequest request) {
		System.out.println("呼び出し２");
		SaveImageFilesResponse response = new SaveImageFilesResponse();
		return response;
	}
}
