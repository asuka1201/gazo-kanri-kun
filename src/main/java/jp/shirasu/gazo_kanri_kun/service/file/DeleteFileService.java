package jp.shirasu.gazo_kanri_kun.service.file;

import java.util.concurrent.CompletableFuture;

import jp.shirasu.gazo_kanri_kun.entity.service.request.DeleteFileRequest;
import jp.shirasu.gazo_kanri_kun.entity.service.response.DeleteFileResponse;

/**
 * S-0003
 * ファイル削除サービス
 */
public interface DeleteFileService {
	/**
	 * ファイル削除
	 * @param request リクエスト
	 * @return レスポンス
	 */
	public DeleteFileResponse delete(DeleteFileRequest request);
	
	/**
	 * ファイル削除（非同期）
	 * @param request リクエスト
	 * @return レスポンス
	 */
	public CompletableFuture<DeleteFileResponse> deleteAsync(DeleteFileRequest request);
}
