package jp.shirasu.gazo_kanri_kun.service.file;

import java.util.concurrent.CompletableFuture;

import jp.shirasu.gazo_kanri_kun.entity.service.request.UpdateFileRequest;
import jp.shirasu.gazo_kanri_kun.entity.service.response.UpdateFileResponse;

/**
 * S-0002
 * ファイル更新サービス
 */
public interface UpdateFileService {
	/**
	 * ファイル更新
	 * @param request リクエスト
	 * @return レスポンス
	 */
	public UpdateFileResponse update(UpdateFileRequest request);
	
	/**
	 * ファイル更新（非同期）
	 * @param request リクエスト
	 * @return レスポンス
	 */
	public CompletableFuture<UpdateFileResponse> updateAsync(UpdateFileRequest request);
}
