package jp.shirasu.gazo_kanri_kun.entity.common.items;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作業アイテム
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE, creatorVisibility = JsonAutoDetect.Visibility.NONE)
public class WorkItem implements Serializable {
	private static final long serialVersionUID = -396495528387593415L;

	/** 作業ディレクトリ */
	private String workDirectory;
	/** 保存ディレクトリ */
	private String saveDirectory;
	/** ファイルグループ */
	private String fileGroup;
	
	/** 元ファイル名保持フラグ */
	private Boolean retentionOriginalFileNameFlg;
}
