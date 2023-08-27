package jp.shirasu.gazo_kanri_kun.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ファイル名フォーマッターEnum
 */
@AllArgsConstructor
@Getter
public enum FileNameFormatterEnum {

	BR_GROUP_BR_NO("({group})({no})", "(グループ)(No)ファイル名.png"),
	BR_GROUP_NO_HY("({group}){no}-", "(グループ)No-ファイル名.png"),
	BR_GROUP_NO_US("({group}){no}_", "(グループ)No_ファイル名.png"),
	BR_NO_BR_GROUP("({no})({group})", "(No)(グループ)ファイル名.png"),
	BR_NO_GROUP_HY("({no}){group}-", "(No)グループ-ファイル名.png"),
	BR_NO_GROUP_US("({no}){group}_", "(No)グループ_ファイル名.png"),
	GROUP_BR_NO("{group}({no})", "グループ(No)ファイル名.png"),
	GROUP_HY_BR_NO("{group}-({no})", "グループ-(No)ファイル名.png"),
	GROUP_US_BR_NO("{group}_({no})", "グループ_(No)ファイル名.png"),
	GROUP_US_NO_HY("{group}_{no}-", "グループ_No-ファイル名.png"),
	GROUP_US_NO_US("{group}_{no}_", "グループ_No_ファイル名.png"),
	GROUP_NO_HY("{group}{no}-", "グループNo-ファイル名.png"),
	GROUP_HY_NO_HY("{group}-{no}-", "グループ-No-ファイル名.png"),
	GROUP_NO_US("{group}{no}_", "グループNo_ファイル名.png"),
	GROUP_HY_NO_US("{group}-{no}_", "グループ-No_ファイル名.png"),
	NO_BR_GROUP("{no}({group})", "No(グループ)ファイル名.png"),
	NO_HY_BR_GROUP("{no}-({group})", "No-(グループ)ファイル名.png"),
	NO_US_BR_GROUP("{no}_({group})", "No_(グループ)ファイル名.png"),
	NO_US_GROUP_HY("{no}_{group}-", "No_グループ-ファイル名.png"),
	NO_US_GROUP_US("{no}_{group}_", "No_グループ_ファイル名.png"),
	NO_GROUP_HY("{no}{group}-", "Noグループ-ファイル名.png"),
	NO_HY_GROUP_HY("{no}-{group}-", "No-グループ-ファイル名.png"),
	NO_GROUP_US("{no}{group}_", "Noグループ_ファイル名.png"),
	NO_HY_GROUP_US("{no}-{group}_", "No-グループ_ファイル名.png");

	/** フォーマッター */
	private String formatter;
	/** サンプル */
	private String sumple;
}
