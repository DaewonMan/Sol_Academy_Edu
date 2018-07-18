--drop table FEB12_DWM_SUPPLIES cascade constraint purge;
--drop sequence FEB12_DWM_SUPPLIES_SEQ;

create sequence FEB13_DWM_SUPPLIES_SEQ;

create table FEB13_DWM_SUPPLIES(
	s_no number(2) primary key,
	s_name varchar2(10 char) not null,
	s_price number(10) not null,
	s_amount number(5) not null,
	s_floor number(2) not null
);
--insert into FEB12_DWM_SUPPLIES values(FEB12_DWM_SUPPLIES_SEQ.nextval, '', 0, 0, '');

create sequence FEB13_DWM_FactoryItem_SEQ;
create table FEB13_DWM_FactoryItem(
	f_no number(2) primary key,
	f_name varchar2(10 char) not null,
	f_amount number(5) not null,
	f_cost number(10) not null
);
--insert into FEB13_DWM_FactoryItem values(FEB13_DWM_FactoryItem_SEQ.nextval, '',0,0);

--update FEB13_DWM_FACTORYITEM
--set f_amount = f_amount + 10
--where f_no = 1;

--delete from FEB13_DWM_FACTORYITEM where f_no = ?;

create sequence FEB13_DWM_SellItem_SEQ;
create table FEB13_DWM_SellItem (
	s_no number(3) primary key,
	s_name varchar2(10 char) not null,
	s_sold number(5) not null,
	s_sum number(10) not null
);

--update FEB13_DWM_SellItem
--set f_amount = f_amount - 10
--where f_no = 1;


select * from FEB13_DWM_SellItem;
select * from FEB13_DWM_FactoryItem;
select * from FEB13_DWM_SUPPLIES;