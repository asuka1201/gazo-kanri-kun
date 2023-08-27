package jp.shirasu.gazo_kanri_kun.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * リザルトコードEnum
 */
@AllArgsConstructor
@Getter
public enum ResultCodeEnum {
	OK(0, "正常"),
	NG_SERVICE(1, "異常"),
	NG_CONTROLLER(2, "異常"),
	NG_OTHER(-1, "異常");

	/** モード */
	private Integer resultCode;
	/** モード（表示文字列） */
	private String resultStr;
}