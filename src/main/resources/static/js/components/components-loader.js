// components-loader.js
$(document).ready(function() {
    // 공통 컴포넌트 경로 설정
    const componentPath = '/components/';

    // CSS 파일 로드 함수
    function loadCSS(filename) {
        if ($('link[href="/css' + componentPath + filename + '"]').length === 0) {
            $('head').append('<link rel="stylesheet" type="text/css" href="/css' + componentPath + filename + '">');
            console.log(filename + ' CSS 로드 완료');
        }
    }

    // JS 파일 로드 함수
    function loadJS(filename) {
        if ($('script[src="/js' + componentPath + filename + '"]').length === 0) {
            $.getScript('/js' + componentPath + filename, function() {
                console.log(filename + ' JS 로드 완료');
            });
        }
    }

    // JS 파일 로드 함수
    function loadComponents(filename) {
        if ($('script[src="/js' + componentPath + filename + '"]').length === 0) {
            $.getScript('/js' + componentPath + filename, function() {
                console.log(filename + ' JS 로드 완료');
            });
        }
    }

    // 공통 컴포넌트 로드
    function loadComponent(name) {
        loadCSS(name + '.css');
        loadJS(name + '.js');
    }

    // 필요한 컴포넌트 로드
    loadComponent('header');
    loadComponent('bottom-nav');
    loadComponent('components');
});