package com.web.utuanime.models.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * カテゴリー
 *
 * @author shiutk
 */
@Getter
@RequiredArgsConstructor
public enum Category {
  FANTASY("001","ファンタジー"),
  ROBOT("002","ロボット"),
  FICTION("003","心理フィクション"),
  CYBER_PUNK("004","サイバーパンク"),
  LOVE("005","恋愛"),
  SF("006","SF");

  private final String code;
  private final String name;

  /**
   * カテゴリーコードからカテゴリーインスタンスを返却
   * @param code カテゴリーコード
   * @return カテゴリーインスタンス
   */
  public static Category of(String code) {
    for (final Category category : Category.values()) {
      if (category.getCode().equals(code)) {
        return category;
      }
    }
    throw new IllegalArgumentException("カテゴリー不正：" + code);
  }

}
