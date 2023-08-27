package jp.shirasu.gazo_kanri_kun.entity.common.items;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ファイルアイテム
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE, creatorVisibility = JsonAutoDetect.Visibility.NONE)
public class FileItem implements Serializable {
	private static final long serialVersionUID = 1401392220851443155L;

	/** ファイルNo */
	private Long fileNo;
	/** 拡張子 */
	private String extension;
	/** ファイルサイズ */
	private Long size;
	/** ファイルグループ */
	private List<String> fileGroups;
	/** 作成日時 */
	private String createDateTime;
	/** 更新日時 */
	private String updateDateTime;

	/** 元ファイル名 */
	private String originalFileName;
	/** 元ディレクトリパス */
	private String originalDirectoryPath;

	/** 編集フラグ */
	private Boolean editFlg;
	/** 削除フラグ */
	private Boolean deleteFlg;
}