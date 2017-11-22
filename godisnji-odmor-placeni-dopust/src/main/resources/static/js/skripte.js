$(document).ready(function(){
		
		//hidden elementi			
	    	$("#btnNoviZahtjev").click(function(){
	        	$(".div-novi-zahtjev").show();
	    	});
	    	
	    	$("#btnOdustani").click(function(){
	    	    $(".div-novi-zahtjev").hide();
	    	});
	    	
	    	$("#btnPregledZahtjeva").click(function(){
	        	$(".div-pregled-zahtjeva").toggle();
	    	});
	    	 	
	    	//datepicker
	    	$(function(){
	    		$.datepicker.setDefaults($.datepicker.regional['hr']);
	    	});
	    	
	    	$(function(){
	    		$("#datum_zaposlenja").datepicker({dateFormat: 'yy-mm-dd'});
	    	});
	    	$(function(){
	    		$("#od_datuma").datepicker({dateFormat: 'yy-mm-dd', minDate: 0});
	    	});
	    	$(function(){
	    		$("#do_datuma").datepicker({dateFormat: 'yy-mm-dd', minDate: 1});
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
	    	
	    	//broj dana
	    	
	    	$("#od_datuma").datepicker({
	            dateFormat: "yy-mm-dd",
	            minDate: 0,
	            onSelect: function (date) {
	                var date2 = $('#od_datuma').datepicker('getDate');
	                date2.setDate(date2.getDate() + 1);
	                $('#do_datuma').datepicker('setDate', date2);
	                //sets minDate to dt1 date + 1
	                $('#do_datuma').datepicker('option', 'minDate', date2);
	                calculate();
	            }
	        });
	        $('#do_datuma').datepicker({
	            dateFormat: "yy-mm-dd",
	            onClose: function () {
	                var dt1 = $('#od_datuma').datepicker('getDate');
	                console.log(dt1);
	                var dt2 = $('#do_datuma').datepicker('getDate');
	                if (dt2 <= dt1) {
	                    var minDate = $('#d_datuma').datepicker('option', 'minDate');
	                    $('#do_datuma').datepicker('setDate', minDate);
	                }
	                calculate();
	            }
	        });

	    	function calculate() {
	    	    var d1 = $("#od_datuma").datepicker('getDate');
	    	    var d2 = $("#do_datuma").datepicker('getDate');
	    	    var diff = 1;
	    	    if (d1 && d2) {
	    	        diff = diff + Math.floor((d2.getTime() - d1.getTime()) / 86400000); // ms per day
	    	    }
	    	    $("#broj_radnih_dana").val(diff);
	    	}
	    	
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
