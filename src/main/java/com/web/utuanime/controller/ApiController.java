package com.web.utuanime.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.web.utuanime.models.enums.Category;
import com.web.utuanime.models.enums.Question;
import com.web.utuanime.models.enums.Selection;

/**
 * API関連のControllerクラス
 *
 * @author shiutk
 *
 */
@RestController
@RequestMapping("/api")
public class ApiController {

  private static final String URL = "https://script.google.com/macros/s/AKfycbxrfOrX95N0AKshPeKngTYiKPfz7QCm956tglAvcrgoYH676Ydn/exec?code=";

  /**
   * 質問１の回答API
   * @param code カテゴリーコード
   * @return 回答内容、次の質問
   */
  @RequestMapping(value="/q1/{code}", method= RequestMethod.GET)
  public Map<String, String> getCategory(@PathVariable("code") String code) {
    Map<String, String> map = new HashMap<>();
    map.put("ques",getQues(code));
    return map;
  }

  /**
   * 質問１以外の返答API
   * @param id 質問ID
   * @param value 回答内容
   * @return 質問または結果
   */
  @RequestMapping(value="/{id}/{value}", method= RequestMethod.GET)
  public Map<String, String> getAnswer(@PathVariable("id") String id, @PathVariable("value") String value) {
    Map<String, String> map = new HashMap<>();
    var ans = Selection.idOf(value);
    String result = null;
    if(ans.getNextQuestion() == null) {
      result = "<div class='onebox'><div class='imgArea'><img src='/img/LainTwist.gif'></div><div class='fukiArea'><div class='fukidasi'><p>あなたにおすすめのアニメは「" + ans.getResultAnime().getName() + "」です。</p></div></div></div>";
      // 結果保存
      var restTemplate = new RestTemplate();
      var url = URL + ans.getResultAnime().getCode();
      restTemplate.getForEntity(url,null);
    } else {
      result = nextQuestion(ans.getNextQuestion());
    }
    map.put("result",result);
    return map;
  }

  /**
   * カテゴリーから質問内容の取得
   * @param code カテゴリーコード
   * @return 質問内容
   */
  private String getQues(String code) {
    var category = Category.of(code);
    var qes = Question.categoryOf(category);
    var selectionList = Selection.of(qes);

    var stringBufferbuf = new StringBuffer();
    var qs = "<div class='onebox'><div class='imgArea'><img src='/img/LainTwist.gif'></div><div class='fukiArea'><div class='fukidasi'><p>" + qes.getNote() + "</p>";
    stringBufferbuf.append(qs);

    if(selectionList.isEmpty())
      throw new IllegalArgumentException("選択肢が取得出来ませんでした");

    for(Selection selection : selectionList) {
      var option = "<div class='btn-box'><button type='button' id=" + qes.getId() + " class='btn btn-info' text='" + selection.getNote() + "' value='" + selection +"'>" + selection.getNote() + "</button></div>";
      stringBufferbuf.append(option);
    }
    stringBufferbuf.append("</div>");
    return stringBufferbuf.toString();
  }

  /**
   * 質問Enumから次の質問内容を返却
   * @param question 質問Enum
   * @return 次の質問
   */
  private String nextQuestion(Question question) {
    var selectionList = Selection.of(question);
    var stringBufferbuf = new StringBuffer();
    var qs = "<div class='onebox'><div class='imgArea'><img src='/img/LainTwist.gif'></div><div class='fukiArea'><div class='fukidasi'><p>" + question.getNote() + "</p>";
    stringBufferbuf.append(qs);

    if(selectionList.isEmpty())
      throw new IllegalArgumentException("選択肢が取得出来ませんでした");

    for(Selection selection : selectionList) {
      var option = "<div class='btn-box'><button type='button' id=" + question.getId() + " class='btn btn-info' text='" + selection.getNote() + "' value='" + selection +"'>" + selection.getNote() + "</button></div>";
      stringBufferbuf.append(option);
    }
    stringBufferbuf.append("</div>");
    return stringBufferbuf.toString();
  }
}