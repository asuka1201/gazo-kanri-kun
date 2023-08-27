package jp.shirasu.gazo_kanri_kun.exceptions;

import lombok.Getter;

/**
 * ファイル例外
 */
public class FileException extends Exception {
	private static final long serialVersionUID = -2435284141006195906L;

	/** エラーコード */
	@Getter
	private String errCode;

	/**
	 * インスタンス
	 * 
	 * @param message メッセージ
	 * @param cause   原因
	 * @param errCode エラーコード
	 */
	public FileException(String message, Throwable cause, String errCode) {
		super(message, cause);
		this.errCode = errCode;
	}

}
