start transaction;

savepoint first_savepoint;

drop table employee;

rollback to first_savepoint;

select * from employee;

update employee set emp_salary = 999;

savepoint second_savepoint;

update employee set emp_salary = 777;

select * from employee;

rollback to second_savepoint;

select * from employee;

rollback to first_savepoint;

select * from employee;

commit transaction;