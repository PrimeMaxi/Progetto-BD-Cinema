drop table if exists Biglietto ;
drop table if exists proiezione;
drop table if exists film;
drop type if exists genere;
drop table if exists posto;
drop table if exists sala;
drop type if exists tecnologia;
drop type if exists audio;
drop table if exists cinema;

CREATE TABLE CINEMA (
	IdCinema SERIAL PRIMARY KEY,
	NomeCinema VARCHAR(50) NOT NULL UNIQUE,
	Indirizzo VARCHAR(250) NOT NULL UNIQUE,
	Provincia CHAR(2) NOT NULL,
	NumeroSala INTEGER NOT NULL,
	Città VARCHAR(50) NOT NULL,
	Telefono CHAR(10) NOT NULL UNIQUE,
	CONSTRAINT chk_telefono CHECK (Telefono not like '%[^0-9]%') --Assicura che un numero che inizia con 0 venga inserito nel DB
);

INSERT INTO CINEMA (NomeCinema,Indirizzo,Provincia,NumeroSala,Città,Telefono)
VALUES
('Happy MaxiCinema Afragola','Centro Commerciale Le Porte di Napoli, Via Santa Maria la Nova, 1, 80021 Afragola NA','NA',10,'Afragola','0818607136');

CREATE TYPE TECNOLOGIA AS ENUM ('IMAX','ISense','ScreenX','3D');
CREATE TYPE AUDIO AS ENUM ('Dolby Digital Surround','Doby Digital Plus');


CREATE TABLE SALA (
	IdSala SERIAL PRIMARY KEY,
	Capienza INTEGER NOT NULL,
	Tecnologia TECNOLOGIA NOT NULL,
	Audio AUDIO NOT NULL,
	DisponibileSala BOOLEAN DEFAULT 'True' NOT NULL,
	CONSTRAINT fk_Cinema FOREIGN KEY (IdSala) REFERENCES CINEMA(IdCinema)
);


CREATE TABLE POSTO(
	IdPosto SERIAL PRIMARY KEY,
	PostoY SMALLINT CHECK(PostoY BETWEEN 1 AND 28) NOT NULL,
	FilaX CHAR(1) NOT NULL,
	DisponibilePosto BOOLEAN DEFAULT 'True' NOT NULL,
	CONSTRAINT fk_Sala FOREIGN KEY (IdPosto) REFERENCES SALA(IdSala) ON DELETE CASCADE
);

CREATE TYPE GENERE AS ENUM ('Azione','Horror','Fantascienza','Comico','Thriller','Western','Documentario');


CREATE TABLE FILM (
	IdFilm SERIAL PRIMARY KEY,
	Titolo VARCHAR(50) NOT NULL UNIQUE,
	Trama VARCHAR(100) DEFAULT 'Nessuna trama inserita',
	Regia VARCHAR(50) DEFAULT 'Nessun registra inserito' NOT NULL,
	Anno SMALLINT CHECK(Anno BETWEEN 0 AND EXTRACT(YEAR FROM CURRENT_DATE)) NOT NULL, --Garantisce l'inserimento di Anno in formato YYYY e verifica che non venga inserito un anno superio a quella corrente
	Durata TIME NOT NULL,
	Genere GENERE NOT NULL,
	CONSTRAINT chk_durata CHECK(durata > TIME '01:19:00' AND durata < TIME '03:20:00') --Verifica che l'inserimento della durata sia compreso dall'intervallo indicato
);

INSERT INTO FILM (Titolo,Trama,Regia,Anno,Durata,Genere)
VALUES 
('Spider-Man: No Way Home',DEFAULT,'Jon Watts',2021,'02:28:00','Fantascienza'),
('Matrix Resurrections',DEFAULT,'Lana Wachowski',2022,'02:28:00','Fantascienza');

CREATE TABLE PROIEZIONE(
	IdProiezione SERIAL PRIMARY KEY,
	Data DATE NOT NULL,
	OraInizio TIME NOT NULL,
	OraFine TIME NOT NULL,
	CONSTRAINT fk_Film FOREIGN KEY (IdProiezione) REFERENCES FILM(IdFilm),
	CONSTRAINT fk_SalaP FOREIGN KEY (IdProiezione) REFERENCES SALA(IdSala)
);

CREATE TABLE BIGLIETTO(
	IdBiglietto SERIAL PRIMARY KEY,
	Prezzo FLOAT NOT NULL,
	CONSTRAINT fk_Proiezione FOREIGN KEY (IdBiglietto) REFERENCES PROIEZIONE(IdProiezione)
);

