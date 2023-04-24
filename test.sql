create table t_member(
	id varchar2(10) not null primary key,	
	pw varchar2(15) not null,	
	name varchar2(15) not null,
	juminNumber varchar2(8) not null,
	trans varchar2(20) not null,
	email varchar2(40) not null,
	AuthenticationNumber varchar2(20)
	del_flag varchar2(10) default 'N'
)

alter table t_member modify id varchar2 primary key(30) 
delete from t_member from AuthenticationNumber where id=123 
alter table t_member add email varchar2(40)
insert into t_member (id, pw, name, juminNumber, trans) values ('123', '123', '123', '123', '123')
alter table t_member add del_flag varchar2(10) 

alter table t_member modify del_flag varchar2(10) default 'N'
alter table t_total_board modify readcnt number(4) default 0
update t_total_board set readcnt = 0;
alter table t_total_board modify readcnt number(4) set not null

update t_member set AuthenticationNumber='test' where id='kh060515'
update t_member set AuthenticationNumber=null where id='kh060515'

select * from t_total_board where mb_id = 'a1'

update T_TOTAL_BOARD set login_read = 'Y'

select * from T_TOTAL_BOARD

delete from t_total_board where num = 24

update T_TOTAL_BOARD set repstep = 0, repindent = 0 where repstep is null


select bo_num, day from T_HISTORY_BOARD

select * from 
	(select rownum as rnum, num, mb_id, title, content, orgfilename, filename, login_read, del_flag, to_char(writerday, 'yyyy-mm-dd') writerday, readcnt, repRoot, repStep, repIndent from 
		(select * from myboard order by repRoot desc, repStep asc)
	)
where ()rnum between 0 and 20) and login_read !=;


select * from 
	(select rownum as rnum, num, mb_id, title, content, orgfilename, filename, login_read, del_flag, to_char(writerday, 'yyyy-mm-dd') writerday, readcnt, repRoot, repStep, repIndent from 
		(select total.* from t_total_board total, t_history_board history where total.num = history.bo_num and history.mb_id = 'a1' order by history.day desc)
	) 
where rnum between 1 and 20

select * from t_total_board

select count(*) as cnt from t_history_board where bo_num = 17 and mb_id = 'a1'
select max(num) as num from t_history_board

select * from
(select rownum as rnum, num, mb_id, title, content, orgfilename, filename, login_read, del_flag, to_char(writerday, 'yyyy-mm-dd') writerday, readcnt, repRoot, repStep, repIndent from 
(select total.* from t_total_board total, t_like_board likeb where total.num = likeb.bo_num order by total.repRoot desc, total.repStep asc

SELECT bo_num, count(*) AS pop_bo FROM (select total.* from t_total_board total, t_like_board likeb where total.num = likeb.bo_num)) GROUP BY bo_num ORDER BY pop_bo DESC


where rnum between 1 and 15 

select bo_num,count(*) as pop_bo (select total.* from t_total_board total, t_like_board likeb where total.num=likeb.bo_num)from t_like_board group by bo_num order by pop_bo desc

select * from t_total_board where num in (select distinct bo_num from t_like_board)

select * from T_LIKE_BOARD

update t_total_board set readcnt = readcnt+1 where num = 9

select * from 
(select rownum as rnum, num, mb_id, title, content, orgfilename, filename, login_read, del_flag, to_char(writerday, 'yyyy-mm-dd') writerday, readcnt, repRoot, repStep, repIndent from
(select total.* from t_total_board total, t_like_board likeb where total.num = likeb.bo_num and likeb.mb_id = 'k1'))

select * from t_total_board where (select bo_num,count(*) as pop_bo from t_like_board group by bo_num order by pop_bo desc)
 

select *from (SELECT num, count(*) AS pop_bo FROM (select rownum as rnum, num, mb_id, title, content, orgfilename, filename, login_read, del_flag, to_char(writerday, 'yyyy-mm-dd') writerday, readcnt, repRoot, repStep, repIndent from (select total.* from t_total_board total, t_like_board likeb where total.num = likeb.bo_num order by total.repRoot desc, total.repStep asc)) where rnum group by likeb.bo_num)  GROUP BY num ORDER BY pop_bo DESC


select * from (select bo_num, count(*) as pop_bo from (select rownum as rnum, num, mb_id, title, content, orgfilename, filename, login_read, del_flag, to_char(writerday, 'yyyy-mm-dd') writerday, readcnt, repRoot, repStep, repIndent from (select total.* from t_total_board total, t_like_board likeb where total.num = likeb.bo_num))) where rnum between 1 and 15 


select * from t_member