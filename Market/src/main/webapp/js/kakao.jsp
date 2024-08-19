
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // íììì ê²ìê²°ê³¼ í­ëª©ì í´ë¦­íìë ì¤íí  ì½ëë¥¼ ìì±íë ë¶ë¶.

                // ê° ì£¼ìì ë¸ì¶ ê·ì¹ì ë°ë¼ ì£¼ìë¥¼ ì¡°í©íë¤.
                // ë´ë ¤ì¤ë ë³ìê° ê°ì´ ìë ê²½ì°ì ê³µë°±('')ê°ì ê°ì§ë¯ë¡, ì´ë¥¼ ì°¸ê³ íì¬ ë¶ê¸° íë¤.
                var addr = ''; // ì£¼ì ë³ì
                var extraAddr = ''; // ì°¸ê³ í­ëª© ë³ì

                //ì¬ì©ìê° ì íí ì£¼ì íìì ë°ë¼ í´ë¹ ì£¼ì ê°ì ê°ì ¸ì¨ë¤.
                if (data.userSelectedType === 'R') { 
                    addr = data.roadAddress;
                } else { 
                    addr = data.jibunAddress;
                }
                if(data.userSelectedType === 'R'){
                    if(data.bname !== '' && /[ë|ë¡|ê°]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>