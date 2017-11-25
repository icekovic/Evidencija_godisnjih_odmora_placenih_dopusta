$(document).ready(function(){
		
		//hidden elementi			
	    	$("#btnNoviZahtjevGodisnjiOdmor").click(function(){
	        	$(".div-novi-zahtjev-godisnji-odmor").show();
	    	});
	    	
	    	$("#btnOdustaniGodisnjiOdmor").click(function(){
	    	    $(".div-novi-zahtjev-godisnji-odmor").hide();
	    	});
	    	
	    	$("#btnNoviZahtjevPlaceniDopust").click(function(){
	        	$(".div-novi-zahtjev-placeni-dopust").show();
	    	});
	    	
	    	$("#btnOdustaniPlaceniDopust").click(function(){
	    	    $(".div-novi-zahtjev-placeni-dopust").hide();
	    	});
	    	
	    	$("#btnPregledZahtjeva").click(function(){
	        	$(".div-pregled-zahtjeva").toggle();
	    	});
	    	
	    	$(function(){
	    		$("#datum_zaposlenja").datepicker({dateFormat: 'yy-mm-dd'});
	    	});
	    	$(function(){
	    		$("#od_datuma_godisnji_odmor").datepicker({dateFormat: 'yy-mm-dd', minDate: 0});
	    	});
	    	$(function(){
	    		$("#do_datuma_godisnji_odmor").datepicker({dateFormat: 'yy-mm-dd', minDate: 0});
	    	});
	    	$(function(){
	    		$("#od_datuma_placeni_dopust").datepicker({dateFormat: 'yy-mm-dd', minDate: 0});
	    	});
	    	$(function(){
	    		$("#do_datuma_placeni_dopust").datepicker({dateFormat: 'yy-mm-dd', minDate: 0});
	    	});
	    
	    //validacije
	    	
	    	$(function(){
	    		
	    		//home
	    		$("#home").validate({
	    			rules: {
	    				korisnicko_ime:{
	    					required: true
	    				},
	    				lozinka: {
	    					required: true
	    				}
	    			},
	    			messages: {
	    				korisnicko_ime:{
	    					required: 'Korisničko ime je obavezno!'
	    				},
	    				lozinka: {
	    					required: 'Lozinka je obavezna!'
	    				}
	    			}
	    		});	    		
	    		
	    		//registracija
	    		$("#registracija").validate({
	    			rules: {
	    				ime: {
	    					required: true
	    				},
	    				prezime: {
	    					required: true
	    				},
	    				korisnicko_ime: {
	    					required: true
	    				},
	    				lozinka: {
	    					required: true
	    				},
	    				ponovljena_lozinka: {
	    					required: true,
	    					equalTo: "#lozinka"
	    				},
	    				maticni_broj: {
	    					required: true
	    				},
	    				datum_zaposlenja: {
	    					required: true
	    				},
	    				godine_staza: {
	    					required: true
	    				},
	    				broj_djece: {
	    					required: true
	    				}
	    			},
	    			messages: {
	    				ime: {
	    					required: 'Ime je obavezno!'
	    				},
	    				prezime: {
	    					required: 'Prezime je obavezno!'
	    				},
	    				korisnicko_ime: {
	    					required: 'Korisničko ime je obavezno!'
	    				},
	    				lozinka: {
	    					required: 'Lozinka je obavezna!'
	    				},
	    				ponovljena_lozinka: {
	    					required: 'Morate ponoviti lozinku!',
	    					equalTo: 'Lozinke se ne podudaraju!'
	    				},
	    				maticni_broj: {
	    					required: 'Matični broj je obavezan!'
	    				},
	    				datum_zaposlenja: {
	    					required: 'Datum zaposlenja je obavezan!'
	    				},
	    				godine_staza: {
	    					required: 'Godine staža su obavezne!',
	    					number: 'Morate unijeti broj!'
	    				},
	    				broj_djece: {
	    					required: 'Broj djece je obavezan!',
	    					number: 'Morate unijeti broj!'
	    				}
	    			}
	    		});
	    		
	    	});
	    	
	    	//broj dana godišnjih odmora    	
	    	$("#od_datuma_godisnji_odmor").datepicker({
	    		dateFormat: "yy-mm-dd",
	    	    minDate: 0,
	    	    maxDate: '+1Y+6M',
	    	    onSelect: function (dateStr) {
	    	        var min = $(this).datepicker('getDate'); // Get selected date
	    	        $("#do_datuma_godisnji_odmor").datepicker('option', 'minDate', min || '0'); // Set other min, default to today
	    	    }
	    	});

	    	$("#do_datuma_godisnji_odmor").datepicker({
	    		dateFormat: "yy-mm-dd",
	    	    minDate: '0',
	    	    maxDate: '+1Y+6M',
	    	    onSelect: function (dateStr) {
	    	        var max = $(this).datepicker('getDate'); // Get selected date
	    	        $('#datepicker').datepicker('option', 'maxDate', max || '+1Y+6M'); // Set other max, default to +18 months
	    	        var start = $("#od_datuma_godisnji_odmor").datepicker("getDate");
	    	        var end = $("#do_datuma_godisnji_odmor").datepicker("getDate");
	    	        var days = (end - start) / (1000 * 60 * 60 * 24);
	    	        $("#broj_radnih_dana").val(days);	    	        
	    	    }
	    	});
	    	
	    	
	    	//razlika dana plaćenih dopusta
	    	$("#od_datuma_placeni_dopust").datepicker({
	    		dateFormat: "yy-mm-dd",
	    	    minDate: 0,
	    	    maxDate: '+1Y+6M',
	    	    onSelect: function (dateStr) {
	    	        var min = $(this).datepicker('getDate'); // Get selected date
	    	        $("#do_datuma_placeni_dopust").datepicker('option', 'minDate', min || '0'); // Set other min, default to today
	    	    }
	    	});

	    	$("#do_datuma_placeni_dopust").datepicker({
	    		dateFormat: "yy-mm-dd",
	    	    minDate: '0',
	    	    maxDate: '+1Y+6M',
	    	    onSelect: function (dateStr) {
	    	        var max = $(this).datepicker('getDate'); // Get selected date
	    	        $('#datepicker').datepicker('option', 'maxDate', max || '+1Y+6M'); // Set other max, default to +18 months
	    	        var start = $("#od_datuma_placeni_dopust").datepicker("getDate");
	    	        var end = $("#do_datuma_placeni_dopust").datepicker("getDate");
	    	        var days = (end - start) / (1000 * 60 * 60 * 24);
	    	        $("#razlika_dana").val(days);	    	        
	    	    }
	    	});
		});

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

