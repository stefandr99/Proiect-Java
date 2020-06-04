drop table chestionar;
drop sequence chestionar_seq;
drop TRIGGER chestionar_on_insert;

create table chestionar(
	id integer not null,
  intrebare varchar(200) not null,
  v1 varchar(100),
	v2 varchar(100),
	v3 varchar(100),
	v4 varchar(100),
	corect varchar(100)
);




CREATE SEQUENCE chestionar_seq
START WITH 1
INCREMENT BY 1;



ALTER TABLE chestionar
  ADD (
    CONSTRAINT chestionar_pk PRIMARY KEY (id)
);



CREATE OR REPLACE TRIGGER chestionar_on_insert
  BEFORE INSERT ON chestionar
  FOR EACH ROW
BEGIN
  SELECT chestionar_seq.nextval
  INTO :new.id
  FROM dual;
END;


insert into chestionar (intrebare, v1, v2, v3, v4, corect) values ('Care este capitala Romaniei?', 'Bucuresti', 'Paris', 'Berlin', 'Madrid', 'Bucuresti');
insert into chestionar (intrebare, v1, v2, v3, v4, corect) values ('Care este capitala Frantei?', 'Bucuresti', 'Paris', 'Berlin', 'Madrid', 'Paris');

select * from chestionar;








