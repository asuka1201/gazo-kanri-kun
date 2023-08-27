package jp.shirasu.gazo_kanri_kun.utils;

import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import jp.shirasu.gazo_kanri_kun.enums.ErrorCodeEnum;
import jp.shirasu.gazo_kanri_kun.exceptions.FileCreateException;

public class FileUtils {

	/**
	 * 保存ファイルパスの取得
	 * 
	 * @param directoryPath ディレクトリパス
	 * @param fileName      ファイル名
	 * @param extension     拡張子
	 * @return 保存ファイルパス
	 * @throws Exception
	 */
	public static Path getSaveFilePath(String directoryPath, String fileName, String extension) throws Exception {
		return Paths.get(directoryPath, new StringBuilder().append(fileName).append(extension).toString());
	}

	/**
	 * 保存ファイルパスの取得
	 * 
	 * @param directoryPath ディレクトリパス
	 * @param fileName      ファイル名
	 * @param extension     拡張子
	 * @return 保存ファイルパス
	 * @throws Exception
	 */
	public static String getSaveFilePathStr(String directoryPath, String fileName, String extension) throws Exception {
		return getSaveFilePath(directoryPath, fileName, extension).toString();
	}

	/**
	 * ファイルのコピー
	 * 
	 * @param src  コピー元
	 * @param dest コピー先
	 * @throws FileCreateException ファイル作成時例外
	 */
	public static void copy(Path src, Path dest) throws FileCreateException {
		try (FileChannel srcChannel = FileChannel.open(src);
			FileChannel destChannel = FileChannel.open(dest, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
			srcChannel.transferTo(0, srcChannel.size(), destChannel);
		} catch (Exception e) {
			ErrorCodeEnum errorCode = ErrorCodeEnum.COPY_FILE;
			throw new FileCreateException(errorCode.getMessage(), e, errorCode.getCode());
		}
	}

	/**
	 * ファイルのリネーム
	 * 
	 * @param directoryPath ディレクトリパス
	 * @param befName       変更前ファイル名
	 * @param aftName       変更後ファイル名
	 * @param extension     拡張子
	 * @throws Exception
	 */
	public static void rename(String directoryPath, String befName, String aftName, String extension) throws Exception {
		Path bef = getSaveFilePath(directoryPath, befName, extension);
		Path aft = getSaveFilePath(directoryPath, aftName, extension);
		try {
			Files.move(bef, aft);
		} catch (Exception e) {

		}
	}

	/**
	 * ファイルの移動
	 * 
	 * @param src  移動元
	 * @param dest 移動先
	 */
	public static void move(Path src, Path dest) {
		try {
			Files.move(src, dest);
		} catch (Exception e) {

		}
	}

	/**
	 * ファイルの削除
	 * 
	 * @param path パス
	 */
	public static void delete(Path path) {
		if (!Files.isDirectory(path)) {
			try {
				Files.deleteIfExists(path);
			} catch (Exception e) {

			}
		}
	}

	/**
	 * 引数のPathがファイルかの判定
	 * 
	 * @param path パス
	 * @return ファイルの場合true、それ以外はfalse
	 */
	public static boolean isFile(Path path) {
		return !Files.isDirectory(path);
	}
}
