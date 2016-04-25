 function checkphone(){
	
		var phone = formset.username.value;//用户名
		var reg = "^[a-zA-Z][a-zA-Z_0-9]{5,}$";//正则
		if(!phone.match(reg)){
			document.getElementById('divname').innerHTML='<font class="tips_false">字母开头且不小于6位</font>'; 
		}else {
			document.getElementById('divname').innerHTML='<font class="tips_true">账号填写正确</font>';
		}
			 
	}
 
 function checkna(){
		na=formset.name.value;
	  	if( na.length<1 || na.length >12)  
		{  	
	  		document.getElementById('divname2').innerHTML='<font class="tips_false">长度是1~12个字符</font>';    
		}else{  
			document.getElementById('divname2').innerHTML='<font class="tips_true">个性昵称填写正确</font>';
		}  
}
//验证密码 
	function checkpsd1(){    
		psd1=formset.password.value;  
		if(psd1.length<6){   
			document.getElementById('divpassword1').innerHTML='<font class="tips_false">长度不得小于6位</font>';
		}else
			{   
			document.getElementById('divpassword1').innerHTML='<font class="tips_true">密码填写正确</font>';
			 
	}
	}
	//验证确认密码 
	function checkpsd2(){ 
		psd1=formset.password.value; 
		psd2=formset.rePassword.value; 
			if(psd1!=psd2||psd2=="") { 
			     document.getElementById('divpassword2').innerHTML='<font class="tips_false">两次密码不一样</font>';
			}
            else { 
			     document.getElementById('divpassword2').innerHTML='<font class="tips_true">密码填写正确</font>';
			}
	}
	//注册按钮提交
	function sub(){
				var phone = formset.username.value;//用户名
				var reg = "^[a-zA-Z][a-zA-Z_0-9]{5,}$";//正则
				if(!phone.match(reg)){
					formset.username.focus;
					return false;
				}
			   if(formset.name.value.length<1 || formset.name.value.length>12)
			   		{
			   			alert(1);
				   formset.name.focus();
			   			return false;
			   		}
			   if(formset.password.value.length<6)
		   		{
		   			
				   formset.password.focus();
		   			return false;
		   		}
			   if(formset.password.value!=formset.rePassword.value)
		   		{
		   			
		   			formset.rePassword.focus();
		   			return false;
		   		}
			   	
			   }
	    
	//提示框隐藏
	function hide(){
		var sbtitle=document.getElementById('hbox');
		if(sbtitle){
		if(sbtitle.style.display=='block'){
		sbtitle.style.display='none';
		}else{
		sbtitle.style.display='block';
		}
		}
	}
	
