package jp.shirasu.gazo_kanri_kun.entity.service.response;

public abstract class CommonResponse {

	/**
	 * 正常レスポンス生成
	 * 
	 * @param message メッセージ
	 */
	abstract void createNormalResponse(String message);

	/**
	 * サービスエラーレスポンス生成
	 * 
	 * @param message メッセージ
	 */
	abstract void createServiceErrorResoponse(String message);

	/**
	 * コントローラエラーレスポンス生成
	 * 
	 * @param message メッセージ
	 */
	abstract void createControllerErrorResponse(String message);

	/**
	 * その他エラーレスポンス生成
	 * 
	 * @param message メッセージ
	 */
	abstract void createOtherErrorResponse(String message);
}
