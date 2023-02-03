insert into base_service(id, is_deleted, name)
values (101, false, 'service5');

insert into sub_service(id, base_price, description, is_deleted, name, base_service_id)
values (201, 220.2, 'sub service10', false, 'subService10', 101);

insert into sub_service(id, base_price, description, is_deleted, name, base_service_id)
values (202, 320.2, 'sub service20', false, 'subService20', 101);