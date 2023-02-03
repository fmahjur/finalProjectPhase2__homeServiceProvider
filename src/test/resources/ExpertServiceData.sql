insert into base_service(id, is_deleted, name)
values (1, false, 'service2');

insert into sub_service(id, base_price, description, is_deleted, name, base_service_id)
values (1, 220.2, 'description', false, 'subService10', 1);

insert into sub_service(id, base_price, description, is_deleted, name, base_service_id)
values (2, 320.2, 'description', false, 'subService20', 1);

insert into comment(id, comment, is_deleted, score)
values (1, 'comment1', false, 2);

insert into comment(id, comment, is_deleted, score)
values (2, 'comment2', false, 4);