//godišnji kalendar
$(function() {
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
	  
      customDayRenderer: function(element, date) {
          if(date.getTime() == svetiStjepan) {
              $(element).css('background-color', 'red');
              $(element).css('color', 'gold');
              $(element).css('border-radius', '0px');
          }
          
          else if(date.getTime() == bozic) {
              $(element).css('background-color', 'red');
              $(element).css('color', 'gold');
              $(element).css('border-radius', '0px');
          }
          
          else if(date.getTime() == sviSveti) {
              $(element).css('background-color', 'red');
              $(element).css('color', 'gold');
              $(element).css('border-radius', '0px');
          }
          
          else if(date.getTime() == danNeovisnosti) {
			  $(element).css('background-color', 'red');
              $(element).css('color', 'gold');
              $(element).css('border-radius', '0px');
          }
          
          else if(date.getTime() == velikaGospa) {
              $(element).css('background-color', 'red');
              $(element).css('color', 'gold');
              $(element).css('border-radius', '0px');
          }
          
          else if(date.getTime() == danDomovinskeZahvalnosti) {
              $(element).css('background-color', 'red');
              $(element).css('color', 'gold');
              $(element).css('border-radius', '0px');
          }
          
          else if(date.getTime() == danDrzavnosti) {
              $(element).css('background-color', 'red');
              $(element).css('color', 'gold');
              $(element).css('border-radius', '0px');
          }
          
          else if(date.getTime() == danAntifasistickeBorbe) {
              $(element).css('background-color', 'red');
              $(element).css('color', 'gold');
              $(element).css('border-radius', '0px');
          }
          
          else if(date.getTime() == tijelovo) {
              $(element).css('background-color', 'red');
              $(element).css('color', 'gold');
              $(element).css('border-radius', '0px');
          }
          
          else if(date.getTime() == praznikRada) {
              $(element).css('background-color', 'red');
              $(element).css('color', 'gold');
              $(element).css('border-radius', '0px');
          }
          
          else if(date.getTime() == uskrs) {
              $(element).css('background-color', 'red');
              $(element).css('color', 'gold');
              $(element).css('border-radius', '0px');
          }
          
          else if(date.getTime() == uskrsnjiPonedjeljak) {
              $(element).css('background-color', 'red');
              $(element).css('color', 'gold');
              $(element).css('border-radius', '0px');
          }
          
          else if(date.getTime() == svetaTriKralja) {
              $(element).css('background-color', 'red');
              $(element).css('color', 'gold');
              $(element).css('border-radius', '0px');
          }
          
          else if(date.getTime() == novaGodina) {
              $(element).css('background-color', 'red');
              $(element).css('color', 'gold');
              $(element).css('border-radius', '0px');
          }
      }
  });
});
