create database GodišnjiOdmorPlaćeniDopust
go

use GodišnjiOdmorPlaćeniDopust
go

create table OrganizacijskaJedinica
(
	IDOrganizacijskaJedinica int primary key identity not null,
	Naziv nvarchar(30)
)

create table Zaposlenik
(
	IDZaposlenik int primary key identity not null,
	Ime nvarchar(20) not null,
	Prezime nvarchar(30) not null,
	KorisničkoIme nvarchar(20),
	Lozinka nvarchar(130),
	MatičniBroj nvarchar(11) not null,
	DatumZaposlenja date not null,
	Rukovoditelj nvarchar(2) not null,
	TjelesnoOštećenjeInvalidnost nvarchar(20) not null,
	GodineStaža tinyint not null,
	BrojDjece tinyint,
	OrganizacijskaJedinicaID int foreign key references OrganizacijskaJedinica(IDOrganizacijskaJedinica)
)

create table Dijete
(
	IDDijete int primary key identity not null,
	DatumRodjenja date not null,
	Starost tinyint not null, 
	ZaposlenikID int foreign key references Zaposlenik(IDZaposlenik)
)

create table Zahtjev
(
	IDZahtjev int primary key identity not null,
	OdDatuma date not null,
	DoDatuma date not null,
	BrojRadnihDana tinyint not null,
	OdobrenjeOd nvarchar(50) not null,
	Napomena nvarchar(500),
	ZaposlenikID int foreign key references Zaposlenik(IDZaposlenik)
)

create table TipZahtjeva
(
	IDTip int primary key identity not null,
	Tip nvarchar(20) not null,
	ZahtjevID int foreign key references Zahtjev(IDZahtjev)
)

create table StatusZahtjeva
(
	IDStatus int primary key identity not null,
	Status nvarchar(20) not null,
	ZahtjevID int foreign key references Zahtjev(IDZahtjev)
)

create table PlaćeniDopust
(
	IDPlaćeniDopust int primary key identity not null,
	Tip nvarchar(70) not null, 
	TipID int foreign key references TipZahtjeva(IDTip)
)

create table Stavka
(
	IDStavka int primary key identity not null,
	ZahtjevID int foreign key references Zahtjev(IDZahtjev),
)

insert into OrganizacijskaJedinica values('Istraživanje i razvoj')
insert into OrganizacijskaJedinica values('Projektni ured')
insert into OrganizacijskaJedinica values('Nabava')
insert into OrganizacijskaJedinica values('Financije')
insert into OrganizacijskaJedinica values('Kadrovska služba')
insert into OrganizacijskaJedinica values('Održavanje')
insert into OrganizacijskaJedinica values('Prodaja')