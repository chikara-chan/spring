/**
 * 自定义的JavaScript函数库
 * 
 * @author chikara
 * 
 */

function setCookie(name, value) {
	var date = new Date();
	date.setTime(date.getTime() + 1000 * 60 * 60 * 24 * 7);
	document.cookie = name + "=" + escape(value) + ";expires="
			+ date.toGMTString() + ";path=/";
}
function getCookie(name) {
	var array = document.cookie.split(';');
	for (i in array) {
		var temp = array[i].split('=');
		if (temp[0].trim() == name) {
			return unescape(temp[1]);
		}
	}
	return null;
}
function changeCaptcha(obj) {
	var src = $(obj).attr('src');
	var i = src.indexOf('?');
	var newSrc;
	if (i > 0) {
		newSrc = src.substring(0, i + 1) + Math.random();
	} else {
		newSrc = src + '?' + Math.random();
	}
	$(obj).attr('src', newSrc);
}