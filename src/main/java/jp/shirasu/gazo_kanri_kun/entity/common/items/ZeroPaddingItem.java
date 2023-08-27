package jp.shirasu.gazo_kanri_kun.entity.common.items;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ゼロパディングアイテム
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE, creatorVisibility = JsonAutoDetect.Visibility.NONE)
public class ZeroPaddingItem implements Serializable {
	private static final long serialVersionUID = -6226780049842651117L;

	/** ゼロパディングモード */
	private Integer zeroPaddingMode;
	/** ゼロパディング個数 */
	private Long zeroPaddingQuantity;
}