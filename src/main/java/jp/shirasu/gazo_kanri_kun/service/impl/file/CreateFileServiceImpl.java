package jp.shirasu.gazo_kanri_kun.service.impl.file;

import java.nio.file.Path;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import jp.shirasu.gazo_kanri_kun.entity.service.request.CreateFileRequest;
import jp.shirasu.gazo_kanri_kun.entity.service.response.CreateFileResponse;
import jp.shirasu.gazo_kanri_kun.enums.ErrorCodeEnum;
import jp.shirasu.gazo_kanri_kun.exceptions.FileException;
import jp.shirasu.gazo_kanri_kun.service.file.CreateFileService;
import jp.shirasu.gazo_kanri_kun.utils.DirectoryUtils;
import jp.shirasu.gazo_kanri_kun.utils.ErrorUtils;
import jp.shirasu.gazo_kanri_kun.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * ファイル作成サービス実装
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CreateFileServiceImpl implements CreateFileService {
	private final MessageSource messageSource;
	private final MessageSource itemSource;

	private String errorMessage;
	private Locale locale = Locale.JAPANESE;

	@Override
	public CreateFileResponse create(CreateFileRequest request) {
		Path originalFilePath;
		Path filePath;

		// 元ファイルパス生成
		originalFilePath = createFilePath(request.getOriginalDirectoryPath().toString(), request.getOriginalFileName(),
			request.getExtension(), "originalFilePath");
		if (ObjectUtils.isEmpty(originalFilePath)) {
			return createErrorResponse();
		}

		// ファイルパス生成
		filePath = createFilePath(request.getDirectoryPath().toString(), request.getFileName(), request.getExtension(),
			"filePath");
		if (ObjectUtils.isEmpty(filePath)) {
			return createErrorResponse();
		}

		// ディレクトリ作成
		if (!createDirectory(request.getDirectoryPath())) {
			return createErrorResponse();
		}

		// ファイル作成
		if (!createFile(originalFilePath, filePath)) {
			return createErrorResponse(originalFilePath, filePath);
		}

		return createNormalResponse(filePath.toString(), originalFilePath.toString());
	}

	@Override
	public CompletableFuture<CreateFileResponse> createAsync(CreateFileRequest request) {
		return CompletableFuture.supplyAsync(() -> {
			return create(request);
		});
	}

	@Override
	public CompletableFuture<CreateFileResponse> createAsync(CreateFileRequest request, ExecutorService pool) {
		return CompletableFuture.supplyAsync(() -> {
			return create(request);
		}, pool);
	}

	/**
	 * ファイルパスの作成
	 * 
	 * @param directoryPath ディレクトリパス
	 * @param fileName      ファイル名
	 * @param extension     拡張子
	 * @param itemName      項目名
	 * @return ファイルパス
	 */
	private Path createFilePath(String directoryPath, String fileName, String extension, String itemName) {
		Path path = null;
		try {
			path = FileUtils.getSaveFilePath(directoryPath, fileName, extension);
		} catch (Exception e) {
			Object[] args = new Object[] {
					itemSource.getMessage(itemName, null, locale)
			};
			errorMessage = messageSource.getMessage("EM001", args, locale);

			log.error(ErrorUtils.getCodeMessage(ErrorCodeEnum.CREATE_PATH.getCode(), errorMessage), e);
			return null;
		}
		return path;
	}

	/**
	 * ディレクトリ作成
	 * 
	 * @param directoryPath ディレクトリパス
	 * @return 例外未発生時true、例外発生時false
	 */
	private boolean createDirectory(Path directoryPath) {
		// ディレクトリの作成
		if (!DirectoryUtils.existsDirectory(directoryPath)) {
			try {
				DirectoryUtils.createDirectory(directoryPath);
			} catch (Exception e) {
				Object[] args = new Object[] {
						itemSource.getMessage("directory", null, locale)
				};
				errorMessage = messageSource.getMessage("EM002", args, locale);
				log.error(ErrorUtils.getCodeMessage(ErrorCodeEnum.CREATE_DIRECTORY.getCode(), errorMessage), e);
				return false;
			}
		}
		return true;
	}

	/**
	 * ファイル作成
	 * 
	 * @param originalFilePath 元ファイルパス
	 * @param filePath         ファイルパス
	 * @return 例外未発生時true、例外発生時false
	 */
	private boolean createFile(Path originalFilePath, Path filePath) {
		try {
			// 元ファイルからファイルを作成
			FileUtils.copy(originalFilePath, filePath);
		} catch (FileException fe) {
			log.error(ErrorUtils.getCodeMessage(fe.getErrCode(), fe.getMessage()), fe);
			Object[] args = new Object[] {
					itemSource.getMessage("file", null, locale)
			};
			errorMessage = messageSource.getMessage("EM002", args, locale);
			return false;
		}
		return true;
	}

	/**
	 * エラーレスポンスの生成
	 * 
	 * @return エラーレスポンス
	 */
	private CreateFileResponse createErrorResponse() {
		CreateFileResponse response = new CreateFileResponse();
		response.createServiceErrorResoponse(errorMessage);
		return response;
	}

	/**
	 * エラーレスポンスの生成
	 * 
	 * @param originalFilePath 元ファイルパス
	 * @param filePath         ファイルパス
	 * @return エラーレスポンス
	 */
	private CreateFileResponse createErrorResponse(Path originalFilePath, Path filePath) {
		CreateFileResponse response = new CreateFileResponse(filePath.toString(), originalFilePath.toString());
		response.createServiceErrorResoponse(errorMessage);
		return response;
	}

	/**
	 * 正常レスポンスの生成
	 * 
	 * @param filePath         ファイルパス
	 * @param originalFilePath 元ファイルパス
	 * @return 正常レスポンス
	 */
	private CreateFileResponse createNormalResponse(String filePath, String originalFilePath) {
		CreateFileResponse response = new CreateFileResponse(filePath.toString(), originalFilePath.toString());
		Object[] args = new Object[] {
				itemSource.getMessage("file", null, locale)
		};
		response.createNormalResponse(messageSource.getMessage("NM001", args, locale));
		return response;
	}

}
