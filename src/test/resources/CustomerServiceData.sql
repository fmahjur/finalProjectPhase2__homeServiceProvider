insert into credit(id, balance)
values (3, 15000);

insert into credit(id, balance)
values (4, 20000);

insert into customer(id, firstname, lastname, email_address, username, password, credit_id)
values (5, 'mahin', 'mahjour', 'mahin@gmail.com','mahin@gmail.com', 'mAhin@1234568', 3);

insert into address(id, address_detail, city)
values (1, 'satarkhan', 'TEHRAN');

insert into base_service(id, is_deleted, name)
values (1, false, 'service2');

insert into sub_service(id, base_price, description, is_deleted, name, base_service_id)
values (1, 220.2, 'description', false, 'subService10', 1);

insert into orders(id, customer_proposed_price, description, duration_of_work, order_number, order_status, address_id, customer_id, sub_service_id)
values (1, 200.5, 'description', 2, 'ad12546', 'WAITING_FOR_EXPERTS_OFFER',1, 5, 1);

insert into expert(id, email_address, firstname, lastname, password, username, expert_status, rate, credit_id)
values (1, 'nargess@gmail.com', 'narges', 'jamshidi', 'nargess@gmail.com', 'Nj!123456', 'ACCEPTED', 2.5, 4);

insert into offer(id, offer_date, offer_price, offer_status, proposed_start_date, duration_of_work,  expert_id, orders_id)
values (1, '2023-02-03 17:13:03.000000', 230, 'WAITING', '2023-02-05 17:00:00.000000', 3,1, 1 );