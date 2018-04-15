
	password CHAR(15) not null, 
	banking_info CHAR(15), 
	FromDate DATE,
	ToDate DATE,
	resume CHAR(12),
	PRIMARY KEY (username, password));
    
create table Hires
	(username_e CHAR (10) not null, 
	password_e CHAR (15) not null, 
	username_p CHAR(10) not null, 
	password_p CHAR(15) not null, 
	wages CHAR(5),
	PRIMARY KEY (username_e, password_e, username_p, password_p),
	FOREIGN KEY (username_e, password_e) references Employee,
	FOREIGN KEY (username_p, password_p) references Producer);

create table writer_writes
	(username_e CHAR(10) not null, 
	password_e CHAR(15) not null, 
HorrorGenre CHAR(5),
FantasyGenre CHAR(5),
ActionGenre CHAR(5),
ThrillerGenre CHAR(5),
DramaGenre CHAR(5),
ComedyGenre CHAR(5),
MusicalGenre CHAR(5),
ScifiGenre CHAR(5),
FromDate DATE,
ToDate DATE, 
	pubNum int not null,
	PRIMARY KEY (username_e, password_e),
	FOREIGN KEY(pubNum) references Script_has,
	FOREIGN KEY(username_e, password_e) references Employee);

create table Director
	(username_e CHAR(10) not null, 
	password_e CHAR(15) not null,
HorrorGenre CHAR(5),
FantasyGenre CHAR(5),
ActionGenre CHAR(5),
ThrillerGenre CHAR(5),
DramaGenre CHAR(5),
ComedyGenre CHAR(5),
MusicalGenre CHAR(5),
ScifiGenre CHAR(5),
FromDate DATE,
ToDate DATE, 
	PRIMARY KEY (username_e, password_e),
	FOREIGN KEY (username_e, password_e) references Employee);

create table Movie_produces
	(Title varchar(20) ,
	Release_date DATE,
HorrorGenre CHAR(5),
FantasyGenre CHAR(5),
ActionGenre CHAR(5),
ThrillerGenre CHAR(5),
DramaGenre CHAR(5),
ComedyGenre CHAR(5),
MusicalGenre CHAR(5),
ScifiGenre CHAR(5),
	Rating int,
	Production_startdate DATE ,
	Production_enddate DATE,
	Filming_location CHAR (20),
	username_p CHAR(10), 
	password_p CHAR(15),
	username_w CHAR(10), 
	password_w CHAR(15),
	username_d CHAR(10), 
	password_d CHAR(15),
	PRIMARY KEY (title, Release_date),
	FOREIGN KEY (username_p, password_p) references producer
	ON DELETE CASCADE,
	FOREIGN KEY (username_w, password_w) references writer_writes
	ON DELETE CASCADE,
	FOREIGN KEY (username_d, password_d) references Director
	ON DELETE CASCADE);

create table Actor_stars
	(username_e CHAR(10) not null, 
	password_e CHAR(15) not null, 
HorrorGenre CHAR(5),
FantasyGenre CHAR(5),
ActionGenre CHAR(5),
ThrillerGenre CHAR(5),
DramaGenre CHAR(5),
ComedyGenre CHAR(5),
MusicalGenre CHAR(5),
ScifiGenre CHAR(5),
	gender CHAR(6),
languageEnglish  CHAR(5),
languageSpanish CHAR(5),
languageFrench CHAR(5), 
	height smallint CHECK (height > 1), 
	title varchar(20) not null, 
	release_date DATE not null,
	PRIMARY KEY (username_e, password_e),
	FOREIGN KEY (username_e, password_e) references Employee,
	FOREIGN KEY (title, release_date) references Movie_produces);

create table Favorite_Movies
(username_e CHAR(10) not null,
password_e CHAR(15) not null,
Title varchar(20) not null, 
Release_date DATE,
PRIMARY KEY (username_e, Title),
FOREIGN KEY (username_e, password_e) references Employee,
FOREIGN KEY (Title, Release_date) references Movie_produces);
    
insert into Users
values('Arash', '1', 'Arash', '0');

insert into Users
values('Ali$er', '12', 'Ali Serag', '1');

