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
('Happy MaxiCinema Afragola','Centro Commerciale Le Porte di Napoli, Via Santa Maria la Nova, 1, 80021 Afragola NA','NA',2,'Afragola','0818607136');

CREATE TYPE TECNOLOGIA AS ENUM ('IMAX','ISense','ScreenX','3D');
CREATE TYPE AUDIO AS ENUM ('Dolby Digital Surround','Doby Digital Plus');


CREATE TABLE SALA (
	IdSala INTEGER PRIMARY KEY,
	Capienza INTEGER NOT NULL,
	Tecnologia TECNOLOGIA DEFAULT NULL,
	Audio AUDIO DEFAULT NULL,
	DisponibileSala BOOLEAN DEFAULT 'True' NOT NULL,
	IdCinemaFk INTEGER NOT NULL,
	CONSTRAINT fk_Cinema FOREIGN KEY (IdCinemaFk) REFERENCES CINEMA(IdCinema)
);

INSERT INTO SALA(IdSala,Capienza,IdCinemaFk)
VALUES
(1,100,1);


CREATE TABLE POSTO(
	IdPosto SERIAL PRIMARY KEY,
	FilaX CHAR(1) NOT NULL, --CHECK (UPPER(FilaX) = FilaX) restituisce errore se non si inserisce il carrattere in UPPER
	PostoY SMALLINT CHECK(PostoY BETWEEN 1 AND 28) NOT NULL,
	DisponibilePosto BOOLEAN DEFAULT 'True' NOT NULL,
	IdSalaFk INTEGER NOT NULL,
	CONSTRAINT fk_Sala FOREIGN KEY (IdSalaFk) REFERENCES SALA(IdSala) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TYPE GENERE AS ENUM ('Azione','Horror','Fantascienza','Comico','Thriller','Western','Documentario','Drammatico');


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
('Matrix Resurrections',DEFAULT,'Lana Wachowski',2022,'02:28:00','Fantascienza'),
('House of Gucci',DEFAULT,'Ridley Scott',2021,'02:38:00','Drammatico'),
('No Time To Die',DEFAULT,'Cary Fukunaga',2021,'02:43:00','Azione');

CREATE TABLE PROIEZIONE(
	IdProiezione SERIAL PRIMARY KEY,
	Data DATE NOT NULL,
	OraInizio TIME NOT NULL,
	OraFine TIME NOT NULL,
	IdFilmFk INTEGER NOT NULL,
	IdSalaFk INTEGER NOT NULL,
	CONSTRAINT fk_Film FOREIGN KEY (IdFilmFk) REFERENCES FILM(IdFilm),
	CONSTRAINT fk_SalaP FOREIGN KEY (IdSalaFk) REFERENCES SALA(IdSala)
);

CREATE TABLE BIGLIETTO(
	IdBiglietto SERIAL PRIMARY KEY,
	Prezzo FLOAT NOT NULL,
	IdProiezioneFk INTEGER NOT NULL,
	CONSTRAINT fk_Proiezione FOREIGN KEY (IdProiezioneFk) REFERENCES PROIEZIONE(IdProiezione)
);



--TRIGGER E FUNZIONI


--Funzione e Trigger che gestisce la creare di una sala rispettando il numero di sala che il cinema può contenere
DROP TRIGGER if exists controllo_numero_sala ON Sala;
DROP FUNCTION if exists limit_sala();

create function limit_sala() returns trigger as $BODY$
DECLARE
	limite INTEGER;
	capienza INTEGER;
BEGIN
	select NumeroSala into limite from cinema;
	select count(*) into capienza from sala;
	
	if(capienza > limite - 1) then RAISE EXCEPTION $$Non ci sono più sale disponibili$$;
		ELSE RETURN NEW;
	END IF;
END;
$BODY$
LANGUAGE PLPGSQL;
	
create trigger controllo_numero_sala
before insert on SALA
EXECUTE PROCEDURE limit_sala();

--Funzione e Trigger che genera il automaticamente le entità POSTO in base alla capienza data dall'entità SALA

DROP TRIGGER if exists generate_posto ON Sala;
DROP FUNCTION if exists generate_Posto();

CREATE OR REPLACE FUNCTION generate_Posto() 
RETURNS TRIGGER AS 
$BODY$
DECLARE
	capienza_sala INTEGER;
	filx char[];
	IdSalafk INTEGER;
	coun INTEGER := 1;
	curr varchar;
BEGIN
	filx :=ARRAY['A','B','C','D','E','F','G','H','I','L','M','N','O','P'];
	select MAX(IdSala) into IdSalafk from SALA;
	select capienza into capienza_sala from sala where IdSala = (select MAX(idsala) from sala);
	FOR item in 1..capienza_sala
	LOOP
		curr := filx[coun];
		INSERT INTO POSTO(FilaX,PostoY,IdSalaFk)
		VALUES
		(curr,coun,IdSalafk);
		coun:=coun+1;
		IF coun = 15 then coun:=1;
		END IF;
	END LOOP;
	RETURN NEW;
END;
$BODY$
LANGUAGE PLPGSQL;

create trigger generate_posto
AFTER insert on SALA
EXECUTE PROCEDURE generate_Posto();  
