package com.web.utuanime.models.enums;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 質問選択肢
 *
 * @author shiutk
 *
 */
@Getter
@RequiredArgsConstructor
public enum Selection {

  Q2S1(Question.Q2,"Q2S1","不思議な世界",Anime.HAIBANE,null),
  Q2S2(Question.Q2,"Q2S2","不条理な世界",Anime.MADOMAGI,null),
  Q3S1(Question.Q3,"Q3S1","望みが叶わず救われない結末",Anime.TEXHNOLYZE,null),
  Q3S2(Question.Q3,"Q3S2","望みをかなえ救われる結末",Anime.LAIN,null),
  Q4S1(Question.Q4,"Q4S1","はい",Anime.GUNSLINGER_GIRL,null),
  Q4S2(Question.Q4,"Q4S2","いいえ",Anime.SAISYUUHEIKI,null),
  Q5S1(Question.Q5,"Q5S1","感情的で衝動的な描写",Anime.EVA,null),
  Q5S2(Question.Q5,"Q5S2","空虚で落ち着いた描写",Anime.BOKUIRANO,null),
  Q6S1(Question.Q6,"Q6S1","はい",Anime.REVAIS,null),
  Q6S2(Question.Q6,"Q6S2","いいえ",null,Question.Q5),
  Q7S1(Question.Q7,"Q7S1","はい",Anime.NARUTARU,null),
  Q7S2(Question.Q7,"Q7S2","いいえ",null,Question.Q3);

  private final Question question;
  private final String id;
  private final String note;
  private final Anime resultAnime;
  private final Question nextQuestion;

  /**
   * 質問Enumから選択肢リストを返却
   * @param question 質問Enum
   * @return 選択肢リスト
   */
  public static List<Selection> of(Question question){
    var selectionList = new ArrayList<Selection>();
    for(final Selection selection : Selection.values()) {
      if(selection.getQuestion().equals(question)) {
        selectionList.add(selection);
      }
    }
    return selectionList;
  }

  /**
   * 選択肢IDから選択肢インスタンスを返却
   * @param id 選択肢ID
   * @return 選択肢
   */
  public static Selection idOf(String id) {
    for(final Selection selection : Selection.values()) {
      if(selection.getId().equals(id)) {
        return selection;
      }
    }
    throw new IllegalArgumentException("選択肢不正：" + id);
  }

}
