package jp.shirasu.gazo_kanri_kun.entity.service.request;

import java.io.Serializable;
import java.nio.file.Path;

import lombok.Data;

/**
 * ファイル作成リクエスト
 */
@Data
public class CreateFileRequest implements Serializable {
	private static final long serialVersionUID = -4997053047335014837L;
	/* 新規ファイル情報 */
	/** ディレクトリパス */
	private Path directoryPath;
	/** ファイル名 */
	private String fileName;
	/** ファイルNo */
	private Long fileNo;
	/** 拡張子 */
	private String extension;

	/* 元ファイル情報 */
	/** 元ディレクトリパス */
	private Path originalDirectoryPath;
	/** 元ファイル名 */
	private String originalFileName;

}
