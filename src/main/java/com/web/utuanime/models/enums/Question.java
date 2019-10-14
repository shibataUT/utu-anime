package com.web.utuanime.models.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 質問内容
 *
 * @author shiutk
 */
@Getter
@RequiredArgsConstructor
public enum Question {

  Q2("q2",Category.FANTASY,"どんな世界観が好き？"),
  Q3("q3",Category.CYBER_PUNK,"どんな結末を望む？"),
  Q4("q4",Category.LOVE,"長い時間をかけて見たい？"),
  Q5("q5",Category.ROBOT,"どちらの描写が好き？"),
  Q6("q6",Category.SF,"群像劇は好き？"),
  Q7("q7",Category.FICTION,"ハートフルボッコ作品は好き？");

  private final String id;
  private final Category category;
  private final String note;

  /**
   * 質問IDから質問インスタンスを返却
   * @param id 質問ID
   * @return 質問インスタンス
   */
  public static Question of(String id) {
    for(final Question question : Question.values()) {
      if(question.getId().equals(id)) {
        return question;
      }
    }
    throw new IllegalArgumentException("質問内容不正：" + id);
  }

  /**
   * カテゴリーから質問インスタンスを返却
   * @param category 回答カテゴリー
   * @return 質問内容
   */
  public static Question categoryOf(Category category) {
    for(final Question question : Question.values()) {
      if(question.getCategory() == category) {
        return question;
      }
    }
    throw new IllegalArgumentException("カテゴリー不正：" + category);
  }
}
