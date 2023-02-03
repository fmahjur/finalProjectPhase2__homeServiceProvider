insert into base_service(id, is_deleted, name)
values (3, false, 'service3');

insert into sub_service(id, base_price, description, is_deleted, name, base_service_id)
values (3, 220.2, 'description', false, 'subService10', 3);

