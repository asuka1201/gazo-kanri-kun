package jp.shirasu.gazo_kanri_kun.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang3.ObjectUtils;

public class DirectoryUtils {

	/**
	 * 保存ディレクトリパスの取得
	 * 
	 * @param workDirectory ワークディレクトリ
	 * @param saveDirectory 保存ディレクトリ
	 * @return 保存ディレクトリパス
	 */
	public static Path getSaveDirectoryPath(String workDirectory, String... saveDirectory) {
		if (ObjectUtils.isEmpty(saveDirectory)) {
			return Paths.get(workDirectory);
		}
		return Paths.get(workDirectory, saveDirectory);
	}

	/**
	 * 保存ディレクトリパスの取得
	 * 
	 * @param workDirectory ワークディレクトリ
	 * @param saveDirectory 保存ディレクトリ
	 * @return 保存ディレクトリパス
	 */
	public static String getSaveDirectoryPathStr(String workDirectory, String... saveDirectory) {
		return getSaveDirectoryPath(workDirectory, saveDirectory).toString();
	}

	/**
	 * ディレクトリの存在確認
	 * 
	 * @param directory ディレクトリ
	 * @return ディレクトリの有無
	 */
	public static boolean existsDirectory(Path directory) {
		return Files.exists(directory);
	}

	/**
	 * ディレクトリの作成
	 * 
	 * @param directory ディレクトリ
	 * @throws IOException
	 */
	public static void createDirectory(Path directory) throws IOException {
		Files.createDirectories(directory);
	}
}
