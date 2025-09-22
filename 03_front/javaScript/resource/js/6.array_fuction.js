//자료형, 목적에따라  가장 간결하고 명확하게 구현하기 위한 다양한 반복문이 존재.
//읽기/변환/필터링/검색...목적에 맞는 반복문을 골라 사용해야 가독성이 높아짐.

let members = [
    "최지원",
    "김지원", 
    "이지원",
    "박지원",
    "정지원",
    "황지원"
]

console.log(members.push("신지원"));
console.log(members);

//splice(인덱스, 몇 개) -> 원본에서 특정 인덱스부터 n개를 잘라냄.
console.log(members.splice(1, 3));
console.log(members);

//slice(인덱스, 마지막인텍스 -1) -> 원본에서 특정 인덱스부터 마지막인텍스 -1개를 추출함.(원본영향없음)
console.log(members.slice(0, 3));
console.log(members);

let members1 = [
    "최지원",
    "김지원", 
    "이지원",
    "박지원",
    "정지원",
    "황지원"
]

//...배열 또는 ...객체 -> spread연산자 
//배열이나 객체의 요소를 개별적으로 펼처서 복사하거나 전달할 때 사용.
let members2 = [
    ...members1,
    "신지원"
]

console.log(members2);

//비구조할당
//배열이나 객체에서 값을 추출할 때 개별변수에 할당해서 추출하는 문법

//js의 반복문

//for..of
//배열값 중심으로 순회한다. 가장 깔끔하며 인덱스가 필요없을 때 사용.
console.log("==========================for..of==========================")
for(const std of stdList){
    console.log(std);
}


//for..in
//인덱스 중심으로 순회한다. 
console.log("==========================for..in==========================")
for(const i in stdList){
    console.log(stdList[i]);
}

//--------배열의 고차함수------------------
//읽기전용 + 새로운배열/값을 반환 -> 불변성 유지를 위해