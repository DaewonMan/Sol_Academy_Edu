x

drop table wonsta_reple cascade constraint;
drop sequence wonsta_reple_seq;

select * from wonsta_reple;
------------------------------------------------

create table wonsta_member(
	wm_id varchar2(10 char) primary key,
	wm_pw varchar2(10 char) not null,
	wm_name varchar2(10 char) not null,
	wm_add1 char(5 char) not null,
	wm_add2 varchar2(100 char) not null,
	wm_add3 varchar2(100 char) not null,
	wm_birth date not null,
	wm_img varchar2(200 char) not null,
	wm_open varchar2(10 char) not null
);

drop table wonsta_member cascade constraint;

select * from WONSTA_MEMBER;
--------------------------------------------------------
create table wonsta_posts(
	wp_no number(10) primary key,
	wp_id varchar2(10 char) not null,
	wp_title varchar2(100 char) not null,
	wp_hash varchar2(200 char) not null,
	wp_date date not null,
	wp_img varchar2(200 char) not null,
	wp_like number(10) not null
	
);

create sequence wonsta_posts_seq;

--update wonsta_posts set wp_like = wp_like - 1 where wp_no = 1
select * from WONSTA_POSTS;

drop table WONSTA_POSTS cascade constraint;
drop sequence wonsta_posts_seq;
-----------------------------------------------------------------------
-- 유저의 게시물 좋아요 여부를 저장하는 테이블
create table wonsta_like_bool(
	wlb_no number(10) primary key,
	wlb_id varchar2(10 char) not null,
	wlb_bool number(2) not null,
	wlb_pno number(10) not null
);

insert into wonsta_like_bool values(wonsta_like_bool_seq.nextval, 'eodnjs725', 1, 23);

create sequence wonsta_like_bool_seq;
select * from wonsta_like_bool;
--update wonsta_like_bool set wlb_bool = 1 where wlb_no = 1

drop table wonsta_like_bool cascade constraint;
drop sequence wonsta_like_bool_seq;
----------------------------------------------------------------------------

create table wonsta_follow(
	wf_no number(10) primary key,
	wf_id varchar2(10 char) not null,
	wf_follower varchar2(10 char) not null
);

create sequence wonsta_follow_seq;

select * from wonsta_follow;

drop table wonsta_follow cascade constraint;
drop sequence wonsta_follow_seq;
------------------------------------------------
create table wonsta_follow_wait(
	wfw_no number(10) primary key,
	wfw_id varchar2(10 char) not null,
	wfw_follower varchar2(10 char) not null
);

create sequence wonsta_follow_wait_seq;

select * from wonsta_follow_wait;