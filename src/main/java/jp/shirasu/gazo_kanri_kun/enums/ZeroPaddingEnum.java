package jp.shirasu.gazo_kanri_kun.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ゼロパディングEnum
 */
@AllArgsConstructor
@Getter
public enum ZeroPaddingEnum {
	NO(1, "なし"),
	SPECIFIED(2, "指定個数"),
	AUTO(3, "自動");

	/** モード */
	private Integer mode;
	/** モード（表示文字列） */
	private String modeStr;
}