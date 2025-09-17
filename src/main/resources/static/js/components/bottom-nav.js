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
        } else if(uriName === "/more"){
            $('#more').addClass('active');
        } else{
            $('#main').addClass('active');
        }
        console.log("location pathname = " + window.location.pathname);
    }, 200);
});
