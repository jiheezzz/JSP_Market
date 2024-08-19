<style>
table{
width: 40%;
}
</style>
<script>
function OK2(orderG){
	alert(orderG+" OK?");
	location.href="${path}/CartController?sw=OD&orderG="+orderG;
}

</script>