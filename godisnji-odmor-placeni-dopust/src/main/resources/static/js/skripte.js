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
	    	
	    	$(function(){
	    		$("#datum_odjave").datepicker({dateFormat: 'yy-mm-dd', minDate: 0});
	    	});
//	    
//	    //validacije
//	    	
	    	$(function(){
//	    		
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
	    		onSelect: function(dateStr){
	    			var min = $(this).datepicker('getDate'); // Get selected date
	    			$("#do_datuma_placeni_dopust").datepicker('option', 'minDate', min || '0'); // Set other min, default to today
	    		}
	    	});
});