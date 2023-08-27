package jp.shirasu.gazo_kanri_kun.service.file;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import jp.shirasu.gazo_kanri_kun.entity.service.request.CreateFileRequest;
import jp.shirasu.gazo_kanri_kun.entity.service.response.CreateFileResponse;

/**
 * S-0001
 * ファイル作成サービス
 *
 */
public interface CreateFileService {

	/**
	 * ファイル作成
	 * 
	 * @param request リクエスト
	 * @return レスポンス
	 */
	public CreateFileResponse create(CreateFileRequest request);

	/**
	 * ファイル作成（非同期）
	 * 
	 * @param request リクエスト
	 * @return レスポンス
	 */
	public CompletableFuture<CreateFileResponse> createAsync(CreateFileRequest request);

	/**
	 * ファイル作成（非同期）
	 * 
	 * @param request リクエスト
	 * @param pool    スレッドプール
	 * @return レスポンス
	 */
	public CompletableFuture<CreateFileResponse> createAsync(CreateFileRequest request, ExecutorService pool);

}
