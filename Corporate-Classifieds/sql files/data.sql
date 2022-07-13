create Table user(
	empid int primary key auto_increment,
	emp_username varchar(50) not null,
	emp_password varchar(50) not null
	);
    
create Table points(
	pointsid int primary Key auto_increment,
    empid int not null,
    points int,
    offerid int not null
);
insert into user(empid,emp_username,emp_password) values (1,'Ashish','Ashish');
insert into user(empid,emp_username,emp_password) values (2,'Prateek','Prateek');
insert into user(empid,emp_username,emp_password) values (3,'Manvendra','Manvendra');
insert into user(empid,emp_username,emp_password) values (4,'megha','Megha');
insert into user(empid,emp_username,emp_password) values (5,'Test','Test');