package jp.shirasu.gazo_kanri_kun.utils;

/**
 * エラー発生時共通処理
 */
public class ErrorUtils {

	/**
	 * エラーコード付きメッセージの取得
	 * 
	 * @param code    エラーコード
	 * @param message メッセージ
	 * @return エラーコード付きメッセージ
	 */
	public static String getCodeMessage(String code, String message) {
		return new StringBuilder().append("【").append(code).append("】").append(message).toString();
	}
}
