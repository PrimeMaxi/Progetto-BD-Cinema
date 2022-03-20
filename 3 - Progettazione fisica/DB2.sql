	drop view if exists Max_affluenza;
	drop view if exists List_affluenza;
	drop table if exists Posto_Prenotato;
	drop table if exists Biglietto;
	drop table if exists proiezione;
	drop table if exists film;
	drop type if exists genere;
	drop table if exists posto;
	drop table if exists sala;
	drop type if exists tecnologia;
	drop type if exists audio;
	drop table if exists cinema;
	drop type if exists fasciorari;




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

	CREATE TYPE TECNOLOGIA AS ENUM ('null','IMAX','ISense','ScreenX','3D');
	CREATE TYPE AUDIO AS ENUM ('null','Dolby Digital Surround','Dolby digital plus');

	CREATE TABLE SALA (
		IdSala INTEGER PRIMARY KEY,
		Capienza INTEGER NOT NULL,
		Tecnologia TECNOLOGIA DEFAULT NULL,
		Audio AUDIO DEFAULT NULL,
		DisponibileSala BOOLEAN DEFAULT 'True' NOT NULL,
		IdCinemaFk INTEGER NOT NULL,
		CONSTRAINT fk_Cinema FOREIGN KEY (IdCinemaFk) REFERENCES CINEMA(IdCinema)
	);

	CREATE TABLE POSTO(
		IdPosto SERIAL PRIMARY KEY,
		FilaX CHAR(1) NOT NULL, --CHECK (UPPER(FilaX) = FilaX) restituisce errore se non si inserisce il carrattere in UPPER
		PostoY SMALLINT CHECK(PostoY BETWEEN 1 AND 28) NOT NULL,
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

	CREATE TYPE FASCIORARI AS ENUM ('16-18','18-20','20-22','22-24');

	CREATE TABLE PROIEZIONE(
		IdProiezione SERIAL PRIMARY KEY,
		InizioData DATE DEFAULT CURRENT_DATE, --Se il campo è vuoto, imposta la data corrente
		FineData DATE DEFAULT (CURRENT_DATE + interval '28' DAY), -- se il campo è vuoto, imposta 28 giorni di disponibilità
		OraInizio TIME NOT NULL,
		OraFine TIME NOT NULL, --Trigger che imposta automaticamente l'orario di fine proiezione
		OrarioProiezione FASCIORARI DEFAULT NULL, ---Trigger gestito
		Prezzo FLOAT NOT NULL,
		IdFilmFk INTEGER NOT NULL,
		IdSalaFk INTEGER NOT NULL,
		CONSTRAINT fk_Film FOREIGN KEY (IdFilmFk) REFERENCES FILM(IdFilm) ON DELETE CASCADE ON UPDATE CASCADE,
		CONSTRAINT fk_SalaP FOREIGN KEY (IdSalaFk) REFERENCES SALA(IdSala) ON DELETE CASCADE
	);
	
		CREATE TABLE Biglietto(
		IdBiglietto SERIAL PRIMARY KEY,
		IdProiezioneFk INTEGER NOT NULL,
		IdPostoFk INTEGER NOT NULL,
		DisponibilePosto BOOLEAN DEFAULT 'True' NOT NULL,
		DataBiglietto TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
		CONSTRAINT fk_Proiezione FOREIGN KEY (IdProiezioneFk) REFERENCES PROIEZIONE(IdProiezione) ON DELETE CASCADE
	);
	/*
	
	Dovrebbe essere una tabella che contiene solo chiavi esterne.
	
	CREATE TABLE POSTO_PRENOTATO(
		IdPostoPrenotato SERIAL PRIMARY KEY,
		IdPostoFk INTEGER NOT NULL,
		IdProiezioneFk INTEGER NOT NULL,
		IdBigliettoFk INTEGER NOT NULL,
		CONSTRAINT fk_Posto FOREIGN KEY (IdPostoFk) REFERENCES POSTO(IdPosto),
		CONSTRAINT fk_Proiezione FOREIGN KEY (IdProiezioneFk) REFERENCES PROIEZIONE(IdProiezione),
		CONSTRAINT fk_Biglietto FOREIGN KEY (IdBigliettoFk) REFERENCES BIGLIETTO(IdBiglietto)
	);
	*/
		CREATE TABLE POSTO_PRENOTATO(
		IdPostoFk INTEGER NOT NULL,
		IdProiezioneFk INTEGER NOT NULL,
		IdBigliettoFk INTEGER NOT NULL,
		CONSTRAINT fk_Posto FOREIGN KEY (IdPostoFk) REFERENCES POSTO(IdPosto) ON DELETE CASCADE,
		CONSTRAINT fk_Proiezione FOREIGN KEY (IdProiezioneFk) REFERENCES PROIEZIONE(IdProiezione) ON DELETE CASCADE,
		CONSTRAINT fk_Biglietto FOREIGN KEY (IdBigliettoFk) REFERENCES BIGLIETTO(IdBiglietto)
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
		
		if(capienza > limite - 1) then RAISE EXCEPTION 'Non ci sono più sale disponibili';
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
		counX INTEGER := 1;
		counY INTEGER := 1;
		curr varchar;
	BEGIN
		filx :=ARRAY['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
		IdSalafk := NEW.IdSala;
		select capienza into capienza_sala from sala where IdSala = (select MAX(idsala) from sala);
		FOR itemX in 1..capienza_sala
		LOOP
			curr := filx[counX];
			INSERT INTO POSTO(FilaX,PostoY,IdSalaFk)
			VALUES
			(curr,counY,IdSalafk);
			counY:=counY+1;
			IF counY = 15 then counY:=1; counX:= counX+1;
			END IF;
		END LOOP;
		RETURN NEW;
	END;
	$BODY$
	LANGUAGE PLPGSQL;

	create trigger generate_posto
	AFTER insert on SALA
	FOR EACH ROW
	EXECUTE PROCEDURE generate_Posto();  

	---Funzion e Trigger che elemina un film dopo la scadenza di proiezione FineData
	---Non necessario, la lista di film disponibili per l'acquisto di biglietto terrà conto da backend 

	---Funzione e Trigger che calcola l'ora di fine proiezione in seguito dell'inserimento di tale in base alla durata del film.
	---Non necessario.
	
	---Funzione e Trigger che elimina i posti dopo la proiezione oppure imposta i posti disponibili a true.
	
	
	---Funzione e Trigger che assegna la fascia oraria appropriata per la proiezione.
	DROP TRIGGER IF EXISTS assignsFasciaOraria ON PROIEZIONE;
	DROP FUNCTION IF EXISTS assignsFasciaOraria();
	
	CREATE OR REPLACE FUNCTION assignsFasciaOraria() 
	RETURNS TRIGGER AS 
	$$
	DECLARE
	BEGIN
		IF(NEW.OraInizio >= TIME '16:00:00' AND NEW.OraInizio < TIME '18:00:00') then 
			UPDATE PROIEZIONE
			SET OrarioProiezione = '16-18'::FASCIORARI
			WHERE IdProiezione = NEW.IdProiezione;
		ELSIF(NEW.OraInizio >= TIME '18:00:00' AND NEW.OraInizio < TIME '20:00:00') THEN
				UPDATE PROIEZIONE
				SET OrarioProiezione = '18-20'::FASCIORARI
				WHERE IdProiezione = NEW.IdProiezione;
		ELSIF(NEW.OraInizio >= TIME '20:00:00' AND NEW.OraInizio < TIME '22:00:00') THEN
				UPDATE PROIEZIONE
				SET OrarioProiezione = '20-22'::FASCIORARI
				WHERE IdProiezione = NEW.IdProiezione;
		ELSIF(NEW.OraInizio >= TIME '22:00:00' AND NEW.OraInizio < TIME '01:00:00') THEN
				UPDATE PROIEZIONE
				SET OrarioProiezione = '22-24'::FASCIORARI
				WHERE IdProiezione = NEW.IdProiezione;
		END IF;
		RETURN NEW;
	END;
	$$
	LANGUAGE PLPGSQL;

	CREATE TRIGGER assignsFasciaOraria
	AFTER INSERT on PROIEZIONE
	FOR EACH ROW
	EXECUTE PROCEDURE assignsFasciaOraria();
	
	---Funzione e Trigger che inserisce automaticamente records ogni nuovo biglietto
	DROP TRIGGER IF EXISTS insertPostPrenotato ON BIGLIETTO;
	DROP FUNCTION IF EXISTS insertPostPrenotato();
	
	CREATE OR REPLACE FUNCTION insertPostPrenotato() 
	RETURNS TRIGGER AS 
	$$
	DECLARE
	BEGIN
		INSERT INTO POSTO_PRENOTATO(IdPostoFk,IdProiezioneFk,IdBigliettoFk)
		VALUES
		(NEW.IdPostoFk,NEW.IdProiezioneFk,NEW.IdBiglietto);
		RETURN NEW;
	END;
	$$
	LANGUAGE PLPGSQL;

	CREATE TRIGGER insertPostPrenotato
	AFTER INSERT on BIGLIETTO
	FOR EACH ROW
	EXECUTE PROCEDURE insertPostPrenotato();
	
	---Funzione e Trigger che solleva errore se esiste una proiezione nello stesso orario e nello stesso giorno
	
	DROP TRIGGER if exists checkProiezione ON proiezione;
	drop function if exists checkProiezione();

	create or replace function checkProiezione()
	returns trigger as
	$$
	DECLARE
		proiezioni integer;
	BEGIN
		select count(*) into proiezioni
		FROM proiezione 
		WHERE iniziodata<=NEW.iniziodata and
			finedata>=NEW.finedata and
			idsalafk=NEW.idsalafk and
			(orarioproiezione=NEW.orarioproiezione or 
			orainizio=NEW.orainizio and orafine=NEW.orafine);

		IF proiezioni=0
		THEN
			RAISE info '%',proiezioni;
			RAISE info 'Proiezione inserita';
			return new;
		ELSE
			RAISE EXCEPTION 'Sala o proiezione già esistente';
		END IF;
	END;
	$$
	LANGUAGE plpgsql;

	CREATE TRIGGER checkProiezione
    BEFORE INSERT on proiezione
	FOR EACH ROW
	EXECUTE PROCEDURE checkProiezione();

	---Funzione e Trigger che una volta comprato un biglietto, imposti automaticamente il campo DisponibilePosto da TRUE a FALSE
	/*
	DROP TRIGGER IF EXISTS checkDisponibilePosto ON BIGLIETTO;
	DROP FUNCTION IF EXISTS checkDisponibilePosto();

	CREATE OR REPLACE FUNCTION checkDisponibilePosto() 
	RETURNS TRIGGER AS 
	$$
	DECLARE

	BEGIN
		UPDATE POSTO
		SET disponibileposto = false
		WHERE idposto = NEW.IdPostoFk;
		RETURN NEW;
	END;
	$$
	LANGUAGE PLPGSQL;

	CREATE TRIGGER checkDisponibilePosto
	AFTER INSERT on BIGLIETTO
	FOR EACH ROW
	EXECUTE PROCEDURE checkDisponibilePosto();  
	*/

	----------------------------------------------------------------------------------------------------------

	---Inserimento dati

	INSERT INTO CINEMA (NomeCinema,Indirizzo,Provincia,NumeroSala,Città,Telefono)
	VALUES
	('Happy MaxiCinema Afragola','Centro Commerciale Le Porte di Napoli, Via Santa Maria la Nova, 1, 80021 Afragola NA','NA',2,'Afragola','0818607136');

	INSERT INTO SALA(IdSala,Capienza,IdCinemaFk)
	VALUES
	(1,100,1),
	(2,100,1);

	INSERT INTO FILM (Titolo,Trama,Regia,Anno,Durata,Genere)
	VALUES 
	('Spider-Man: No Way Home',DEFAULT,'Jon Watts',2021,'02:28:00','Fantascienza'),
	('Matrix Resurrections',DEFAULT,'Lana Wachowski',2022,'02:28:00','Fantascienza'),
	('House of Gucci',DEFAULT,'Ridley Scott',2021,'02:38:00','Drammatico'),
	('No Time To Die',DEFAULT,'Cary Fukunaga',2021,'02:43:00','Azione');

	INSERT INTO PROIEZIONE (InizioData,FineData,orainizio,orafine,Prezzo,idfilmfk,idsalafk)
	VALUES
	('2022-03-18',DEFAULT,'20:00','22:30',10.00,1,1),
	('2022-03-18',DEFAULT,'17:00','19:00',7.00,2,1);
	
	INSERT INTO BIGLIETTO(IdProiezioneFk,IdPostoFk)
	VALUES
	(1,1),
	(1,2),
	(1,10),
	(1,11),
	(2,1),
	(2,2);
	/*
	INSERT INTO POSTO_PRENOTATO(IdPostoFk,IdProiezioneFk,IdBigliettoFk)
	VALUES
	(1,1,1),
	(2,1,2),
	(10,1,3),
	(11,1,4),
	(1,2,5),
	(2,2,6);
	*/
	

	--QUERY lista di film più remunerativi
	SELECT f.titolo AS Film, SUM(pr.prezzo) AS Ricavi
	FROM film AS f INNER JOIN proiezione AS pr ON f.idfilm=pr.idfilmfk INNER JOIN biglietto AS b ON pr.idproiezione = b.idproiezionefk
	GROUP BY f.titolo 
	ORDER BY Ricavi DESC;
	
	--VIEW Lista maggior affluenza
	CREATE VIEW List_affluenza AS
	select pr.orarioproiezione, SUM(b.idproiezionefk) as MAXaffluenza 
	from proiezione as pr inner join biglietto as b on pr.idproiezione = b.idproiezionefk  
	group by pr.orarioproiezione 
	ORDER by MAXaffluenza DESC;
	
	--VIEW Fascia oraria con massima affluenza
	CREATE VIEW Max_affluenza AS
	select *
	from List_affluenza
	where maxaffluenza = (
		select MAX(Maxaffluenza)
		from List_affluenza);
		
	
