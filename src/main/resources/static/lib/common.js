// $.getScript("jquery.min.js", function() {
//   alert("Script loaded but not necessarily executed.");
// });

// $.getScript("bootstrap-3.3.7-dist/js/bootstrap.min.js", function() {
//   alert("Script loaded but not necessarily executed.");
// });

window.onload = function() {
  // 글로벌 에러 색깔 입히기
  $(".field-error").addClass("alert alert-warning");

  // 네비게이션 영역
  $("ul[class*='navbar-nav'] li").click(function(){
    let clickedMenu = $(this).text();
    $("ul[class*='navbar-nav'] li").each(function(idx, val){
        if(clickedMenu === $(val).text()) {
            $(val).addClass("active");
        } else {
            $(val).removeClass("active");
        }
    });
});

  // 빠른검색 자동완성 START
  const MINIMUM_SEARCH_LENGTH = 2;
  const BACKSPACE_KEYCODE = 8;
  const ARROW_UP = 38;
  const ARROW_DOWN = 40;
  const ENTER = 13;

  let arrowFlag = false; // 마지막에 누른게 arrow인가
  let selectedNode;

  function checkNode(mode) {
    switch(mode) {
      case "first":
        selectedNode = $searchList.first();
        break;
      case "last":
        selectedNode = $searchList.last();
        break;
      case "next":
        selectedNode = selectedNode.next();
        break;
      case "prev":
        selectedNode = selectedNode.prev();
        break;
    }
    // e.preventDefault();
    $searchList.removeClass("cursor");
    selectedNode.addClass("cursor");
  };



  $("input#fast-search").keyup(function(e){

    $searchList = $("#search-list").children();

    // if(this.value.length >=2 ) debugger;

      if(e.keyCode === ENTER ) {
        // console.log(selectedNode.text());
        location.href = "/item/" + selectedNode.attr("id");
      }
      else if( (e.keyCode === ARROW_UP || e.keyCode === ARROW_DOWN )
          && $("#search-list").children().length >= 1) {

          // 직전에 움직이지 않았다면
          if(arrowFlag === false) {
              if(e.keyCode === ARROW_DOWN) {
                checkNode("first");
              }
              else if(e.keyCode === ARROW_UP) {
                checkNode("last");
              }

          } 
          else { // 연속적인 선택
              if(e.keyCode === ARROW_DOWN && (selectedNode.index() !== $searchList.last().index() ) ) {
                checkNode("next");
              }
              else if(e.keyCode === ARROW_UP && (selectedNode.index() !== $searchList.first().index() ) ) {
                checkNode("prev");
              }
          }
          arrowFlag = true;
      }
      // 지울 때
      else if(this.value.length >= MINIMUM_SEARCH_LENGTH || e.keyCode ===  BACKSPACE_KEYCODE) {

          $searchList.remove();

          for(item of items) {
              if(item["name"].includes(this.value)) {
                  $li = '<li class="list-group-item" id="' + item.id + '">' 
                    + item["name"]
                    + '&nbsp; &nbsp;'
                    + '<span class="glyphicon glyphicon-ok"></span>'
                    + '</li>';
                  $("#search-list").append($li);
              }
          }
          arrowFlag = false;
      }
      else if(this.value === "") {
        $searchList.remove();
        arrowFlag = false;
      }

  });
  // 빠른검색 자동완성 END
};