insert into Users
values('Mohzin!', '123', 'Mohsin Khan', '2');

insert into Users
values('MOA%', '1234', 'Muhammad Ali', '3');

Insert into script_has values (1,'LOTR','2007-05-02','6223 BatemanSt.');
Insert into script_has values (2,'Java','2007-05-02','6223 BatemanSt.');
Insert into script_has values (3,'CocaKing','2007-02-02','6223 Bateman St.');
Insert into script_has values (4,'Singoods saints','2007-05-02','6223 BatemanSt.');
Insert into script_has values (5,'Planet of Faloodah','2000-02-02','6224 Bateman St.');
Insert into script_has values (6,'Sholeh Zard Tales','2000-05-02','6225 BatemanSt.');
Insert into script_has values (7,'Kubiddeh Kebob','2000-02-02','6226 Bateman St.');

insert into Producer
values('Mack99', 'Mackie', 'abc123',
'5522211225621', '2018-05-21' , '2019-05-21' , 3000, 1);
insert into producer values ('JJBean', 'John Maquire', 'Z142', '551234123', '2013-02-02','2019-05-21', 123,1); 
insert into Producer
values('Smithz3', 'George Smith', 'NoobPass123',
'142511225621', '2013-05-21' , '2014-05-21' , 4000, 1);
insert into producer values ('Quacker42', 'Tim Quaker', 'QQrrr452', '781234123', '2013-02-04','2019-07-21', 4223, 1); 
insert into Producer
values('Json94', 'Jason Tisdall', 'TissyJ',
'5522211225622', '2014-05-21' , '2015-05-21' , 3000, 1);
insert into producer values ('Ahmddie', 'Ahmad Mansur', '!2314a', '651234123', '2000-01-02','2004-05-21', 1213, 1); 

insert into employee values ('Jon94','abc1a2223', '123123123', '2000-12-21', '2007-02-02', 'www.shat@shat.com');
insert into employee values ('Deborah','Debbz123', '223123123', '2000-12-21', '2019-02-02', 'a1421.com');
insert into Employee
values('Tariq', '123Tsutsu', '66222514', '2013-03-20', '2015-05-02', 'www.lol.com');
insert into Employee
values('Toby', '123Toby', '2422514', '2016-04-02', '2020-04-20' , 'linkedin.com');
insert into Employee
values('Becky','Becky123', '66222514', '2013-03-20', '2015-05-02', 'linkedin.com');
insert into Employee
values('Logan','Logan123', '2522514', '2016-04-02', '2020-04-20' , 'linkedin.com');
insert into Employee
values('Jasmine','Jasmine123', '66262514', '2013-03-20', '2015-05-02', 'linkedin.com');
insert into Employee
values('Geoff', 'Geoff123', '2472514', '2016-04-02', '2020-04-20' , 'linkedin.com');
insert into Employee
values('Cathy', 'Cath123', '66224514', '2013-03-20', '2015-05-02', 'linkedin.com');
insert into Employee
values('Robert', 'Rob123', '24222514', '2016-04-02', '2020-04-20' , 'linkedin.com');
insert into Employee
values('Teresa', 'Teresa123', '66122514', '2013-03-20', '2015-05-02', 'linkedin.com');
insert into Employee
values('Jerry', 'Jerry123', '2422584', '2016-04-02', '2020-04-20' , 'linkedin.com');

insert into Hires
values('Jon94', 'abc1a2223', 'Mack99',
'abc123', '$9.99');

insert into writer_writes
values('Tariq',  '123Tsutsu', 'True', 'False', 'False', 'False', 'False', 'False', 'False', 'False', '2006-05-02', '2008-05-02', 7);
insert into writer_writes values ('Deborah','Debbz123', 'False', 'False', 'True', 'False', 'False', 'False', 'False', 'False', '2009-02-02','2012-02-02', 6);
insert into writer_writes values ('Becky','Becky123', 'False', 'False', 'False', 'False', 'False', 'True', 'False', 'False', '2009-02-02','2012-02-02',5);
insert into writer_writes values ('Logan','Logan123','False', 'False', 'False', 'False', 'True', 'False', 'False', 'False', '2000-02-02','2017-02-02', 3);
insert into writer_writes values ('Jasmine','Jasmine123','True', 'True', 'True', 'True', 'True', 'False', 'True', 'True','2001-02-02','2018-02-02',1);

