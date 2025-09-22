//함수 내부에 값이 있으면 그 값을 사용, 없다면 전역에서 값을 찾아서 사용.

// var num1 = 20;
// var num2 = 10;

// function test3(){
//     var num1 = 40;
//     let num2 = 20;
//     test4();
//     console.log(num1);
// }

let j = 1000;
for(let j = 0; j<10; j++){
    console.log(j);
}
console.log("j : "+j);

//var는 함수 스코프이기때문에 for문내에 새로운 i가 생기지않고 전역에 i를 저장함. for문이 종료되어도 전역 i는 그대로 유지됨
//let, const는 블록 스코프이기때문에 for문내에 새로운 스코프를 만들고 j를 해당 스코프에 저장해 사용한 뒤 for문이 종료되면 제거된다
//별개로 전역에 있는 j는 그대로 전역변수로 사용됨.