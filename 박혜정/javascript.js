// ===== 전역 변수 =====
let transactions = JSON.parse(localStorage.getItem('transactions')) || [];
let filterState = 'all'; 

// ===== DOM 요소 =====
const descInput = document.getElementById('desc-input');
const amountInput = document.getElementById('amount-input');
const incomeBtn = document.getElementById('income-input');
const expenseBtn = document.getElementById('expense-input');
const addBtn = document.getElementById('add-btn');
const transactionList = document.getElementById('transaction-list');
const filterBtns = document.querySelectorAll('.filter-buttons button');

// 요약 영역
const totalIncomeEl = document.getElementById('total-income');
const totalExpenseEl = document.getElementById('total-expense');
const balanceEl = document.getElementById('balance');
const summaryBalanceEl = document.getElementById('summary-balance');

// 선택된 타입 (income/expense)
let selectedType = null;

// ===== 초기화 =====
document.addEventListener('DOMContentLoaded', init);

function init() {
    bindEvents();
    render();
}

// ===== 이벤트 바인딩 =====
function bindEvents() {
    // 수입 버튼
    incomeBtn.addEventListener('click', function () {
        selectedType = 'income';
        incomeBtn.classList.add('active');
        expenseBtn.classList.remove('active');
    });

    // 지출 버튼
    expenseBtn.addEventListener('click', function () {
        selectedType = 'expense';
        expenseBtn.classList.add('active');
        incomeBtn.classList.remove('active');
    });

    // 추가 버튼
    addBtn.addEventListener('click', addTransaction);

    // Enter 키로도 추가 가능
    amountInput.addEventListener('keydown', function (e) {
        if (e.key === 'Enter') addTransaction();
    });

    // 필터 버튼들
    filterBtns.forEach(function (btn) {
        btn.addEventListener('click', function (e) {
            setFilter(e.target.dataset.filter);
        });
    });
}

// ===== 데이터 조작 =====
function addTransaction() {
    const desc = descInput.value.trim();
    const amount = parseInt(amountInput.value.trim(), 10);

    if (!desc || isNaN(amount) || amount <= 0 || !selectedType) {
        alert("내용, 금액, 수입/지출을 올바르게 입력하세요!");
        return;
    }

    const transaction = {
        id: Date.now(),
        description: desc,
        amount: amount,
        type: selectedType, // income or expense
        date: new Date().toLocaleDateString()
    };

    transactions.push(transaction);
    saveTransactions();

    // 입력값 초기화
    descInput.value = "";
    amountInput.value = "";
    selectedType = null;
    incomeBtn.classList.remove('active');
    expenseBtn.classList.remove('active');

    render();
}

function deleteTransaction(id) {
    transactions = transactions.filter(t => t.id !== id);
    saveTransactions();
    render();
}

function getFilteredTransactions() {
    if (filterState === 'income') {
        return transactions.filter(t => t.type === 'income');
    } else if (filterState === 'expense') {
        return transactions.filter(t => t.type === 'expense');
    }
    return transactions;
}

function saveTransactions() {
    localStorage.setItem('transactions', JSON.stringify(transactions));
}

// ===== 렌더링 =====
function render() {
    transactionList.innerHTML = "";

    const filtered = getFilteredTransactions();

    if (filtered.length === 0) {
        const emptyEl = document.createElement('div');
        emptyEl.className = 'empty-state';
        transactionList.appendChild(emptyEl);
    } else {
        filtered.forEach(t => renderItem(t));
    }

    updateSummary();
}

function renderItem(t) {
    const li = document.createElement('li');
    li.className = `transaction-item ${t.type}`;

    li.innerHTML = `
        <span class="transaction-date">${t.date}</span>
        <span class="transaction-desc">${t.description}</span>
        <span class="transaction-amount ${t.type}">
            ${t.type === 'expense' ? '-' : '+'}${t.amount.toLocaleString()}원
        </span>
        <button class="delete-btn">삭제</button>
    `;

    li.querySelector('.delete-btn').addEventListener('click', function () {
        deleteTransaction(t.id);
    });

    transactionList.appendChild(li);
}

// ===== 요약 업데이트 =====
function updateSummary() {
    let income = 0;
    let expense = 0;

    transactions.forEach(t => {
        if (t.type === 'income') income += t.amount;
        else expense += t.amount;
    });

    totalIncomeEl.innerText = `${income.toLocaleString()}원`;
    totalExpenseEl.innerText = `${expense.toLocaleString()}원`;
    balanceEl.innerText = `${(income - expense).toLocaleString()}원`;
    summaryBalanceEl.innerText = `${(income - expense).toLocaleString()}원`;
}

// ===== 필터 =====
function setFilter(filter) {
    filterState = filter;

    filterBtns.forEach(btn => {
        btn.className = (btn.dataset.filter === filter ? "active" : "");
    });

    render();
}