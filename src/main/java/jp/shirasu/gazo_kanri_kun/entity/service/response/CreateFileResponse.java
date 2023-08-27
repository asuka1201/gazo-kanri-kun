package jp.shirasu.gazo_kanri_kun.entity.service.response;

import jp.shirasu.gazo_kanri_kun.enums.ResultCodeEnum;
import lombok.Getter;

/**
 * ファイル作成レスポンス
 */
@Getter
public class CreateFileResponse extends CommonResponse {
	private String message;
	private Integer resultCode;
	private String filePath;
	private String originalFilePath;

	public CreateFileResponse() {
		this.filePath = "";
		this.originalFilePath = "";
	}

	public CreateFileResponse(String filePath, String originalFilePath) {
		this.filePath = filePath;
		this.originalFilePath = originalFilePath;
	}

	@Override
	public void createNormalResponse(String message) {
		this.message = message;
		this.resultCode = ResultCodeEnum.OK.getResultCode();

	}

	@Override
	public void createServiceErrorResoponse(String message) {
		this.message = message;
		this.resultCode = ResultCodeEnum.NG_SERVICE.getResultCode();

	}

	@Deprecated
	@Override
	public void createControllerErrorResponse(String message) {}

	@Override
	public void createOtherErrorResponse(String message) {
		this.message = message;
		this.resultCode = ResultCodeEnum.NG_OTHER.getResultCode();
	}

}
