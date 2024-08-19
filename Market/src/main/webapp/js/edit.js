
function Klist(){
	location.href="${path}/MemberController?sw=S";
}
function KKlist(){
	location.href="${path}/ProductController?sw=S";
}

function Kcart(){
	amount=f1.amount.value
	product_id =f1.product_id.value
	mid=f1.mid.value
	location.href="${path}/CartController?sw=I&amount="+amount+"&product_id="+product_id+"&mid="+mid;
	alert(amount+":"+product_id+":"+mid)
	
	
}function elist(){
	location.href="${path}/BoardController?sw=S";
}

