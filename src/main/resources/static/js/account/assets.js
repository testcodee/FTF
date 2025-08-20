    document.addEventListener('DOMContentLoaded', function() {
    const accountUpdateForm = document.getElementById('accountUpdateForm');

    accountUpdateForm.addEventListener('submit', async function(event) {
    event.preventDefault(); // 폼 제출 기본 동작 방지

    const accountsToUpdate = [];
    document.querySelectorAll('.account-item').forEach(item => {
        const accountId = item.querySelector('input[name="accountId"]').value;
        const balanceInput = item.querySelector('.balance-input');
        let balance = balanceInput.value.replace(/,/g, ''); // 콤마 제거

        // 빈 값 처리 및 숫자 변환
        if (balance === '') {
            balance = '0';
        }

        accountsToUpdate.push({
        id: accountId,
        balance: parseFloat(balance) // 숫자로 변환
        });
    });

    try {
        const response = await fetch('/accounts/update', {
        method: 'POST',
        headers: {
        'Content-Type': 'application/json' // JSON 형태로 전송한다고 명시
        },
            body: JSON.stringify(accountsToUpdate) // JavaScript 객체를 JSON 문자열로 변환
        });

        const result = await response.json(); // 서버 응답을 JSON으로 파싱

        const messageDisplay = document.createElement('p');
        messageDisplay.classList.add('status-message');

        if (response.ok) { // HTTP 상태 코드가 200번대인 경우
            messageDisplay.classList.add('success-message');
            messageDisplay.textContent = result.message || '잔액이 성공적으로 업데이트되었습니다.';
        } else {
            messageDisplay.classList.add('error-message');
            messageDisplay.textContent = result.error || '잔액 업데이트 중 오류가 발생했습니다.';
        }

        // 기존 메시지 제거 후 새로 추가
        const existingMessages = document.querySelectorAll('.status-message');
        existingMessages.forEach(msg => msg.remove());

        accountUpdateForm.insertAdjacentElement('afterend', messageDisplay); // 폼 바로 뒤에 메시지 추가

        // 메시지 자동 사라지게 (옵션)
        setTimeout(() => {
        messageDisplay.style.opacity = '0';
        setTimeout(() => messageDisplay.remove(), 500);
        }, 3000);

        } catch (error) {
            console.error('Fetch error:', error);
            const messageDisplay = document.createElement('p');
            messageDisplay.classList.add('status-message', 'error-message');
            messageDisplay.textContent = '네트워크 오류가 발생했습니다.';
            accountUpdateForm.insertAdjacentElement('afterend', messageDisplay);

            setTimeout(() => {
                messageDisplay.style.opacity = '0';
                setTimeout(() => messageDisplay.remove(), 500);
            }, 3000);
        }
});

    // 잔액 입력 시 콤마 제거 및 숫자 변환 로직 (이미 assets.html에 있습니다)
    // input.addEventListener('focus', function() { ... });
    // input.addEventListener('blur', function() { ... });
});