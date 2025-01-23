insert into app_user (name, email, password, role) values ('Nico', 'nico123@hola.com', '12345', 'ADMINISTRADOR');
insert into product (name, description, price, bar_code, stock) values ('Azucar', 'Alimento dulce', 100.00, 'a23b01px', 10);
insert into product_movement (amount, date_time, type, product_id)  values (2, '2025-01-20 23:55:00', 'INGRESO', 1);
insert into sale (date_time, used_balance, type, user_id)  values ('2025-01-20 23:56:00', 0, 'VENTA', 1);
insert into sale_detail (amount, unit_price, sale_id, product_id) values (3, 100.00, 1, 1);
insert into cash_movement (description, total, date_time, type) values ("Dinero para nafta", 20.00, '2025-01-20 23:57:00', 'EGRESO');
insert into cash_closing (start_date_time, end_date_time, total_sales, total_income, total_expenses, initial_amount, final_amount, total_closure, observations) values ('2025-01-20 23:50:00', '2025-01-20 23:59:00', 10, 100, 20, 0, 80, 120, 'Dia agitado');
