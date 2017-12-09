//uskrs
function easterForYear (year) {
	  var a = year % 19;
	  var b = Math.floor(year / 100);
	  var c = year % 100;
	  var d = Math.floor(b / 4); 
	  var e = b % 4;
	  var f = Math.floor((b + 8) / 25);
	  var g = Math.floor((b - f + 1) / 3); 
	  var h = (19 * a + b - d - g + 15) % 30;
	  var i = Math.floor(c / 4);
	  var k = c % 4;
	  var l = (32 + 2 * e + 2 * i - h - k) % 7;
	  var m = Math.floor((a + 11 * h + 22 * l) / 451);
	  var n0 = (h + l + 7 * m + 114)
	  var n = Math.floor(n0 / 31) - 1;
	  var p = n0 % 31 + 1;
	  var date = new Date(year,n,p);
	  return date; 
	}

$(function(){
	
  var currentYear = new Date().getFullYear();
  var svetiStjepan = new Date(currentYear, 11, 26).getTime();
  var bozic = new Date(currentYear, 11, 25).getTime();
  var sviSveti = new Date(currentYear, 11, 0).getTime();
  var danNeovisnosti = new Date(currentYear, 9, 8).getTime();
  var velikaGospa = new Date(currentYear, 7, 15).getTime();
  var danDomovinskeZahvalnosti = new Date(currentYear, 7, 5).getTime();
  var danDrzavnosti = new Date(currentYear, 5, 25).getTime();
  var danAntifasistickeBorbe = new Date(currentYear, 5, 22).getTime();
  var tijelovo = new Date(currentYear, 5, 15).getTime();
  var praznikRada = new Date(currentYear, 4, 1).getTime();
  var uskrs = easterForYear(currentYear).getTime();
  var uskrsnjiPonedjeljak = new Date(currentYear, 3, 17).getTime();
  var svetaTriKralja = new Date(currentYear, 0, 6).getTime();
  var novaGodina = new Date(currentYear, 0, 1).getTime();
  
  $('#calendar').calendar({
	  
	  customDayRenderer: function(element, date){
		if(date.getTime() == svetiStjepan){
		   $(element).css('background-color', 'red');
           $(element).css('color', 'gold');
           $(element).css('border-radius', '0px');
		}  
		
		if(date.getTime() == bozic){
			   $(element).css('background-color', 'red');
	           $(element).css('color', 'gold');
	           $(element).css('border-radius', '0px');
			}  
		
		if(date.getTime() == sviSveti){
			   $(element).css('background-color', 'red');
	           $(element).css('color', 'gold');
	           $(element).css('border-radius', '0px');
			}  
		
		if(date.getTime() == danNeovisnosti){
			   $(element).css('background-color', 'red');
	           $(element).css('color', 'gold');
	           $(element).css('border-radius', '0px');
			}  
		
		if(date.getTime() == velikaGospa){
			   $(element).css('background-color', 'red');
	           $(element).css('color', 'gold');
	           $(element).css('border-radius', '0px');
			}  
		
		if(date.getTime() == danDomovinskeZahvalnosti){
			   $(element).css('background-color', 'red');
	           $(element).css('color', 'gold');
	           $(element).css('border-radius', '0px');
			}  
		
		if(date.getTime() == danDrzavnosti){
			   $(element).css('background-color', 'red');
	           $(element).css('color', 'gold');
	           $(element).css('border-radius', '0px');
			}  
		
		if(date.getTime() == danAntifasistickeBorbe){
			   $(element).css('background-color', 'red');
	           $(element).css('color', 'gold');
	           $(element).css('border-radius', '0px');
			}  
		
		if(date.getTime() == tijelovo){
			   $(element).css('background-color', 'red');
	           $(element).css('color', 'gold');
	           $(element).css('border-radius', '0px');
			}  
		
		if(date.getTime() == praznikRada){
			   $(element).css('background-color', 'red');
	           $(element).css('color', 'gold');
	           $(element).css('border-radius', '0px');
			}  
		
		if(date.getTime() == uskrs){
			   $(element).css('background-color', 'red');
	           $(element).css('color', 'gold');
	           $(element).css('border-radius', '0px');
			}  
		
		if(date.getTime() == uskrsnjiPonedjeljak){
			   $(element).css('background-color', 'red');
	           $(element).css('color', 'gold');
	           $(element).css('border-radius', '0px');
			} 
		
		if(date.getTime() == svetaTriKralja){
			   $(element).css('background-color', 'red');
	           $(element).css('color', 'gold');
	           $(element).css('border-radius', '0px');
			} 
		
		if(date.getTime() == novaGodina){
			   $(element).css('background-color', 'red');
	           $(element).css('color', 'gold');
	           $(element).css('border-radius', '0px');
			} 
	  }
  });
});