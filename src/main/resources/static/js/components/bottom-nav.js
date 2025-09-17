$(document).ready(function() {
    setTimeout(function() {

        // 모든 메뉴에서 active 클래스 제거
        document.querySelectorAll('.nav-item').forEach(item => {
            item.classList.remove('active');
        });

        const uriName = window.location.pathname;
        console.log(uriName === "/assets");
        if(uriName === "/assets"){
            $('#assets').addClass('active');
        } else if(uriName === "/account"){
            $('#account').addClass('active');
        } else if(uriName === "more"){
            $('#more').addClass('active');
        } else{
            $('#main').addClass('active');
        }
        console.log("location pathname = " + window.location.pathname);
    }, 200);

});

    document.addEventListener('click', function(e) {
    // 클릭된 요소가 .nav-item 또는 그 하위 요소인지 확인
    if(e.target.closest('.nav-item')) {
        const navItem = e.target.closest('.nav-item');

        // 모든 메뉴에서 active 클래스 제거
        document.querySelectorAll('.nav-item').forEach(item => {
            item.classList.remove('active');
        });

        // 클릭한 메뉴에만 active 클래스 추가
        navItem.classList.add('active');
    }
});