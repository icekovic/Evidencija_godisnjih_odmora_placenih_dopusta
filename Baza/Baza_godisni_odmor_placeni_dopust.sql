create database GodisnjiOdmorPlaceniDopust
go

use GodisnjiOdmorPlaceniDopust
go

create table organizacijska_jedinica
(
	id_organizacijska_jedinica int primary key identity not null,
	naziv nvarchar(30) not null
)

insert into organizacijska_jedinica values('Istraživanje i razvoj')
insert into organizacijska_jedinica values('Projektni ured')
insert into organizacijska_jedinica values('Nabava')
insert into organizacijska_jedinica values('Financije')
insert into organizacijska_jedinica values('Kadrovska služba')
insert into organizacijska_jedinica values('Održavanje')
insert into organizacijska_jedinica values('Prodaja')

create table rola
(
	id_rola int primary key identity not null,
	naziv nvarchar(20) not null
)

insert into rola values('Obični zaposlenik')
insert into rola values('Rukovoditelj')

create table zaposlenik
(
	id_zaposlenik int primary key identity not null,
	ime nvarchar(20) not null,
	prezime nvarchar(30) not null,
	email nvarchar(80) not null,
	korisnicko_ime nvarchar(20) not null,
	lozinka nvarchar(130) not null,
	maticni_broj nvarchar(11) not null,
	datum_zaposlenja date not null,
	tjelesno_ostecenje_invalidnost nvarchar(20) not null,
	godine_staza tinyint not null,
	broj_djece tinyint not null,
	organizacijska_jedinica_id int not null foreign key references organizacijska_jedinica(id_organizacijska_jedinica),
	rola_id int not null foreign key references rola (id_rola)
)

create table placeni_dopust
(
	id_placeni_dopust int primary key identity not null,
	tip varchar(80),
	trajanje_u_danima tinyint
)

create table status_zahtjeva
(
	id_status_zahtjeva int primary key identity not null,
	status nvarchar(25) not null,
)

create table zahtjev
(
	id_zahtjev int primary key identity not null,
	od_datuma date not null,
	do_datuma date not null,
	tip nvarchar(20) not null,
	broj_radnih_dana tinyint not null,
	odobrenje_od nvarchar(50) not null,
	napomena nvarchar(200),
	zaposlenik_id int not null foreign key references zaposlenik(id_zaposlenik),
	placeni_dopust_id int foreign key references placeni_dopust(id_placeni_dopust),
	status_zahtjeva_id int foreign key references status_zahtjeva(id_status_zahtjeva)
)

insert into placeni_dopust values('Sklapanje braka', 3)
insert into placeni_dopust values('Porod supruge', 2)
insert into placeni_dopust values('Smrt člana uže obitelji', 4)
insert into placeni_dopust values('Smrt roditelja supružnika', 2)
insert into placeni_dopust values('Selidba u drugo mjesto', 3)
insert into placeni_dopust values('Selidba u istom mjestu', 2)
insert into placeni_dopust values('Elementarna nepogoda u domaćinstvu radnika', 3)
insert into placeni_dopust values('Teža bolest člana uže obitelji izvan mjesta stanovanja', 2)
insert into placeni_dopust values('Dobrovoljno davanje krvi', 1)
insert into placeni_dopust values('Za stipendiste za pripremu doktorske disertacije', 7)

create table dijete
(
	id_dijete int primary key identity not null,
	starost tinyint not null, 
	zaposlenik_id int  not null foreign key references zaposlenik(id_zaposlenik)
)
												------------rezervacija sobe--------------------------

create table grad
(
	id_grad int primary key identity not null,
	naziv nvarchar(20) not null
)

insert into grad values('Crikvenica')
insert into grad values('Dubrovnik')
insert into grad values('Makarska')
insert into grad values('Split')
insert into grad values('Zadar')

create table hotel
(
	id_hotel int primary key identity not null,
	naziv nvarchar(30) not null,
	broj_zvjezdica tinyint not null,
	detalji nvarchar(max) not null,
	grad_id int not null foreign key references grad(id_grad)
)

create table rezervacija
(
	id_rezervacija int primary key identity not null,
	datum_prijave date not null,
	datum_odjave date not null,
	zaposlenik_id int not null foreign key references zaposlenik(id_zaposlenik),
	hotel_id int not null foreign key references hotel(id_hotel)
)