document.addEventListener('DOMContentLoaded', function() {
    // 폼 요소 가져오기
    const signupForm = document.querySelector('.signup-form');

    // 입력 필드 가져오기
    const usernameInput = document.getElementById('username');
    const nameInput = document.getElementById('name');
    const nicknameInput = document.getElementById('nickname');
    const emailInput = document.getElementById('email'); // 이메일 필드 추가
    const passwordInput = document.getElementById('password');

    // 유효성 검사 상태를 저장할 객체
    const validationState = {
        username: false,
        name: false,
        nickname: false,
        email: false, // 이메일 유효성 검사 상태 추가
        password: false
    };

    // 회원가입 버튼 활성화/비활성화 함수
    function toggleSubmitButton() {
        const submitButton = document.querySelector('.signup-btn');
        const isFormValid = Object.values(validationState).every(state => state);

        submitButton.disabled = !isFormValid;
        submitButton.style.opacity = isFormValid ? '1' : '0.5';
    }

    // 이메일 유효성 검사 추가
    emailInput.addEventListener('input', function() {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        validationState.email = emailRegex.test(this.value);

        if (validationState.email) {
            this.style.borderColor = '#3897f0';
        } else {
            this.style.borderColor = this.value ? '#ed4956' : '#dbdbdb';
        }

        toggleSubmitButton();
    });

    // 아이디 유효성 검사 (4~12자 영문, 숫자)
    usernameInput.addEventListener('input', function() {
        const usernameRegex = /^[a-zA-Z0-9]{4,12}$/;
        validationState.username = usernameRegex.test(this.value);

        if (validationState.username) {
            this.style.borderColor = '#3897f0';
        } else {
            this.style.borderColor = this.value ? '#ed4956' : '#dbdbdb';
        }

        toggleSubmitButton();
    });

    // 이름 유효성 검사
    nameInput.addEventListener('input', function() {
        validationState.name = this.value.length >= 2;

        if (validationState.name) {
            this.style.borderColor = '#3897f0';
        } else {
            this.style.borderColor = this.value ? '#ed4956' : '#dbdbdb';
        }

        toggleSubmitButton();
    });

    // 별명 유효성 검사
    nicknameInput.addEventListener('input', function() {
        validationState.nickname = this.value.length >= 2;

        if (validationState.nickname) {
            this.style.borderColor = '#3897f0';
        } else {
            this.style.borderColor = this.value ? '#ed4956' : '#dbdbdb';
        }

        toggleSubmitButton();
    });

    // 비밀번호 유효성 검사
    passwordInput.addEventListener('input', function() {
        validationState.password = this.value.length >= 6;

        if (validationState.password) {
            this.style.borderColor = '#3897f0';
        } else {
            this.style.borderColor = this.value ? '#ed4956' : '#dbdbdb';
        }

        toggleSubmitButton();
    });

    // 폼 제출 이벤트 처리
    signupForm.addEventListener('submit', async function (event) {
        event.preventDefault();

        // 폼 유효성 검사 (이미 통과했다고 가정)
        const formData = {
            username: usernameInput.value,
            name: nameInput.value,
            nickname: nicknameInput.value,
            email: emailInput.value,
            password: passwordInput.value
        };

        try {
            const response = await fetch('/signup', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            });

            // 응답 상태 코드 확인
            if (response.ok) {
                // 성공 처리
                alert("회원가입에 성공했습니다. 로그인 해 주세요.")
                window.location.href = '/';  // 로그인 페이지로 리다이렉트
            } else {
                // 서버에서 오류 응답을 보낸 경우
                const errorData = await response.json();
                alert('회원가입 실패: ' + (errorData.message || '알 수 없는 오류가 발생했습니다.'));
            }
        } catch (error) {
            // 네트워크 오류 등
            console.error('오류 발생:', error);
            alert('회원가입 처리 중 오류가 발생했습니다.');
        }
    });

    // 초기 버튼 상태 설정
    toggleSubmitButton();
});