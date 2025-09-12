document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.querySelector('.login-form');

    loginForm.addEventListener('submit', function(event) {
        // 폼 제출 전에 클라이언트 측 유효성 검사를 추가할 수 있습니다
    });

    const loadingScreen = document.getElementById('loading-screen');
    const content = document.getElementById('login-content');

    // 0.5초 후 페이드 아웃 효과와 본문 노출
    setTimeout(() => {
        loadingScreen.style.transition = 'opacity 0.5s ease';
        loadingScreen.style.opacity = '0';
        setTimeout(() => {
            loadingScreen.style.display = 'none';
            content.style.display = 'block';
        }, 500);
    }, 500);
});