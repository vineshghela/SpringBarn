drop table if exists `animal` CASCADE;
drop table if exists `barn` CASCADE;
 
create table barn (id bigint PRIMARY KEY AUTO_INCREMENT, area double not null, colour varchar(255) not null, name varchar(255) not null);
create table animal (id bigint PRIMARY KEY AUTO_INCREMENT, age integer not null, name varchar(255), type varchar(255), barn_id bigint);