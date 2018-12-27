drop database if exists tirocinio2_5;
create schema if not exists tirocinio2_5;

use tirocinio2_5;

create table if not exists studente (
	Matricola varchar(10) primary key,
    Nome varchar(25) null,
    Cognome varchar(25)  null,
    Email varchar(64) not null unique,
    Username varchar(32) not null unique,
    psw varchar(128) not null,
    linkedin varchar(256), 
    link_curriculum varchar(256) 
);

create table if not exists professore_tutoraziendale (
	Username varchar(32) primary key,
    Nome varchar(25) null,
    Cognome varchar(25)  null,
    Tipo varchar(24)  not null,
    Company varchar(64)  null,
    Indirizzo varchar(32)  null,
    Telefono varchar(15)  null,
    Fax varchar(15)  null,
    Email varchar(64) not null unique,
    psw varchar(128) not null,
    Citta varchar(32)  null,
    SitoWeb varchar(64)  null,
    ChiSono varchar(512)  null,
    Immagine_Profilo varchar(256)  null
);

create table if not exists segreteria (
	Username varchar(32) primary key,
    Email varchar(64) not null unique,
    psw varchar(128) not null
);

create table if not exists tirocinio (
	id int auto_increment primary key,
	Stato varchar(32) not null,
    Tipo varchar(24) not null,
    MatricolaStudente varchar(10) not null,
    SegreteriaUsername varchar(32) not null,
    TutorUsername varchar(32) not null,
    foreign key (MatricolaStudente) references studente(Matricola)
								on delete cascade,
	foreign key (SegreteriaUsername) references segreteria(Username)
								on delete cascade,
	foreign key (TutorUsername) references professore_tutoraziendale(Username)
								on delete cascade
);

create table if not exists andamento (
	id int auto_increment primary key,
	DataT date not null,
    Ora_Inizio time not null,
    Ora_Fine time not null,
    TirocinioID int not null,
    foreign key (TirocinioID) references tirocinio(id)
								on delete cascade
);

create table  if not exists tirocini_precedenti (
  id int(11)  auto_increment primary key,
  azienda varchar(64) NOT NULL,
  nomeCognome varchar(128) NOT NULL,
  stato varchar(32) NOT NULL,
  oreSvolte varchar(100) NOT NULL,
  compitiSvolti varchar(150) NOT NULL,
  documentazione varchar(255) NULL,
  studenteMatricola varchar(10) NOT NULL,
  segreteriaUsername varchar(45) NOT NULL,
  foreign key (studenteMatricola) references studente(Matricola)
								on delete cascade,
  foreign key (segreteriaUsername) references segreteria(Username)
								on delete cascade
);

INSERT INTO segreteria (username,email,psw) values('segreteria','segreteria@segreteria.unisa.it','189bbbb00c5f1fb7fba9ad9285f193d1');