insert into sqlmap (key_,sql_) values ('userAdd','insert into users(id,name,password,level,login,recommend) values (?,?,?,?,?,?)');
insert into sqlmap (key_,sql_) values ('userGet','select * from users where id = ?');
insert into sqlmap (key_,sql_) values ('userGetAll','select * from users');
insert into sqlmap (key_,sql_) values ('userDeleteAll','delete from users');
insert into sqlmap (key_,sql_) values ('userGetCount','select count(*) from users');
insert into sqlmap (key_,sql_) values ('userUpdate','update users set name= ?, password=?, level=?,login=?,recommend=? where id=?');

insert into sqlmap (key_,sql_) values ('todoAdd','insert into todo (title,dueDate,finished,writer) VALUES (?,?,?,?)');
insert into sqlmap (key_,sql_) values ('todoGet','select * from todo where tno =?');
insert into sqlmap (key_,sql_) values ('todoGetAll','select * from todo');
insert into sqlmap (key_,sql_) values ('todoUpdate','update todo set title =?,dueDate = ?,finished = ?,writer =? where tno = ?');