insert into Director
values('Toby', '123Toby', 'True', 'False', 'False', 'False', 'False', 'False', 'False', 'False', '2001-05-02', '2017-05-02');
insert into Director
values('Geoff', 'Geoff123',  'True', 'False', 'False', 'False', 'False', 'False', 'False', 'False', '2001-05-02', '2016-05-02');
insert into Director
values('Cathy', 'Cath123', 'False', 'False', 'True', 'False', 'False', 'False', 'False', 'False', '2001-05-02', '2016-05-02');
insert into Director
values('Robert', 'Rob123', 'False', 'False', 'True', 'False', 'False', 'False', 'False', 'False', '2001-05-02', '2016-05-02');
insert into Director
values('Teresa', 'Teresa123', 'False', 'False', 'False', 'False', 'False', 'True', 'False', 'False', '2001-05-02', '2016-05-02');
insert into Director
values('Jerry', 'Jerry123', 'False', 'False', 'False', 'False', 'False', 'True', 'False', 'False', '2001-05-02', '2016-05-02');

insert into Movie_produces
values('Kubiddeh Kebob', '2006-05-02', 'False', 'False', 'True', 'False', 'False', 'False', 'False', 'False', 1, '2001-01-02','2015-05-02', 'Los Angeles', 'Ahmddie', '!2314a', 'Tariq',  '123Tsutsu', 'Toby', '123Toby');
insert into Movie_produces
values('Sholeh Zard Tales', '2009-05-02', 'False', 'False', 'True', 'False', 'False', 'False', 'False', 'False', 1, '2001-01-02','2006-05-02', 'Miami', 'Json94', 'TissyJ', 'Deborah', 'Debbz123', 'Cathy', 'Cath123');
insert into Movie_produces
values('Planet of Faloodah', '2007-05-02', 'False', 'False', 'False', 'False', 'False', 'True', 'False', 'False', 1, '2004-01-02','2006-05-02', 'Miami', 'Quacker42', 'QQrrr452','Becky','Becky123','Teresa', 'Teresa123');
insert into Movie_produces
values('Singoods saints', '2006-05-02', 'False', 'False', 'False', 'False', 'False', 'True', 'False', 'False', 10, '2004-05-02','2005-05-02', 'Vancouver', 'Smithz3', 'NoobPass123','Becky','Becky123', 'Jerry', 'Jerry123');
insert into Movie_produces
values('CocaKing', '2006-05-03', 'True', 'False', 'False', 'False', 'False', 'False', 'False', 'False', 50, '2004-05-02','2005-05-02', 'Vancouver', 'JJBean', 'Z142', 'Tariq', '123Tsutsu', 'Geoff', 'Geoff123');
insert into Movie_produces
values('LOTR', '2007-05-02', 'True', 'False', 'False', 'False', 'False', 'False', 'False', 'False', 100, '2003-07-02','2006-04-02', 'Vancouver', 'Mack99', 'abc123','Tariq',  '123Tsutsu', 'Toby', '123Toby');

insert into Actor_stars
values('Toby','123Toby', 'True', 'False', 'False', 'False', 'False', 'False', 'False', 'False', 'Male', 'True', 'False', 'False', 6, 'LOTR', '2007-05-02');
insert into Actor_stars
values('Tariq','123Tsutsu', 'False', 'False', 'False', 'False', 'False', 'True', 'False', 'False', 'Male', 'True', 'True', 'True', 5, 'Singoods saints', '2006-05-02');
insert into Actor_stars
values('Deborah','Debbz123', 'False', 'False', 'True', 'False', 'False', 'False', 'False', 'False', 'Female', 'False', 'False', 'False', 4, 'Sholeh Zard Tales', '2009-05-02');

Insert into Favorite_Movies
values('Toby', '123Toby', 'Planet of Faloodah', '2007-05-02');
