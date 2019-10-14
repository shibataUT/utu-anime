package com.web.utuanime.models.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * アニメ一覧
 *
 * @author shiutk
 */
@Getter
@RequiredArgsConstructor
public enum Anime {

  MADOMAGI("01",Category.FANTASY,null,"魔法少女まどか☆マギカ"),
  BOKUIRANO("02",Category.SF,Category.ROBOT,"ぼくらの"),
  HAIBANE("03",Category.FANTASY,null,"灰羽連盟"),
  NARUTARU("04",Category.FICTION,null,"なるたる"),
  TEXHNOLYZE("05",Category.CYBER_PUNK,Category.FICTION,"TEXHNOLYZE"),
  SAISYUUHEIKI("06",Category.SF,Category.LOVE,"最終兵器彼女"),
  LAIN("07",Category.CYBER_PUNK,Category.FICTION,"serial experiments lain"),
  REVAIS("08",Category.SF,Category.ROBOT,"無限のリヴァイアス"),
  EVA("09",Category.SF,Category.ROBOT,"新世紀エヴァンゲリオン"),
  GUNSLINGER_GIRL("10",Category.SF,Category.LOVE,"GUNSLINGER GIRL");

  private final String code;
  private final Category category1;
  private final Category category2;
  private final String name;

  /**
   * アニメコードからアニメインスタンスを返却する
   * @param code アニメコード
   * @return アニメインスタンス
   */
  public static Anime of(String code) {
    for(final Anime anime:Anime.values()) {
      if(anime.getCode() == code)
        return anime;
    }
    throw new IllegalArgumentException("アニメ不正：" + code);
  }

}
