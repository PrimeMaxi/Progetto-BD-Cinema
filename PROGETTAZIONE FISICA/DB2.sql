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
		InizioData DATE DEFAULT CURRENT_DATE, --Se il campo è vuoto, imposta la data corrente
		FineData DATE DEFAULT (CURRENT_DATE + interval '28' DAY), -- se il campo è vuoto, imposta 28 giorni di disponibilità
		CONSTRAINT chk_durata CHECK(durata > TIME '01:19:00' AND durata < TIME '03:20:00') --Verifica che l'inserimento della durata sia compreso dall'intervallo indicato
	);

	CREATE TABLE PROIEZIONE(
		IdProiezione SERIAL PRIMARY KEY,
		Data DATE NOT NULL,
		OraInizio TIME NOT NULL,
		OraFine TIME NOT NULL, --Trigger che imposta automaticamente l'orario di fine proiezione
		IdFilmFk INTEGER NOT NULL,
		IdSalaFk INTEGER NOT NULL,
		CONSTRAINT fk_Film FOREIGN KEY (IdFilmFk) REFERENCES FILM(IdFilm),
		CONSTRAINT fk_SalaP FOREIGN KEY (IdSalaFk) REFERENCES SALA(IdSala)
	);

	CREATE TABLE BIGLIETTO(
		IdBiglietto SERIAL PRIMARY KEY,
		Prezzo FLOAT NOT NULL,
		IdProiezioneFk INTEGER NOT NULL,
		IdSalaFk INTEGER NOT NULL,
		IdPostoFk INTEGER NOT NULL,
		CONSTRAINT fk_Proiezione FOREIGN KEY (IdProiezioneFk) REFERENCES PROIEZIONE(IdProiezione),
		CONSTRAINT fk_Sala FOREIGN KEY (IdSalaFk) REFERENCES SALA(IdSala),
		CONSTRAINT fk_Posto FOREIGN KEY (IdPostoFk) REFERENCES POSTO(IdPosto)
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
		counX INTEGER := 1;
		counY INTEGER := 1;
		curr varchar;
	BEGIN
		filx :=ARRAY['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
		select MAX(IdSala) into IdSalafk from SALA;
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
	EXECUTE PROCEDURE generate_Posto();  

	---Funzion e Trigger che elemina un film dopo la scadenza di proiezione FineData
	---Non necessario, la lista di film disponibili per l'acquisto di biglietto terrà conto da backend 

	---Funzione e Trigger che calcola l'ora di fine proiezione in seguito dell'inserimento di tale in base alla durata del film.
	---Non necessario.
	
	---Funzione e Trigger che elimina i posti dopo la proiezione oppure imposta i posti disponibili a true.

	---Funzione e Trigger che una volta comprato un biglietto, imposti automaticamente il campo DisponibilePosto da TRUE a FALSE

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


	----------------------------------------------------------------------------------------------------------

	---Inserimento dati

	INSERT INTO CINEMA (NomeCinema,Indirizzo,Provincia,NumeroSala,Città,Telefono)
	VALUES
	('Happy MaxiCinema Afragola','Centro Commerciale Le Porte di Napoli, Via Santa Maria la Nova, 1, 80021 Afragola NA','NA',2,'Afragola','0818607136');

	INSERT INTO SALA(IdSala,Capienza,IdCinemaFk)
	VALUES
	(1,100,1);

	INSERT INTO FILM (Titolo,Trama,Regia,Anno,Durata,Genere)
	VALUES 
	('Spider-Man: No Way Home',DEFAULT,'Jon Watts',2021,'02:28:00','Fantascienza'),
	('Matrix Resurrections',DEFAULT,'Lana Wachowski',2022,'02:28:00','Fantascienza'),
	('House of Gucci',DEFAULT,'Ridley Scott',2021,'02:38:00','Drammatico'),
	('No Time To Die',DEFAULT,'Cary Fukunaga',2021,'02:43:00','Azione');

	INSERT INTO PROIEZIONE (data,orainizio,orafine,idfilmfk,idsalafk)
	VALUES
	('2022-02-16','20:00','22:30',1,1),
	('2022-02-16','17:00','19:00',2,1);

	INSERT INTO BIGLIETTO (prezzo,idproiezionefk,idsalafk,idpostofk)
	VALUES
	(10.00,1,1,1),
	(10.00,1,1,2),
	(10.00,1,1,3),
	(10.00,1,1,4),
	(10.00,1,1,5),
	(10.00,1,1,6),
	(10.00,1,1,7),
	(10.00,1,1,8),
	(10.00,1,1,9),
	(10.00,1,1,10),
	(8.00,2,1,1);
	
	--QUERY lista di film più remunerativi
	SELECT f.titolo AS Film, SUM(b.prezzo) AS Ricavi
	FROM film AS f INNER JOIN proiezione AS pr ON f.idfilm=pr.idfilmfk INNER JOIN biglietto AS b ON pr.idproiezione = b.idproiezionefk
	GROUP BY f.titolo 
	ORDER BY Ricavi DESC