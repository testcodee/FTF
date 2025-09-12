$(document).ready(function() {

    // 타이틀 영역 로드
    $("#bottom-nav").load("components/bottom-nav", function() {
        console.log("타이틀 영역이 로드되었습니다.");
    });

    $("#header").load("components/header", function() {
        console.log("타이틀 영역이 로드되었습니다.");
    });
});