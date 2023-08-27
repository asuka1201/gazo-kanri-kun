package jp.shirasu.gazo_kanri_kun.entity.service.response;

import lombok.Data;

/**
 * ファイル削除レスポンス
 */
@Data
public class DeleteFileResponse {
	private String message;
	private Integer result;
}
