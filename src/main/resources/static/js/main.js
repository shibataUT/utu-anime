$(function () {

  /** 質問ボタンクリック制御 */
  $(document).on("click", "[id^=q]", function () {
    // 回答テキスト表示
    $('.oneArea').append("<div class='onebox'><div class='imgArea'><img src='/img/icon.png'></div><div class='fukiArea'><div class='fukidasi'>" + $(this).text() + "</div></div></div>");
    $('.oneArea').append("<div class='onebox'><div class='imgArea'><img src='/img/LainTwist.gif'></div><div class='fukiArea'><div class='fukidasi'><div class='loader'>Loading...</div><br><br></div></div></div>");
    setTimeout(apiExec, 2000, this);
  });

  function apiExec(value){
    if(value.id == "q1"){
      $.ajax({
            url: 'http://utu-anime.com/api/q1/' + value.value,
            type: 'GET',
          })
          .done(function(data) {
            $(".onebox:last").remove();
            $('.oneArea').append(data.ques);
          })
          .fail(function() {
            console.log("エラー");
          });
    } else {
      $.ajax({
            url: 'http://utu-anime.com/api/' + value.id + '/' + value.value,
            type: 'GET',
          })
          .done(function(data) {
            $(".onebox:last").remove();
            $('.oneArea').append(data.result);
          })
          .fail(function() {
            console.log("エラー");
          });
    }
  }

});