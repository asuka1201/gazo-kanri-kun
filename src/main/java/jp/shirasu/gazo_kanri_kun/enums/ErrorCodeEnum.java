package jp.shirasu.gazo_kanri_kun.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * エラーコードEnum
 */
@AllArgsConstructor
@Getter
public enum ErrorCodeEnum {
	CREATE_PATH("E001", "パス生成時エラー"),
	CREATE_DIRECTORY("E002", "ディレクトリ作成時エラー"),
	COPY_FILE("E003", "ファイルコピー時エラー");

	/** エラーコード */
	private String code;
	/** エラーメッセージ */
	private String message;
}