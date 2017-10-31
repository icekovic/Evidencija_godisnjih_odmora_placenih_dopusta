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
	    	
	    	$("#btnKalendar").click(function(){
	    		$("#godisnjiKalendar").zabuto_calendar({language: "en"});
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