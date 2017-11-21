drop database if exists userdatabase;
	create database userdatabase;
	use userdatabase;

create table users(
	id integer auto_increment,
	username varchar(10) not null unique,
	password varchar(10) not null,
	email varchar(10) not null unique,
	primary key(id)
);

insert into users(username, password, email)
	values("p", "pppp", "p@p.com");	
insert into users(username, password, email)
	values("o", "oooo", "o@o.com");
insert into users(username, password, email)
	values("i", "iiii", "i@i.com");
