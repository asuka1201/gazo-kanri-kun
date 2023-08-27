package jp.shirasu.gazo_kanri_kun.entity.controller.request;

import java.util.List;

import jp.shirasu.gazo_kanri_kun.entity.common.items.FileItem;
import jp.shirasu.gazo_kanri_kun.entity.common.items.WorkItem;
import lombok.Data;

@Data
public class SaveImageFilesRequest {
	/** 作業アイテム */
	private WorkItem workItem;
	/** ファイルアイテム一覧 */
	private List<FileItem> fileItems;
}
