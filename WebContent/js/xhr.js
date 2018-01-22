 function getXmlHttpRequest(){
	 var xhr;
	 if(window.XMLHttpRequest){
		 //判断是否成立 成立说明是ie7+以上的浏览器，或者其他的浏览器
		 xhr=new XMLHttpRequest();
	 }else{
		 xhr=new ActiveXObject("Microsoft.XMLHTTP");
	 }
	 return xhr;
 }