document.addEventListener('DOMContentLoaded', function() {
    // 🎯 더미 데이터 (실제로는 백엔드에서 Fetch API 등으로 가져올 데이터)
    // 아이콘 클래스는 Font Awesome을 예시로 들었습니다.
    // 색상은 각 카테고리에 할당될 고유한 색상입니다.
    const rawSpendingData = [
        { category: '식비', amount: 350000, iconClass: 'fas fa-utensils', color: '#FF6384' },
        { category: '교통', amount: 120000, iconClass: 'fas fa-bus', color: '#36A2EB' },
        { category: '카페/간식', amount: 80000, iconClass: 'fas fa-coffee', color: '#FFCE56' },
        { category: '문화생활', amount: 0, iconClass: 'fas fa-film', color: '#4BC0C0' }, // 지출 0
        { category: '쇼핑', amount: 200000, iconClass: 'fas fa-shopping-bag', color: '#9966FF' },
        { category: '통신', amount: 50000, iconClass: 'fas fa-phone', color: '#FF9F40' },
        { category: '생활용품', amount: 70000, iconClass: 'fas fa-lightbulb', color: '#C9CBCF' },
        { category: '월세', amount: 500000, iconClass: 'fas fa-home', color: '#7E7A7A' }
    ];

    // 🎯 지출이 0이 아닌 카테고리만 필터링
    const spendingData = rawSpendingData.filter(data => data.amount > 0);

    const categories = spendingData.map(data => data.category);
    const amounts = spendingData.map(data => data.amount);
    const colors = spendingData.map(data => data.color);
    const totalExpense = amounts.reduce((sum, current) => sum + current, 0);

    // 총 지출 요약 업데이트
    document.getElementById('total-expense').textContent = totalExpense.toLocaleString('ko-KR') + '원';

    // 🎯 Chart.js 설정 및 렌더링
    const ctx = document.getElementById('categoryExpenseChart').getContext('2d');
    const categoryExpenseChart = new Chart(ctx, {
        type: 'doughnut', // 원형 그래프 중에서도 '얇은' 효과를 위해 도넛 차트 선택
        data: {
            labels: categories,
            datasets: [{
                data: amounts,
                backgroundColor: colors,
                borderColor: '#ffffff', // 각 조각 사이의 경계선 색상
                borderWidth: 2,
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false, // 컨테이너에 맞춰 크기 조절
            cutout: '70%', // 도넛 차트의 두께 (숫자가 클수록 얇아짐)
            plugins: {
                tooltip: {
                    callbacks: {
                        label: function(context) {
                            const label = context.label || '';
                            const value = context.parsed || 0;
                            const total = context.dataset.data.reduce((acc, current) => acc + current, 0);
                            const percentage = ((value / total) * 100).toFixed(1);
                            return `${label}: ${value.toLocaleString('ko-KR')}원 (${percentage}%)`;
                        }
                    }
                },
                legend: {
                    display: false, // Chart.js 기본 범례는 사용하지 않고 커스텀 범례 생성
                },
            }
        }
    });

    // 🎯 커스텀 카테고리 리스트 생성 및 렌더링
    const categoryListElement = document.getElementById('categoryList');
    categoryListElement.innerHTML = ''; // 기존 내용 초기화

    spendingData.forEach(data => {
        const listItem = document.createElement('li');

        // 아이콘 wrapper (색상 배경)
        const iconWrapper = document.createElement('div');
        iconWrapper.className = 'icon-wrapper';
        iconWrapper.style.backgroundColor = data.color;

        // 아이콘 (Font Awesome 사용)
        const icon = document.createElement('i');
        icon.className = data.iconClass; // 예: fas fa-utensils
        iconWrapper.appendChild(icon);

        const categoryName = document.createElement('span');
        categoryName.className = 'category-name';
        categoryName.textContent = data.category;

        const total = amounts.reduce((sum, current) => sum + current, 0);
        const percentage = ((data.amount / total) * 100).toFixed(1);
        const percentageSpan = document.createElement('span');
        percentageSpan.className = 'percentage';
        percentageSpan.textContent = percentage + '%';

        const amountSpan = document.createElement('span');
        amountSpan.className = 'amount';
        amountSpan.textContent = data.amount.toLocaleString('ko-KR') + '원';

        listItem.appendChild(iconWrapper);
        listItem.appendChild(categoryName);
        listItem.appendChild(percentageSpan);
        listItem.appendChild(amountSpan);
        categoryListElement.appendChild(listItem);
    });
});