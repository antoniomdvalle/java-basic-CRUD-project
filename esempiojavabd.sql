
USE esempiojavabd;


SELECT * FROM impresa;
SELECT * FROM dipendente;



#ALTER TABLE dipendente ADD ammissione date;

/*CREATE TABLE dipendente(
id INT NOT NULL AUTO_INCREMENT,
nome VARCHAR(70) NOT NULL,
impresaid INT NOT NULL,
PRIMARY KEY(id)
);

ALTER TABLE dipendente ADD CONSTRAINT fk_impresa FOREIGN KEY (impresaid) REFERENCES impresa(id) ON DELETE RESTRICT ON UPDATE RESTRICT;*/

/*DROP DATABASE IF EXISTS esempiojavabd;

CREATE DATABASE esempiojavabd;
USE esempiojavabd;

CREATE TABLE impresa(
id INT NOT NULL auto_increment,
nomedellimpresa VARCHAR(70),
areadicompetenza VARCHAR(70),
PRIMARY KEY(id)
);*/