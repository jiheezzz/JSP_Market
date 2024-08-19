//제목 행의 체크박스의 체크여부를 확인
const allChk = document.querySelector('#allChk');

//제목 줄의 체크박스가 클릭되면..
allChk.addEventListener('click',function(){
	//참고)
	//alert(allChk.checked);
	//allChk.id; //allChk
	//allChk.type;//checkbox

	const isChecked = allChk.checked;//체크된 제목줄 체크박스를 isChecked라고 한다.

	//클래스가 chk 인 태그들을 모두 데리고 온다.
	// 장바구니의 모든 체크박스를 체크한다.
	const chk = document.querySelectorAll('.chk');
	
	//제목 줄의 체크박스가 체크 됐다면..
	if(isChecked){
		//chk.checked = true;
		
		//자바스크립트는 for문사용가능하나 자료형이 없어서 int 사용은 안한다
		for(i = 0; i<chk.length; i++){
			chk[i].checked = true;//체크된 클래스가 chk의 값은 true로 준다.
		}
		
		//자바스크립트의 for-each문: of를 사용한다!
		//for(const e of chk){
		//	e.checked = true;
		//}
	}
	else{
		for(i = 0; i<chk.length; i++){
			chk[i].checked = false;//체크된 클래스가 chk의 값은 true로 준다.
		}
	}
});

//제목줄 아래 체크박스칸들이 전부! 체크가 된다면..
	//제목줄 아래 체크박스칸들이 전부! 가져온다.
	const chk = document.querySelectorAll('.chk');
	
	for(const e of chk){
		//원래라면..
		//매개변수 e
		//e.addEventListener('click',function(e){});
		
		//위의 이벤트문을 조금 변경한다면..
		e.addEventListener('click',e=>{
			const cnt = chk.length; //제목줄 밑의 전체 체크박스의 갯수는 cnt
			const checkedCnt = document.querySelectorAll('.chk:checked').length; //클래스가 chk 인 것들 중에서 체크된것만 들고오겠다.그것의 갯수.
		
		if(cnt == checkedCnt){
			document.querySelector('#allChk').checked = true;
		}
		
		else{
			document.querySelector('#allChk').checked = false;
					
		}		
	});	
}

//선택구매버튼을 클릭시
//클릭하면, '선택한 상품을 구매하시겠습니까?'팝업창을 띄운다.
function goBuy(){
	const checkedCnt = document.querySelectorAll('.chk:Checked').length;//체크박스들중에서 체크된 것들만!(all)그것의 갯수.
	
	if(checkedCnt == 0){
		alert('선택한 상품이 없습니다.');		
		return ;
		//리턴의 2가지 기능
		//1)데이터를 넘기도록 돌려줄 값을 쓸 때(일반적) 
		//2)자바에서는 메소드이기때문에 뒤에 아무값도없으면 더이상 읽지않도록 함수종료 기능.
	}
	
	//장바구니에서 선택한 상품 구매하기 기능 구현
	//넘겨야하는 데이터: 내가 체크한 ITEM_CODE,BUY_CNT,BUY_CODE, //TOTAL_PRICE(상관없어서 넘기지않음)
	//ITEM_CODE, BUY_CNT, totalPrice..
	
	//총가격(장바구니전체):우선,내가 체크된 체크박스와 같은 줄에있는 '총가격(상품별)'만 가져간다. 그리고 그들끼리만 더한다.
	const checkedBoxes = document.querySelectorAll('.chk:checked'); // 여러개가지고 오니까 all 사용
	let totalPrice = 0; // 변하기때문 let 사용한다.
	let itemCodes = ''; // 변하기때문 let 사용한다.
	let buyCnts = ''; // 변하기때문 let 사용한다.
	let cartCodes = ''; // 변하기때문 let 사용한다.
	
	//반복문사용해서 각 체크박스들의 각 총가격을 빼낸다
	for(const checkBox of checkedBoxes){//체크된 checkedBoxes들 중에 하나씩 뺀다 그것을 checkBox 라고한다.
		//자식,부모 태그 선택 함수
		//parentElement() : 가장 가까운 부모태그를 찾아가는 함수
		//children : 가장 가까운 자식태그를 찾아가는 함수
		//previousSibling : 이전 형제 노드를 찾아 감. 제이쿼리는 prev()
		//nextElementSibling() : 다음 형제 노드를 찾아 감. 
		//closest() : 가장 가까운 상위태그를 찾아가는 함수

		//체크박스기준 감싸고있는 것중 가장가까운 tr 태그에서 6번째 자식태그안에 내용
		const price = checkBox.closest('tr').children[5].innerText; 
		totalPrice = totalPrice + parseInt(price); 
			//총가격은 각 총가격에 가격을 누적시킨다.
			//price는 위에서 const자료형이라 숫자합계산이 안되므로 정수형변화시키기!!
		//alert(checkBox.value);

		//상품코드 값 선택 후 누적합 계산
		const itemCode = checkBox.value;
		itemCodes = itemCodes + itemCode +',';//ITEM_001,ITEM_002, 
		
		//구매수량 값 선택 후 누적합 계산
		const buyCnt = checkBox.closest('tr').children[4].innerText; 
		buyCnts = buyCnts + buyCnt +',';
		
		//카테고리코드 값 선택 후 누적 합 계산
		//우리가 임의로 만든 속성(data-cartCode)은 다른 방법으로 데이터 추출
		const cartCode= checkBox.dataset.cartcode;//jsp에서 cartCode이지만 js는 항상 소문자로 가져오기때문에 소문자로 작성해야 오류발생x
		console.log(checkBox.dataset);// 개발자콘솔창확인시, data-cart-code/data-cartCode(data-cartcode) 의 차이점 파악하기위해 
		cartCodes = cartCodes + cartCode +',';//CART_001.CART_002,..
	
	}
	document.querySelector('form[action="insertBuys.buy"]').submit();
}	