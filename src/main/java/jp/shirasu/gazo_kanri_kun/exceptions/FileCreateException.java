package jp.shirasu.gazo_kanri_kun.exceptions;

/**
 * ファイル作成時例外
 */
public class FileCreateException extends FileException {

	private static final long serialVersionUID = 6326863448672510042L;

	public FileCreateException(String message, Throwable cause, String code) {
		super(message, cause, code);
	}
}
